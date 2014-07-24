package com.style.product.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
    public List<Product> getAllProducts(int start, int end, String[] brands, String[] categories) {
        // get last N runs for the user
        Criteria criteria = getSession().createCriteria(Product.class, "product");
        criteria.setFirstResult(start).setMaxResults(end);
        criteria.createAlias("product.productPrices", "price");
        criteria.createAlias("price.branch", "branch");
        criteria.createAlias("product.categories", "category");
        if(brands != null && brands.length > 0 ){
        	System.out.println("brnads----------");
            criteria.add(Restrictions.in("branch.partner.id", brands));
        }
        if(categories != null && categories.length > 0 ){
        	System.out.println("categories----------");
            criteria.add(Restrictions.in("category.id", categories));
        }
        System.out.println("after if----------");
        List<Product> products = criteria.list();
        System.out.println("after ifffffffff----------");
        System.out.println(products.size());
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
