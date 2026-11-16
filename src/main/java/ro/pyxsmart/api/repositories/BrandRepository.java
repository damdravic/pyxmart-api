package ro.pyxsmart.api.repositories;

import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.models.Brand;

import java.util.List;

public interface BrandRepository {

    Brand createBrand (Brand brand);

    Brand editBrand (Brand brand);
    List<Brand> getBrands ();

    Brand getBrandById (Long id);

    Brand getBrandByName (String name);

    Brand getBrandBySlug ( String slug);

    void deleteBrand ( Long id );

    String saveLogo(MultipartFile file);
}
