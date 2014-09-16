package com.style.booking.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.style.model.BookingDetail;
import com.style.model.ProductPrice;
import com.style.model.User;
import com.style.product.service.ProductManager;
import com.style.service.UserManager;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
                now.add(Calendar.HOUR, 5);
                detail.setStartTime(now);
                now.add(Calendar.MINUTE, 30);
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

}
