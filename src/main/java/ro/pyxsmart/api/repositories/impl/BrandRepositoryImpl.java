package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.mappers.BrandRowMapper;
import ro.pyxsmart.api.models.Brand;
import ro.pyxsmart.api.repositories.BrandRepository;

import java.util.List;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.BrandQueries.*;


@Repository
@RequiredArgsConstructor
public class BrandRepositoryImpl implements BrandRepository {


    private final NamedParameterJdbcTemplate jdbc ;


    @Override
    public Brand createBrand(Brand brand) {

        KeyHolder kh = new GeneratedKeyHolder();

        SqlParameterSource param = getParams(brand);

        jdbc.update(INSERT_NEW_BRAND_QUERY, param, kh);

        brand.setId(Objects.requireNonNull(kh.getKey()).longValue());

          return brand;


    }

    private SqlParameterSource getParams(Brand brand) {

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("name",brand.getName())
                .addValue("description",brand.getDescription())
                .addValue("slug",brand.getSlug())
                .addValue("active",brand.isActive())
                .addValue("logoUrl",brand.getLogoUrl())
                .addValue("logoUrlSecondary",brand.getLogoUrlSecondary());
        if(brand.getId() != null){
            params.addValue("id",brand.getId());
        }

        return params;
    }

    @Override
    public Brand editBrand(Brand brand) {


        jdbc.update(UPDATE_BRAND_QUERY,getParams(brand));

        return brand;

    }

    @Override
    public List<Brand> getBrands() {

        return jdbc.query(SELECT_ALL_BRANDS_QUERY,new BrandRowMapper());
    }

    @Override
    public Brand getBrandById(Long id) {
        return null;
    }

    @Override
    public Brand getBrandByName(String name) {
        return null;
    }

    @Override
    public Brand getBrandBySlug(String slug) {
        return null;
    }

    @Override
    public void deleteBrand(Long id) {

    }

    @Override
    public String saveLogo(MultipartFile file) {
        return "";
    }
}
