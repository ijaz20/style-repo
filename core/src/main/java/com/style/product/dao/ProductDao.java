package com.style.product.dao;

import java.util.List;

import com.style.dao.GenericDao;
import com.style.model.Product;
import com.style.model.ProductPrice;

/**
 * Product Data Access Object (GenericDao) interface.
 * 
 * @author mathi
 */
public interface ProductDao extends GenericDao<Product, String> {

	/**
	 * get all products
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	List<Product> getAllProducts(int start, String[] brands, String[] categories);

	/**
	 * save product
	 * 
	 * @param product
	 * @return
	 */
	Product saveProduct(Product product);

	/**
	 * get product by id
	 * 
	 * @param id
	 * @return
	 */
	Product getProduct(String id);

	/**
	 * get product prices by id's
	 * 
	 * @param priceIds
	 * @return
	 */
	List<ProductPrice> getPrices(List<String> priceIds);
}
