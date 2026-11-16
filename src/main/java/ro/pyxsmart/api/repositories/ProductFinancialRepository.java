package ro.pyxsmart.api.repositories;
import ro.pyxsmart.api.models.ProductFinancial;
import ro.pyxsmart.api.models.modelDTO.ProductFinancialRequest;

public interface ProductFinancialRepository{

    public ProductFinancial createProductFinancial(Long id, ProductFinancialRequest Financial);

    public ProductFinancial getProdFinancialByProductId(Long productId);

    public ProductFinancial updateProdFinancialByProductId(Long productId);

}
