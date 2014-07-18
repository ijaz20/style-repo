package com.style.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

/**
 * This class represents the basic "Brand" object in VSU that allows for
 * managing partners
 * 
 * @auther ganesh
 * @author mathi
 */
@Entity
@Table(name = "vsu_brand")
@Indexed
public class Brand extends BaseObject implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String brandName;
    private Set<Branch> branches = new HashSet<Branch>();

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="brand_name")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand", cascade = CascadeType.ALL)
    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this,
                ToStringStyle.DEFAULT_STYLE)
                .append("brandName", this.brandName);
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Brand)) {
            return false;
        }

        final Brand brand = (Brand) o;

        return !(id != null ? !id.equals(brand.getId()) : brand.getId() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
}
