package com.style.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.booking.dao.BookingDao;
import com.style.booking.service.BookingManager;
import com.style.exception.AppException;
import com.style.model.Booking;
import com.style.service.impl.GenericManagerImpl;

/**
 * Implementation of BookingManager interface.
 * 
 * @author mathi
 */
@Service("bookingManager")
public class BookingManagerImpl extends GenericManagerImpl<Booking, String>
		implements BookingManager {

	private BookingDao bookingDao;

	@Autowired
	public BookingManagerImpl(BookingDao bookingDao) {
		super(bookingDao);
		this.bookingDao = bookingDao;
	}

	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.dao = bookingDao;
		this.bookingDao = bookingDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Booking saveBooking(Booking booking) throws AppException {
		return bookingDao.saveBooking(booking);
	}

	public List<Booking> getBookings(String[] bookingIds) throws AppException {
		return bookingDao.getBookings(bookingIds);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Booking> getBookings() throws AppException {
		return bookingDao.getBookings();
	}

	/**
	 * {@inheritDoc}
	 */
	public Booking getBooking(String id) throws AppException {
		return bookingDao.getBooking(id);
	}

}
