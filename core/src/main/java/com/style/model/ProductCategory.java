package com.style.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

/**
 * This class represents the basic "ProductCategory" object in VSU that allows
 * for managing product category
 * 
 * @auther ganesh
 * @author mathi
 */
@Entity
@Table(name = "vsu_product_category")
@Indexed
@XmlRootElement
public class ProductCategory extends BaseObject implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;
    private String categoryName;
    private Set<Product> products = new HashSet<Product>();

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vsu_relation_product_category_product", joinColumns = { @JoinColumn(name = "product_category_id") }, inverseJoinColumns = @JoinColumn(name = "product_id"))
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this,
                ToStringStyle.DEFAULT_STYLE).append("categoryName",
                this.categoryName);
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductCategory)) {
            return false;
        }

        final ProductCategory productCategory = (ProductCategory) o;

        return !(id != null ? !id.equals(productCategory.getId())
                : productCategory.getId() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
}
