package com.style.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.model.Product;
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
}
