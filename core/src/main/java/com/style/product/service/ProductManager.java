package com.style.product.service;

import java.util.List;

import com.style.model.Product;
import com.style.model.ProductCategory;
import com.style.service.GenericManager;

public interface ProductManager extends GenericManager<Product, String>{

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
