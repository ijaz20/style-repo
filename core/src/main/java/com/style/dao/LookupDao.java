package com.style.dao;

import java.util.List;

import com.style.model.Branch;
import com.style.model.Partner;
import com.style.model.ProductCategory;
import com.style.model.Role;

/**
 * Lookup Data Access Object (GenericDao) interface. This is used to lookup
 * values in the database (i.e. for drop-downs).
 * 
 * @author mathi
 */
public interface LookupDao {
	// ~ Methods
	// ================================================================

	/**
	 * Returns all Roles ordered by name
	 * 
	 * @return populated list of roles
	 */
	List<Role> getRoles();

	/**
	 * Return all the product categories
	 * 
	 * @return
	 */
	List<ProductCategory> getProductCategories();

	/**
	 * Return all the partners
	 * 
	 * @return
	 */
	List<Partner> getPartners();
	
	/**
	 * Returns all the branches
	 * 
	 * @return
	 */
	List<Branch> getBranches();
}
