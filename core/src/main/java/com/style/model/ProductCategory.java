package com.style.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Manager on 12/7/14.
 */
@Entity
@Table(name = "product_category")
@Indexed
@XmlRootElement
public class ProductCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long categoryId;
	private Long categoryName;
	private Set<Product> products = new HashSet<Product>();

	public ProductCategory() {

	}

	public ProductCategory(Long id, String name) {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "categoryName", nullable = false, length = 50, unique = true)
	@Field
	public Long getCategoryName() {

		return categoryName;
	}

	public void setCategoryName(Long categoryName) {
		this.categoryName = categoryName;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "product_category_mapping", joinColumns = { @JoinColumn(name = "categoryId") }, inverseJoinColumns = @JoinColumn(name = "id"))
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
