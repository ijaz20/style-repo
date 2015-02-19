package com.style.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.style.model.types.BookingState;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

/**
 * This class represents the basic "BookingDetails" object in VSU that allows
 * for managing booking details
 * 
 * @author mathi
 */

@Entity
@Table(name = "vsu_booking_detail")
@Indexed
public class BookingDetail extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bookingDetailId;
	private Product product;
	private int price;
	private int discount;
	private Booking booking;
	private Calendar createdTime;
	private Calendar modifiedTime;
	private BookingState status;
	private boolean isActive;
    private Calendar startTime;
    private Calendar endTime;
    private String bookingTime;

    @Column(name= "start_time")
    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    @Column(name= "end_time")
    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getBookingDetailId() {
		return bookingDetailId;
	}

	public void setBookingDetailId(String bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
	}

	@OneToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking_id", referencedColumnName = "bookingId", nullable = true)
	@JsonIgnore
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Column(name = "price")
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "discount")
	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	@Column(name = "status")
	public BookingState getStatus() {
		return status;
	}

	public void setStatus(BookingState status) {
		this.status = status;
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
				ToStringStyle.DEFAULT_STYLE).append("bookingDetailId",
				this.bookingDetailId);
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BookingDetail)) {
			return false;
		}

		final BookingDetail bookingDetail = (BookingDetail) o;

		return !(bookingDetailId != null ? !bookingDetailId
				.equals(bookingDetail.getBookingDetailId()) : bookingDetail
				.getBookingDetailId() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (bookingDetailId != null ? bookingDetailId.hashCode() : 0);
	}
}
