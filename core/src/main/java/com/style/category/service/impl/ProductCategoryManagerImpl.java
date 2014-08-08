package com.style.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.category.dao.ProductCategoryDao;
import com.style.category.service.ProductCategoryManager;
import com.style.exception.AppException;
import com.style.model.LabelValue;
import com.style.model.ProductCategory;
import com.style.service.impl.GenericManagerImpl;

/**
 * Implementation of ProductManager interface.
 * 
 * @author mathi
 */
@Service("categoryManager")
public class ProductCategoryManagerImpl extends
		GenericManagerImpl<ProductCategory, String> implements
		ProductCategoryManager {

	private ProductCategoryDao categoryDao;

	@Autowired
	public ProductCategoryManagerImpl(ProductCategoryDao categoryDao) {
		super(categoryDao);
		this.categoryDao = categoryDao;
	}

	@Autowired
	public void setProductDao(ProductCategoryDao categoryDao) {
		this.dao = categoryDao;
		this.categoryDao = categoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductCategory> getAllProductCategoriess() throws AppException {
		return categoryDao.getAllProductCategoriess();
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductCategory saveCategory(ProductCategory category)
			throws AppException {
		return categoryDao.saveCategory(category);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllProductCategories() throws AppException {
		List<ProductCategory> categories = categoryDao.getProductCategories();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (ProductCategory category : categories) {
			list.add(new LabelValue(category.getCategoryName(), category
					.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductCategory> getProductCategories() throws AppException {
		return categoryDao.getProductCategories();
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductCategory getProductCategory(String id) throws AppException {
		return categoryDao.getProductCategory(id);
	}

}
