package com.style.product.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.model.Product;
import com.style.model.ProductCategory;
import com.style.product.dao.ProductDao;

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
	@SuppressWarnings({ "unchecked" })
	public List<Product> getAllProducts(int start, String[] brands,
			String[] categories) {
		List<Product> products = new ArrayList<Product>();
		/*Criteria criteria = getSession().createCriteria(Product.class,
				"product");
		criteria.addOrder(Order.asc("product.id"));
		
		if (brands != null && brands.length > 0) {
			criteria.createAlias("product.productPrices", "price");
			criteria.createAlias("price.branch", "branch");
			criteria.add(Restrictions.in("branch.partner.id", brands));
		}
		
		if (categories != null && categories.length > 0) {
			criteria.createAlias("product.categories", "category");
			criteria.add(Restrictions.in("category.id", categories));
		}
		criteria.setFirstResult(start).setMaxResults(start + 15);*/
		
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//criteria.setProjection(Projections.distinct(Projections.property("product.id")));
		/*criteria.setProjection(Projections.projectionList()
		        .add(Projections.groupProperty("product.id")))
		    .setResultTransformer(Transformers.aliasToBean(Product.class));*/
		//criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("product.id")).add(Projections.property("product.categories").as("categories")).add(Projections.property("product.productPrices").as("productPrices")).add(Projections.property("product.id").as("id")).add(Projections.property("product.productName").as("productName")).add(Projections.property("product.isComboProduct").as("isComboProduct")).add(Projections.property("product.gender").as("gender"))).setResultTransformer(Transformers.aliasToBean(Product.class));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//List<Product> products = criteria.list();
		if(brands != null && categories != null){
			products = (List<Product>)getSession().createQuery("select product from Product as product join product.categories as category join product.productPrices as price join price.branch as branch where category.id in (:categories) and branch.brand.id in (:brands) ").setParameterList("categories", categories).setParameterList("brands", brands).setFirstResult(start).setMaxResults(start+15).list();
		} else if(brands != null){
			products = (List<Product>)getSession().createQuery("select product from Product as product join product.productPrices as price join price.branch as branch where branch.brand.id in (:brands)  group by product.id order by product.id asc").setParameterList("brands", brands).setFirstResult(start).setMaxResults(start+15).list();
		} else if(categories != null) {
			products = (List<Product>)getSession().createQuery("select product from Product as product join product.categories as category where category.id in (:categories)  group by product.id order by product.id asc").setParameterList("categories", categories).setFirstResult(start).setMaxResults(start+15).list();
		} else {
			products = (List<Product>)getSession().createQuery("select product from Product as product group by product.id order by product.id asc").setFirstResult(start).setMaxResults(start+15).list();
		}
		
		log.info("no of products------------"+products.size());
		return products;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getAllProductCategoriess() {
		Criteria criteria = getSession().createCriteria(ProductCategory.class);
		List<ProductCategory> categories = criteria.list();
		return categories;
	}

}
