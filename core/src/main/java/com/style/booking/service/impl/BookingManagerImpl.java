package com.style.booking.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.style.booking.dao.BookingDao;
import com.style.booking.service.BookingManager;
import com.style.exception.AppException;
import com.style.model.Booking;
import com.style.model.BookingDetail;
import com.style.model.Product;
import com.style.model.ProductPrice;
import com.style.model.User;
import com.style.product.service.ProductManager;
import com.style.service.UserManager;
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

    private ProductManager productManager;

    private UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }


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
	public Booking saveBooking(List<String> priceIds) throws AppException {
        try {
            List<ProductPrice> prices = productManager.getPrices(priceIds);
            Booking booking = new Booking();
            List<BookingDetail> bookingDetails = new ArrayList<BookingDetail>();
            int totalPrice = 0;
            int totalDsicount = 0;
            for (ProductPrice price : prices) {
                BookingDetail detail = new BookingDetail();
                detail.setProduct(price.getProduct());
                detail.setStatus("open");
                detail.setBooking(booking);
                detail.setPrice(price.getPrice());
                detail.setBranch(price.getBranch());
                totalPrice = totalPrice + price.getPrice();
                detail.setDiscount(0);
                Calendar now = new GregorianCalendar();
                log.info("time zone "+now.getTimeZone());
                detail.setStartTime(now);
                Calendar end = now;
                end.add(Calendar.HOUR_OF_DAY, 5);
                end.add(Calendar.MINUTE, Integer.parseInt(price.getExpectedTime()));
                detail.setEndTime(now);
                totalDsicount = totalDsicount + detail.getDiscount();
                bookingDetails.add(detail);

            }
            booking.setBookingDetails(bookingDetails);
            booking.setStatus("open");
            booking.setTotalDiscountPrice(totalDsicount);
            booking.setTotalPrice(totalPrice);
            booking.setNetPrice(totalPrice - totalDsicount);
            booking.setUser((User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
            return bookingDao.saveBooking(booking);
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        return null;
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

	/**
	 * {@inheritDoc}
	 */
	public List<BookingDetail> getBookingDetails(Calendar startTime) throws AppException{
		return bookingDao.getBookingDetails(startTime);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> getAvailableTime(Product product,
			Calendar bookingDate) {
		List<ProductPrice> prices = product.getProductPrices();
		Map<String, List<String>> branchAvailableTimes = new HashMap<String, List<String>>();
		Calendar endDate = new GregorianCalendar();
		int year = bookingDate.get(Calendar.YEAR);
		int month = bookingDate.get(Calendar.MONTH);
		int day = bookingDate.get(Calendar.DATE);
		endDate.set(year, month, day, 23, 59, 59);
		List<BookingDetail> bookingDetails = getBookingDetails(bookingDate);
		for (ProductPrice price : prices) {
			List<String> availTimes = new ArrayList<String>();
			if(price.getBranch().getAvailableTimes() == null){
				price.getBranch().setAvailableTime(getTimeIntervals(
						(bookingDate.get(Calendar.HOUR_OF_DAY) * 60)
						+ +bookingDate.get(Calendar.MINUTE),
				(endDate.get(Calendar.HOUR_OF_DAY) * 60)
						+ +endDate.get(Calendar.MINUTE),
				Integer.parseInt(price.getExpectedTime())));
			}
			for (BookingDetail bookingDetail : bookingDetails) {
				if (bookingDetail.getBranch().getId()
						.equals(price.getBranch().getId())) {
					int start = bookingDate.get(Calendar.MINUTE)
							+ (bookingDate.get(Calendar.HOUR_OF_DAY) * 60);
					int end = endDate.get(Calendar.MINUTE)
							+ (endDate.get(Calendar.HOUR_OF_DAY) * 60);
					for (int i = start; i < end; i = i
							+ Integer.parseInt(price.getExpectedTime())) {
						int existingBookStart = bookingDetail.getStartTime()
								.get(Calendar.MINUTE)
								+ (bookingDetail.getStartTime().get(
										Calendar.HOUR_OF_DAY) * 60);
						int existingBookEnd = bookingDetail.getEndTime().get(
								Calendar.MINUTE)
								+ (bookingDetail.getEndTime().get(
										Calendar.HOUR_OF_DAY) * 60);
						for (int j = existingBookStart; j < existingBookEnd; j = j
								+ Integer.parseInt(price.getExpectedTime())) {
							if (j >= i) {
								int hours = j / 60;
								int minutes = j % 60;
								String time = hours + ":" + minutes;
								price.getBranch().getAvailableTimes().remove(time);
							}
						}

					}
				}
			}
			branchAvailableTimes.put(price.getBranch().getId(),
					availTimes);
		}

		return branchAvailableTimes;
	}
	
	/**
	 * get time intervals
	 * 
	 * @param startTime
	 * @param endTime
	 * @param intervel
	 * @return
	 */
	private List<String> getTimeIntervals(int startTime, int endTime, int interval){
		List<String> timeIntervals = new ArrayList<String>();
		for(int i = startTime; i < endTime; i = i+interval) {
			int hours = i / 60;
			int minutes = i % 60;
			String time = hours + ":" + minutes;
			timeIntervals.add(time);
		}
		return timeIntervals;
	}
}
