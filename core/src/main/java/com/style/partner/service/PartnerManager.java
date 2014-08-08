package com.style.partner.service;

import java.io.File;
import java.util.List;

import com.style.exception.AppException;
import com.style.model.LabelValue;
import com.style.model.Partner;
import com.style.service.GenericManager;

/**
 * PartnerManager Service Interface to handle communication between web and
 * persistence layer.
 * 
 * @author mathi
 */
public interface PartnerManager extends GenericManager<Partner, String> {

	/**
	 * save partner
	 * 
	 * @param partner
	 * @return
	 */
	Partner savePartner(Partner partner, File file, String filePath) throws AppException;

	/**
	 * Return all the partners
	 * 
	 * @return
	 */
	List<LabelValue> getAllPartners() throws AppException;

	/**
	 * Return all the partners
	 * 
	 * @return
	 */
	List<Partner> getPartners() throws AppException;

	/**
	 * get partner by id
	 * 
	 * @param id
	 * @return
	 */
	Partner getPartner(String id) throws AppException;

}
