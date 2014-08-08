package com.style.category.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.style.category.dao.ProductCategoryDao;
import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.exception.AppException;
import com.style.model.ProductCategory;

/**
 * This class interacts with Hibernate session to save/delete and retrieve
 * Product objects.
 * 
 * @author mathi
 */
@Repository("categoryDao")
public class ProductCategoryDaoHibernate extends
		GenericDaoHibernate<ProductCategory, String> implements
		ProductCategoryDao {

	public ProductCategoryDaoHibernate() {
		super(ProductCategory.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getAllProductCategoriess() {
		try {
			Criteria criteria = getSession().createCriteria(
					ProductCategory.class);
			List<ProductCategory> categories = criteria.list();
			return categories;
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductCategory saveCategory(ProductCategory category) {
		try {
			return (ProductCategory) getSession().merge(category);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getProductCategories() {
		try {
			log.debug("Retrieving all category names...");
			return getSession().createCriteria(ProductCategory.class).list();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public ProductCategory getProductCategory(String id) {
		try {
			List<ProductCategory> categories = getSession()
					.createCriteria(ProductCategory.class)
					.add(Restrictions.eq("id", id)).list();
			if (categories.isEmpty()) {
				return null;
			} else {
				return categories.get(0);
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

}
