package com.style.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

/**
 * This class represents the basic "Payment" object in VSU that allows for
 * managing payment
 * 
 * @author mathi
 */
public class Payment extends BaseObject {
	
	private static final long serialVersionUID = 1L;
	private String paymentId;
	private String paymentType;
	private Calendar paymentDate;
	private Booking booking;
	private String referenceId;
	private String status;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@Column( name="payment_type")
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column( name="payment_date")
	public Calendar getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column( name="booking_id")
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Column( name="reference_id")
	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	@Column( name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE)
				.append("paymentId", this.paymentId);
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Payment)) {
			return false;
		}

		final Payment payment = (Payment) o;

		return !(paymentId != null ? !paymentId.equals(payment.getPaymentId())
				: payment.getPaymentId() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (paymentId != null ? paymentId.hashCode() : 0);
	}

}
