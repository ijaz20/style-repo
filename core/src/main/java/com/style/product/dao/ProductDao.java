package com.style.product.dao;

import java.util.List;

import com.style.dao.GenericDao;
import com.style.model.Product;
import com.style.model.ProductCategory;

public interface ProductDao extends GenericDao<Product, String>{

    /**
     * get all products
     * 
     * @param start
     * @param end
     * @return
     */
    List<Product> getAllProducts(int start, int end, String[] brands, String[] categories);
    
    /**
     * get all product categories
     * 
     * @return
     */
    List<ProductCategory> getAllProductCategoriess();
}
