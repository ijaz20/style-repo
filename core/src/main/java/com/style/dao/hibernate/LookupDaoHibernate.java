package com.style.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.style.dao.LookupDao;
import com.style.model.Partner;
import com.style.model.ProductCategory;
import com.style.model.Role;

/**
 * Hibernate implementation of LookupDao.
 * 
 * @author mathi
 */
@Repository
public class LookupDaoHibernate implements LookupDao {
	private Log log = LogFactory.getLog(LookupDaoHibernate.class);
	private final SessionFactory sessionFactory;

	/**
	 * Initialize LookupDaoHibernate with Hibernate SessionFactory.
	 * 
	 * @param sessionFactory
	 */
	@Autowired
	public LookupDaoHibernate(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		log.debug("Retrieving all role names...");
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Role.class).list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getProductCategories() {
		log.debug("Retrieving all role names...");
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(ProductCategory.class).list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Partner> getPartners() {
		log.debug("Retrieving all partners names...");
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Partner.class).list();
	}
}
