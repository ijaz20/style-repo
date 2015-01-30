package com.style.product.service;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import com.style.exception.AppException;
import com.style.model.Product;
import com.style.model.ProductPrice;
import com.style.service.GenericManager;

/**
 * ProductManager Service Interface to handle communication between web and
 * persistence layer.
 * 
 * @author mathi
 */
public interface ProductManager extends GenericManager<Product, String> {

	/**
	 * get all products
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	List<Product> getAllProducts(int start, String[] brands, String[] categories)
			throws AppException;

	/**
	 * save product
	 * 
	 * @param product
	 * @return
	 */
	Product saveProduct(Product product, File file, String filePath)
			throws AppException;

	/**
	 * get product by id
	 * 
	 * @param id
	 * @return
	 */
	Product getProduct(String id) throws AppException;

	/**
	 * get product prices by id's
	 * 
	 * @param priceId
	 * @return
	 */
	ProductPrice getPrice(String priceId) throws AppException;
	
	/**
	 * get product by id with available time
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	Product getProductWithAvailableTime(String id, Calendar bookingDate) throws AppException;
	
	/**
	 * get product price for branch
	 * 
	 * @param branchId
	 * @return
	 */
	List<ProductPrice> getBranchProductPrices(String branchId);
}
