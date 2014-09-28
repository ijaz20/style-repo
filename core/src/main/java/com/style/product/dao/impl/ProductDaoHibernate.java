package com.style.product.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.exception.AppException;
import com.style.model.Product;
import com.style.model.ProductPrice;
import com.style.product.dao.ProductDao;

/**
 * This class interacts with Hibernate session to save/delete and retrieve
 * Product objects.
 * 
 * @author mathi
 */
@Repository("productDao")
public class ProductDaoHibernate extends GenericDaoHibernate<Product, String>
		implements ProductDao {

	public ProductDaoHibernate() {
		super(Product.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductPrice> getPrices(List<String> priceIds) {
		try {
			List<ProductPrice> prices = (List<ProductPrice>) getSession()
					.createQuery(
							"select price from com.style.model.ProductPrice as price where price.priceId in (:priceIds)")
					.setParameterList("priceIds", priceIds).list();
			return prices;
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Product> getAllProducts(int start, String[] partners,
			String[] categories) {
		List<Product> products = new ArrayList<Product>();
		/*
		 * Criteria criteria = getSession().createCriteria(Product.class,
		 * "product"); criteria.addOrder(Order.asc("product.id"));
		 * 
		 * if (partners != null && partners.length > 0) {
		 * criteria.createAlias("product.productPrices", "price");
		 * criteria.createAlias("price.branch", "branch");
		 * criteria.add(Restrictions.in("branch.partner.id", partners)); }
		 * 
		 * if (categories != null && categories.length > 0) {
		 * criteria.createAlias("product.categories", "category");
		 * criteria.add(Restrictions.in("category.id", categories)); }
		 * criteria.setFirstResult(start).setMaxResults(start + 15);
		 */

		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// criteria.setProjection(Projections.distinct(Projections.property("product.id")));
		/*
		 * criteria.setProjection(Projections.projectionList()
		 * .add(Projections.groupProperty("product.id")))
		 * .setResultTransformer(Transformers.aliasToBean(Product.class));
		 */
		// criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("product.id")).add(Projections.property("product.categories").as("categories")).add(Projections.property("product.productPrices").as("productPrices")).add(Projections.property("product.id").as("id")).add(Projections.property("product.productName").as("productName")).add(Projections.property("product.isComboProduct").as("isComboProduct")).add(Projections.property("product.gender").as("gender"))).setResultTransformer(Transformers.aliasToBean(Product.class));
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// List<Product> products = criteria.list();
		try {
			if (partners != null && categories != null) {
				products = (List<Product>) getSession()
						.createQuery(
								"select product from Product as product join product.productPrices as price join price.branch as branch where product.category.id in (:categories) and branch.partner.id in (:partners) group by product.id order by product.id asc")
						.setParameterList("categories", categories)
						.setParameterList("partners", partners)
						.setFirstResult(start).setMaxResults(start + 15).list();
			} else if (partners != null) {
				products = (List<Product>) getSession()
						.createQuery(
								"select product from Product as product join product.productPrices as price join price.branch as branch where branch.partner.id in (:partners) group by product.id order by product.id asc")
						.setParameterList("partners", partners)
						.setFirstResult(start).setMaxResults(start + 15).list();
			} else if (categories != null) {
				products = (List<Product>) getSession()
						.createQuery(
								"select product from Product as product where product.category.id in (:categories) group by product.id order by product.id asc")
						.setParameterList("categories", categories)
						.setFirstResult(start).setMaxResults(start + 15).list();
			} else {
				products = (List<Product>) getSession()
						.createQuery(
								"select product from Product as product group by product.id order by product.id asc")
						.setFirstResult(start).setMaxResults(start + 15).list();
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
		log.info("no of products------------" + products.size());
		return products;
	}

	/**
	 * {@inheritDoc}
	 */
	public Product saveProduct(Product product) {
		try {
			return (Product) getSession().merge(product);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Product getProduct(String id) {
		try {
			List<Product> products = getSession().createCriteria(Product.class)
					.add(Restrictions.eq("id", id)).list();
			if (products.isEmpty()) {
				return null;
			} else {
				return products.get(0);
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}
}
