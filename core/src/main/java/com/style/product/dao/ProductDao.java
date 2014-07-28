package com.style.product.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.style.dao.GenericDao;
import com.style.model.Branch;
import com.style.model.Partner;
import com.style.model.Product;
import com.style.model.ProductCategory;

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
	 * get all product categories
	 * 
	 * @return
	 */
	List<ProductCategory> getAllProductCategoriess();
	
	/**
	 * save partner
	 * 
	 * @param partner
	 * @return
	 */
	Partner savePartner(Partner partner);

	/**
	 * save branch
	 * 
	 * @param branch
	 * @return
	 */
	Branch saveBranch(Branch branch);

	/**
	 * save category
	 * @param category
	 * @return
	 */
	ProductCategory saveCategory(ProductCategory category);

	/**
	 * save product
	 * @param product
	 * @return
	 */
	Product saveProduct(Product product);
	
	/**
	 * 
	 * @param branchIds
	 * @return
	 */
	List<Branch> getBranches(String[] branchIds);
}
