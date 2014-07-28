package com.style.service;

import java.util.List;

import com.style.model.Branch;
import com.style.model.LabelValue;
import com.style.model.Partner;
import com.style.model.ProductCategory;

/**
 * Business Service Interface to talk to persistence layer and retrieve values
 * for drop-down choice lists.
 * 
 * @author mathi
 */
public interface LookupManager {
	/**
	 * Retrieves all possible roles from persistence layer
	 * 
	 * @return List of LabelValue objects
	 */
	List<LabelValue> getAllRoles();

	/**
	 * Return all the product categories
	 * 
	 * @return
	 */
	List<LabelValue> getAllProductCategories();

	/**
	 * Return all the partners
	 * 
	 * @return
	 */
	List<LabelValue> getAllPartners();
	
	/**
	 * Returns all the branches
	 * 
	 * @return
	 */
	List<LabelValue> getAllBranches();
	
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
