package ro.pyxsmart.api.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.models.Product;
import ro.pyxsmart.api.models.ProductFinancial;
import ro.pyxsmart.api.models.ProductImages;
import ro.pyxsmart.api.models.ProductInventory;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.repositories.ProductFinancialRepository;
import ro.pyxsmart.api.repositories.ProductInventoryRepository;
import ro.pyxsmart.api.repositories.ProductRepository;
import ro.pyxsmart.api.services.ProductService;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Value("${upload.folder}")
    private String uploadFolder;

    private final ProductRepository productRepository;
    private final ProductFinancialRepository financialRepository;
    private final ProductInventoryRepository inventoryRepository;

    @Override
    @Transactional
    public ProductAdminDTO createNewProduct(ProductRequest productRequest, List<MultipartFile> images) throws IOException {

        productRepository.verifyCodeAndSlug(productRequest.getMpnCode(), productRequest.getSlug());
        Product product = productRepository.createProduct(productRequest);

        ProductFinancial productFinancial = financialRepository.createProductFinancial(product.getId(), productRequest.getFinancial());

        ProductInventory productInventory = inventoryRepository.createProductInventory(product.getId(), productRequest.getInventory());

        List<ProductImages> imagesUrls = saveImagesForProduct(product.getId(), images);

        return new ProductAdminDTO(product, productFinancial, productInventory, imagesUrls);
    }

    private List<ProductImages> saveImagesForProduct(Long id, List<MultipartFile> images) throws IOException {
        if (images == null || images.isEmpty()) {
            return List.of();
        }

        List<ProductImages> productImages = new java.util.ArrayList<>(List.of());

        Path uploadPath = Paths.get(uploadFolder, String.valueOf(id));
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        int imageIndex = 0;

        for (MultipartFile image : images) {

            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            boolean primaryImage = imageIndex == 0;

            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            ProductImages pi = ProductImages.builder()
                    .productId(id)
                    .imageUrl(filePath.toString())
                    .altText("Product Image")
                    .sortOrder(imageIndex)
                    .primary(primaryImage)
                    .build();

            try {

                productImages.add(productRepository.createProductImage(pi));
            } catch (DataAccessException exception) {
                deleteProductImages(uploadPath);
                productRepository.deleteProductImagesByProductId(id);
                productImages.clear();
                return productImages;
            }


            imageIndex++;

        }

        return productImages;

    }

    private void deleteProductImages(Path uploadPath) throws IOException {

        if (!Files.exists(uploadPath)) {
            return;
        }

        Files.walkFileTree(uploadPath, new SimpleFileVisitor<>() {

            @Override
            @NonNull
            public FileVisitResult visitFile(@NonNull Path file, @NonNull BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            @NonNull
            public FileVisitResult postVisitDirectory(@NonNull Path dir, IOException exc)
                    throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });

    }

    @Override
    public ProductAdminDTO updateProductById(Long id, ProductRequest requestProduct) {
        //TODO - implement product update
        return null;
    }

    @Override
    public ProductFinancial createProductFinancial(ProductFinancial financial) {
        return null;
    }

    @Override
    public List<ProductAdminDTO> getAll() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<String> sevaProductImages(List<MultipartFile> images) {


        return List.of();
    }
}
