package ro.pyxsmart.api.resources.admin;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.models.ApiResponse;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.services.ProductService;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
@Slf4j
public class ProductResource {

    private final ProductService productService;

    @GetMapping("/test")
    public String getTest() {
        return "It is working";
    }

    @PostMapping(value = "/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<@NonNull ApiResponse> createNewProduct(
            @ModelAttribute ProductRequest productRequest,
            @RequestParam("images" ) List<MultipartFile> images) throws IOException {

        String message = "Product created";

        ProductAdminDTO productAdminDTO = productService.createNewProduct(productRequest , images);
        if((images != null && !images.isEmpty()) && (productAdminDTO.getProductImages() == null || productAdminDTO.getProductImages().isEmpty())){
            message = "Product created without images.Something goes wrong !!!";
        }

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message(message)
                        .data(Map.of("productAdminDTO", productService.createNewProduct(productRequest , images)))
                        .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<@NonNull ApiResponse> getAll() {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Products load successfully")
                        .data(Map.of("productsAdminDTO", productService.getAll()))
                        .build()
        );
    }

}
