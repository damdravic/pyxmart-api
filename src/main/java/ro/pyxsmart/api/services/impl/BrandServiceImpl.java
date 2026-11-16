package ro.pyxsmart.api.services.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.models.Brand;
import ro.pyxsmart.api.repositories.BrandRepository;
import ro.pyxsmart.api.services.BrandService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Value("${upload.folder}")
    private String uploadFolder;

    private  final BrandRepository brandRepo ;



    @Override
    public List<Brand> getAllBrands() {
        return brandRepo.getBrands();
    }

    @Override
    public List<Brand> getAllActiveBrands() {
        return List.of();
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepo.createBrand(brand);
    }

    @Override
    public Brand editBrand(Brand brand) {
        return brandRepo.editBrand(brand);
    }

    @Override
    public String saveLogo(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadFolder , "brandImg");

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(),filePath);


        return fileName;
    }
}
