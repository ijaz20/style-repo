package com.style.booking.dao;

import java.util.Calendar;
import java.util.List;

import com.style.dao.GenericDao;
import com.style.model.Booking;
import com.style.model.Booking;
import com.style.model.BookingDetail;
import com.style.model.User;

/**
 * Booking Data Access Object (GenericDao) interface.
 * 
 * @author mathi
 */
public interface BookingDao extends GenericDao<Booking, String> {

	/**
	 * save booking
	 * 
	 * @param booking
	 * @return
	 */
	Booking saveBooking(Booking booking);

	/**
	 * 
	 * @param bookingIds
	 * @return
	 */
	List<Booking> getBookings(String[] bookingIds);

	/**
	 * Returns all the bookings
	 * 
	 * @return
	 */
	List<Booking> getBookings();

	/**
	 * get booking by id
	 * 
	 * @param id
	 * @return
	 */
	Booking getBooking(String id);
	
	/**
	 * get booking details by date
	 * 
	 * @param date
	 * @return
	 */
	List<BookingDetail> getBookingDetails(Calendar startTime);

    /**
     *
     * @param user
     * @return
     */
    Booking getBooking(User user);


	/**
	 * get booking details by date
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BookingDetail> getBookingDetails(Calendar startTime, Calendar endTime);
	
	/**
	 * get branch booking schedules
	 * 
	 * @param branchId
	 * @return
	 */
	List<Booking> getBranchBookingSchedules(String branchId);
	
	/**
	 * get branch booking details
	 * 
	 * @param branchId
	 * @param startTime
	 * @return
	 */
	List<BookingDetail> getBranchBookingDetails(String branchId, Calendar startTime);
}
