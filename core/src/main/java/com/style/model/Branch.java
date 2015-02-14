package com.style.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class represents the basic "Branch" object in VSU that allows for
 * managing partners branches
 * 
 * @auther ganesh
 * @author mathi
 */

@Entity
@Table(name = "vsu_branch")
@Indexed
public class Branch extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String branchName;
	private String username;
	private String password;
	private String address1;
	private String address2;
	private Area area;
	private String landmark;
	private Partner partner;
	private String description;
	private Set<ProductPrice> productPrices = new HashSet<ProductPrice>();
	private boolean isActive = true;
	private Calendar createdTime;
	private Calendar modifiedTime;
	private String createdBy;
	private String modifiedBy;
	private boolean isHomeServiceAvailable = false;
	private boolean isCashOnDeliveryAvailable = false;
	private boolean isFreeDeliveryAvailable = false;
	private int minimumDeliveryAmount;
	private int serviceCharge;
	private String openTime;
	private String closeTime;
	private List<String> availableTimes;
	private int noOfResource;
	private Set<User> branchUsers = new HashSet<>();
	private Map<Integer, Integer> availableResource = new HashMap<Integer, Integer>();

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "branch_name")
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.REMOVE, targetEntity = User.class, mappedBy = "userBranch", fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
	@JSON(serialize = false)
	public Set<User> getBranchUsers() {
		return branchUsers;
	}

	public void setBranchUsers(Set<User> branchUsers) {
		this.branchUsers = branchUsers;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "partner_id")
	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	@OneToMany(cascade = CascadeType.PERSIST, targetEntity = ProductPrice.class, mappedBy = "branch", fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
	@JSON(serialize = false)
	// @Fetch(value = FetchMode.SELECT)
	public Set<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(Set<ProductPrice> productPrices) {
		this.productPrices = productPrices;
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

	@Column(name = "is_home_service_available")
	public boolean getIsHomeServiceAvailable() {
		return isHomeServiceAvailable;
	}

	public void setIsHomeServiceAvailable(boolean isHomeServiceAvailable) {
		this.isHomeServiceAvailable = isHomeServiceAvailable;
	}

	@Column(name = "minimum_delivery_amount")
	public int getMinimumDeliveryAmount() {
		return minimumDeliveryAmount;
	}

	public void setMinimumDeliveryAmount(int minimumDeliveryAmount) {
		this.minimumDeliveryAmount = minimumDeliveryAmount;
	}

	@Column(name = "service_charge")
	public int getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	@Column(name = "is_cash_on_delivery_available")
	public boolean getIsCashOnDeliveryAvailable() {
		return isCashOnDeliveryAvailable;
	}

	public void setIsCashOnDeliveryAvailable(boolean isCashOnDeliveryAvailable) {
		this.isCashOnDeliveryAvailable = isCashOnDeliveryAvailable;
	}

	@Column(name = "is_free_delivery_available")
	public boolean getIsFreeDeliveryAvailable() {
		return isFreeDeliveryAvailable;
	}

	public void setIsFreeDeliveryAvailable(boolean isFreeDeliveryAvailable) {
		this.isFreeDeliveryAvailable = isFreeDeliveryAvailable;
	}

	@Column(name = "open_time")
	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "close_time")
	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name = "address1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "address2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@OneToOne
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Column(name = "landmark")
	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@Transient
    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }
    
    @Column(name = "no_of_resource")
	public int getNoOfResource() {
		return noOfResource;
	}

	public void setNoOfResource(int noOfResource) {
		this.noOfResource = noOfResource;
	}

	@Transient
	public Map<Integer, Integer> getAvailableResource() {
		return availableResource;
	}

	public void setAvailableResource(Map<Integer, Integer> availableResource) {
		this.availableResource = availableResource;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("branchName",
				this.branchName);
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Branch)) {
			return false;
		}

		final Branch branch = (Branch) o;

		return !(id != null ? !id.equals(branch.getId())
				: branch.getId() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

}
