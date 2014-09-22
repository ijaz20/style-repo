package com.style.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

/**
 * This class represents the basic "Booking" object in VSU that allows for
 * managing booking
 * 
 * @author mathi
 */

@Entity
@Table(name = "vsu_booking")
@Indexed
public class Booking extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bookingId;
	private List<BookingDetail> bookingDetails = new ArrayList<BookingDetail>();
	private User user;
	private int netPrice;
	private int totalPrice;
	private int totalDiscountPrice;
	private Calendar startTime;
	private Calendar endTime;
	private Calendar createdTime;
	private Calendar modifiedTime;
	private String status;
	private boolean isActive;
	private Payment payment;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	@Column(name = "user")
	@Lob
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = BookingDetail.class, mappedBy = "booking", fetch = FetchType.EAGER, orphanRemoval = true)
	@OrderBy("bookingDetailId")
	@Fetch(value = FetchMode.SELECT)
	public List<BookingDetail> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetail> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	@Column(name = "net_price")
	public int getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(int netPrice) {
		this.netPrice = netPrice;
	}

	@Column(name = "total_price")
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "discount_price")
	public int getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(int totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	@Column(name = "start_time")
	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time")
	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
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

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "payment")
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Column(name = "is_active")
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE)
				.append("bookingId", this.bookingId);
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Booking)) {
			return false;
		}

		final Booking booking = (Booking) o;

		return !(bookingId != null ? !bookingId.equals(booking.getBookingId())
				: booking.getBookingId() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (bookingId != null ? bookingId.hashCode() : 0);
	}
}
