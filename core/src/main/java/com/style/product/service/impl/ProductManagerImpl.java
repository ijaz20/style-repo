package com.style.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.model.Branch;
import com.style.model.Partner;
import com.style.model.Product;
import com.style.model.ProductCategory;
import com.style.product.dao.ProductDao;
import com.style.product.service.ProductManager;
import com.style.service.impl.GenericManagerImpl;

@Service("productManager")
public class ProductManagerImpl extends GenericManagerImpl<Product, String>
		implements ProductManager {

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
	public List<Product> getAllProducts(int start, String[] brands,
			String[] categories) {
		return productDao.getAllProducts(start, brands, categories);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductCategory> getAllProductCategoriess() {
		return productDao.getAllProductCategoriess();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Partner savePartner(Partner partner){
		return productDao.savePartner(partner);
	}

	/**
	 * {@inheritDoc}
	 */
	public Branch saveBranch(Branch branch){
		return productDao.saveBranch(branch);
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductCategory saveCategory(ProductCategory category){
		return productDao.saveCategory(category);
	}

	/**
	 * {@inheritDoc}
	 */
	public Product saveProduct(Product product){
		return productDao.saveProduct(product);
	}
	
	public List<Branch> getBranches(String[] branchIds){
		return productDao.getBranches(branchIds);
	}
}
