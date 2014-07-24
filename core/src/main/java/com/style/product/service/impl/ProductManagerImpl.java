package com.style.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.model.Product;
import com.style.model.ProductCategory;
import com.style.product.dao.ProductDao;
import com.style.product.service.ProductManager;
import com.style.service.impl.GenericManagerImpl;

@Service("productManager")
public class ProductManagerImpl extends GenericManagerImpl<Product, String> implements ProductManager {
    
    private ProductDao productDao;
    
    @Autowired
	public ProductManagerImpl(ProductDao productDao) {
        super(productDao);
        this.productDao = productDao;
    }

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.dao = productDao;
		this.productDao = productDao;
	}
	
	/**
     * {@inheritDoc}
     */
    public List<Product> getAllProducts(int start, int end, String[] brands, String[] categories){
        return productDao.getAllProducts(start, end, brands, categories);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<ProductCategory> getAllProductCategoriess(){
       return productDao.getAllProductCategoriess(); 
    }
}
