package ro.pyxsmart.api.services;

import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.models.Brand;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();

    List<Brand> getAllActiveBrands();

    Brand createBrand( Brand brand);

    Brand editBrand (Brand brand);

    String saveLogo(MultipartFile file) throws IOException;





}
