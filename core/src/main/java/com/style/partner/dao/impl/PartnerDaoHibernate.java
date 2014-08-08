package com.style.partner.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.exception.AppException;
import com.style.model.Partner;
import com.style.partner.dao.PartnerDao;

/**
 * This class interacts with Hibernate session to save/delete and retrieve
 * Partner objects.
 * 
 * @author mathi
 */
@Repository("partnerDao")
public class PartnerDaoHibernate extends GenericDaoHibernate<Partner, String>
		implements PartnerDao {

	public PartnerDaoHibernate() {
		super(Partner.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	public Partner savePartner(Partner partner) {
		try {
			return (Partner) getSession().merge(partner);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Partner> getPartners() {
		try {
			log.debug("Retrieving all partner names...");
			return getSession().createCriteria(Partner.class).list();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Partner getPartner(String id) {
		try {
			List<Partner> partners = getSession().createCriteria(Partner.class)
					.add(Restrictions.eq("id", id)).list();
			if (partners.isEmpty()) {
				return null;
			} else {
				return partners.get(0);
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}
}
