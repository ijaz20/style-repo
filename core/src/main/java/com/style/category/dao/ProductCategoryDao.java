package com.style.category.dao;

import java.util.List;

import com.style.dao.GenericDao;
import com.style.model.ProductCategory;

/**
 * ProductCategory Data Access Object (GenericDao) interface.
 * 
 * @author mathi
 */
public interface ProductCategoryDao extends GenericDao<ProductCategory, String> {

	/**
	 * get all product categories
	 * 
	 * @return
	 */
	List<ProductCategory> getAllProductCategoriess();

	/**
	 * save category
	 * 
	 * @param category
	 * @return
	 */
	ProductCategory saveCategory(ProductCategory category);

	/**
	 * Return all the product categories
	 * 
	 * @return
	 */
	List<ProductCategory> getProductCategories();

	/**
	 * get category by id
	 * 
	 * @param id
	 * @return
	 */
	ProductCategory getProductCategory(String id);

}
