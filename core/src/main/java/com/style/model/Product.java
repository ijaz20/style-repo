package com.style.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
@Table(name = "product")
@Indexed
@XmlRootElement
public class Product extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Set<Branch> branches = new HashSet<Branch>();
	private Set<ProductPrice> productPrices = new HashSet<ProductPrice>();
	private Boolean isComboProduct;

	public Product() {
	}

	public Product(String name, Boolean isComboProduct) {
		this.name = name;
		this.isComboProduct = isComboProduct;
	}

	@Column(name = "name", nullable = false, length = 50, unique = true)
	@Field
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "is_combo_product", nullable = false)
	public Boolean getIsComboProduct() {
		return isComboProduct;
	}

	public void setIsComboProduct(Boolean isComboProduct) {
		this.isComboProduct = isComboProduct;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "product_branch_mapping", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = @JoinColumn(name = "branchId"))
	public Set<Branch> getBranches() {
		return branches;
	}

	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

	public void setProductPrices(Set<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "product_price_mapping", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = @JoinColumn(name = "productId"))
	public Set<ProductPrice> getProductPrices() {

		return productPrices;
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE);
		sb.append("id : ", getId());
		sb.append("name :", getName());
		sb.append("isComboProduct :", getIsComboProduct());
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
