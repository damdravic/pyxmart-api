package ro.pyxsmart.api.repositories;

import ro.pyxsmart.api.models.Brand;

public interface BrandRepository {

    Brand createBrand (Brand brand);

    Brand editBrand (Brand brand);

    Brand[] getBrands ();

    Brand getBrandById (Long id);

    Brand getBrandByName (String name);

    Brand getBrandBySlug ( String slug);

    void deleteBrand ( Long id );

}
