package com.style.booking.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.style.booking.dao.BookingDao;
import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.exception.AppException;
import com.style.model.Booking;

/**
 * This class interacts with Hibernate session to save/delete and retrieve
 * Booking objects.
 * 
 * @author mathi
 */
@Repository("bookingDao")
public class BookingDaoHibernate extends GenericDaoHibernate<Booking, String>
		implements BookingDao {

	public BookingDaoHibernate() {
		super(Booking.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Booking> getBookings(String[] bookingIds) {
		try {
			List<Booking> bookingList = (List<Booking>) getSession()
					.createQuery(
							"select booking from com.style.model.Booking as booking where booking.id in (:bookingIds)")
					.setParameterList("bookingIds", bookingIds).list();
			return bookingList;
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Booking saveBooking(Booking booking) {
		try {
			return (Booking) getSession().merge(booking);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Booking> getBookings() {
		try {
			log.debug("Retrieving all booking names...");
			return getSession().createCriteria(Booking.class).list();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Booking getBooking(String id) {
		try {
			List<Booking> bookings = getSession().createCriteria(Booking.class)
					.add(Restrictions.eq("id", id)).list();
			if (bookings.isEmpty()) {
				return null;
			} else {
				return bookings.get(0);
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

}
