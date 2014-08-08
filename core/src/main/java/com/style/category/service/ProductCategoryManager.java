package com.style.category.service;

import java.util.List;

import com.style.exception.AppException;
import com.style.model.LabelValue;
import com.style.model.ProductCategory;
import com.style.service.GenericManager;

/**
 * ProductCategoryManager Service Interface to handle communication between web
 * and persistence layer.
 * 
 * @author mathi
 */
public interface ProductCategoryManager extends
		GenericManager<ProductCategory, String> {

	/**
	 * get all product categories
	 * 
	 * @return
	 */
	List<ProductCategory> getAllProductCategoriess() throws AppException;

	/**
	 * save category
	 * 
	 * @param category
	 * @return
	 */
	ProductCategory saveCategory(ProductCategory category) throws AppException;

	/**
	 * Return all the product categories
	 * 
	 * @return
	 */
	List<LabelValue> getAllProductCategories() throws AppException;

	/**
	 * Return all the product categories
	 * 
	 * @return
	 */
	List<ProductCategory> getProductCategories() throws AppException;

	/**
	 * get category by id
	 * 
	 * @param id
	 * @return
	 */
	ProductCategory getProductCategory(String id) throws AppException;

}
