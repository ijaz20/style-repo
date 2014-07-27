package com.style.service;

import java.util.List;

import com.style.model.LabelValue;
import com.style.model.Partner;

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
	List<LabelValue> getProductCategories();

	/**
	 * Return all the partners
	 * 
	 * @return
	 */
	List<LabelValue> getPartners();
}
