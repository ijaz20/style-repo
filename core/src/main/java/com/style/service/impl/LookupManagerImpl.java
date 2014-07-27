package com.style.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.dao.LookupDao;
import com.style.model.LabelValue;
import com.style.model.Partner;
import com.style.model.ProductCategory;
import com.style.model.Role;
import com.style.service.LookupManager;

/**
 * Implementation of LookupManager interface to talk to the persistence layer.
 * 
 * @author mathi
 */
@Service("lookupManager")
public class LookupManagerImpl implements LookupManager {
	@Autowired
	LookupDao dao;

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllRoles() {
		List<Role> roles = dao.getRoles();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (Role role1 : roles) {
			list.add(new LabelValue(role1.getName(), role1.getName()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getProductCategories() {
		List<ProductCategory> categories = dao.getProductCategories();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (ProductCategory category : categories) {
			list.add(new LabelValue(category.getCategoryName(), category
					.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getPartners() {
		List<Partner> partners = dao.getPartners();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (Partner partner : partners) {
			list.add(new LabelValue(partner.getPartnerName(), partner.getId()));
		}

		return list;
	}
}
