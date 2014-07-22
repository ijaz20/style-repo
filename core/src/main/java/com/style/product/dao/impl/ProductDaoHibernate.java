package com.style.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.model.Product;
import com.style.product.dao.ProductDao;

@Repository("productDao")
public class ProductDaoHibernate extends GenericDaoHibernate<Product, String> implements ProductDao {

	public ProductDaoHibernate() {
		super(Product.class);
		// TODO Auto-generated constructor stub
	}

}
