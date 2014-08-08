package com.style.partner.dao;

import java.util.List;

import com.style.dao.GenericDao;
import com.style.model.Partner;

/**
 * Partner Data Access Object (GenericDao) interface.
 * 
 * @author mathi
 */
public interface PartnerDao extends GenericDao<Partner, String> {

	/**
	 * save partner
	 * 
	 * @param partner
	 * @return
	 */
	Partner savePartner(Partner partner);

	/**
	 * Return all the partners
	 * 
	 * @return
	 */
	List<Partner> getPartners();

	/**
	 * get partner by id
	 * 
	 * @param id
	 * @return
	 */
	Partner getPartner(String id);
}
