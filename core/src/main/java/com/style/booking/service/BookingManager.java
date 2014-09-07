package com.style.booking.service;

import java.util.ArrayList;
import java.util.List;

import com.style.exception.AppException;
import com.style.model.Booking;
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
	 * @param priceIds
	 * @return
	 */
	Booking saveBooking(List<String> priceIds) throws AppException;

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

}
