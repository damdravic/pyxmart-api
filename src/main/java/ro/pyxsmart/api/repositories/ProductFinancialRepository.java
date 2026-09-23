package ro.pyxsmart.api.repositories;
import ro.pyxsmart.api.models.ProductFinancial;

public interface ProductFinancialRepository{

    public ProductFinancial createProdFinancial(ProductFinancial productFinancial);

    public ProductFinancial getProdFinancialByProductId(Long productId);

    public ProductFinancial updateProdFinancialByProductId(Long productId);

}
