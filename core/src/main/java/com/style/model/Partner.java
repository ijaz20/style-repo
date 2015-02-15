package com.style.model;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
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
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import com.style.Constants;
import com.style.util.StringUtil;

/**
 * This class represents the basic "Brand" object in VSU that allows for
 * managing partners
 * 
 * @auther ganesh
 * @author mathi
 */
@Entity
@Table(name = "vsu_partner")
@Indexed
public class Partner extends BaseObject implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String partnerName;
    private String description;
    private String imagePath;
    private Set<Branch> branches = new HashSet<Branch>();
    private boolean isActive = true;
    private Calendar createdTime;
    private Calendar modifiedTime;
    private String createdBy;
    private String modifiedBy;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="partner_name")
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    @Column(name="image_path")
    public String getImagePath(){
        return Constants.DEFAULT_PRODUCT_IMAGE_PATH;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner", cascade = CascadeType.ALL)
    @JsonIgnore
    @JSON(serialize=false)
    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    @Column(name = "is_active", columnDefinition = "boolean default true", nullable = false)
    public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "created_time")
	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "modified_time")
	public Calendar getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Calendar modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this,
                ToStringStyle.DEFAULT_STYLE)
                .append("brandName", this.partnerName);
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Partner)) {
            return false;
        }

        final Partner brand = (Partner) o;

        return !(id != null ? !id.equals(brand.getId()) : brand.getId() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
}
