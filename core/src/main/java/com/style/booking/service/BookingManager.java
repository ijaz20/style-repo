package com.style.booking.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.style.exception.AppException;
import com.style.model.Booking;
import com.style.model.BookingDetail;
import com.style.model.Product;
import com.style.model.User;
import com.style.service.GenericManager;

/**
 * BookingManager.java Service Interface to handle communication between web and
 * persistence layer.
 * 
 * @author mathi
 */
public interface BookingManager extends GenericManager<Booking, String> {

	/**
	 * save booking
	 * 
	 * @param priceId
     * @param bookingId
	 * @return
	 */
	Booking saveBooking(String priceId, String bookingId, Calendar bookingDate) throws AppException;

    /**
     *
     * @param bookingId
     * @return
     * @throws AppException
     */
    Booking getBookingById(String bookingId) throws AppException;
	/**
	 * 
	 * @param bookingIds
	 * @return
	 */
	List<Booking> getBookings(String[] bookingIds) throws AppException;

	/**
	 * Returns all the bookings
	 * 
	 * @return
	 */
	List<Booking> getBookings() throws AppException;

	/**
	 * get booking by id
	 * 
	 * @param id
	 * @return
	 */
	Booking getBooking(String id) throws AppException;

	/**
	 * get booking details by date
	 * 
	 * @param date
	 * @return
	 */
	List<BookingDetail> getBookingDetails(Calendar startTime);
	
	/**
	 * get booking details by date
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BookingDetail> getBookingDetails(Calendar startTime, Calendar endTime);

    Booking getBooking(User user);
	/**
	 * get availabeTimes of product on branches
	 * 
	 * @param product
	 * @param bookingDate
	 * @return
	 */
	Product getAvailableTime(Product product,
			Calendar bookingDate);

}
