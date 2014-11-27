package com.style.booking.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.style.booking.dao.BookingDao;
import com.style.booking.service.BookingManager;
import com.style.exception.AppException;
import com.style.model.Booking;
import com.style.model.BookingDetail;
import com.style.model.Branch;
import com.style.model.Product;
import com.style.model.ProductPrice;
import com.style.model.User;
import com.style.model.types.BookingState;
import com.style.product.service.ProductManager;
import com.style.service.UserManager;
import com.style.service.impl.GenericManagerImpl;
import com.style.util.CommonUtil;
import com.style.util.StringUtil;

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
	public Booking saveBooking(String priceId, String bookingId, Calendar bookingDate)
			throws AppException {
		try {
			ProductPrice price = productManager.getPrice(priceId);
			Booking booking = new Booking();
			List<BookingDetail> bookingDetails = new ArrayList<BookingDetail>();
			BookingDetail detail = new BookingDetail();
			int totalPrice = 0;
			int totalDsicount = 0;
			if (price != null) {
				detail.setProduct(price.getProduct());
				detail.setStatus(BookingState.OPEN);
				detail.setBooking(booking);
				detail.setPrice(price.getPrice());
				detail.setBranch(price.getBranch());
				totalPrice = totalPrice + price.getPrice();
				detail.setDiscount(0);
				Calendar now = new GregorianCalendar();
				log.info("time zone " + now.getTimeZone());
				detail.setStartTime(bookingDate);
				Calendar end = bookingDate;
				end.add(Calendar.MINUTE,
						Integer.parseInt(price.getExpectedTime()));
				detail.setEndTime(now);
				totalDsicount = totalDsicount + detail.getDiscount();
				bookingDetails.add(detail);
			}
			String browserId = CommonUtil.getBrowserId();
			log.info("browser Id :: " + browserId);
			booking.setBrowserId(browserId);
			if (!StringUtil.isEmptyString(bookingId)) {
				booking = getBookingById(bookingId);
				detail.setBooking(booking);
				booking.getBookingDetails().add(detail);
				return bookingDao.saveBooking(booking);
			} else {
				booking.setBookingDetails(bookingDetails);
				booking.setStatus("open");
				booking.setTotalDiscountPrice(totalDsicount);
				booking.setTotalPrice(totalPrice);
				booking.setNetPrice(totalPrice - totalDsicount);
				if (!CommonUtil.isAnonymous()) {
					booking.setUser((User) (SecurityContextHolder.getContext()
							.getAuthentication().getPrincipal()));
				}
				return bookingDao.saveBooking(booking);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Booking getBookingById(String bookingId) {
		log.info("getting booking details");
		if (!StringUtil.isEmptyString(bookingId)) {
			try {
				return get(bookingId);
			} catch (AppException e) {
				log.error(e.getMessage(), e);
			}
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
	public List<BookingDetail> getBookingDetails(Calendar startTime)
			throws AppException {
		return bookingDao.getBookingDetails(startTime);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BookingDetail> getBookingDetails(Calendar startTime,
			Calendar endTime) throws AppException {
		return bookingDao.getBookingDetails(startTime, endTime);
	}

	/**
	 * {@inheritDoc}
	 */
	public Product getAvailableTime(Product product, Calendar bookingDate) {
		Calendar endDate = new GregorianCalendar();
		int hours = bookingDate.get(Calendar.HOUR_OF_DAY);
		int minutes = bookingDate.get(Calendar.MINUTE);
		int year = bookingDate.get(Calendar.YEAR);
		int month = bookingDate.get(Calendar.MONTH);
		int day = bookingDate.get(Calendar.DATE);
		endDate.set(year, month, day, 23, 59, 59);
		//get booking details of booking date
		List<BookingDetail> bookingDetails = getBookingDetails(bookingDate);
		//iterate product price to get each branch available time
		for (int k = 0; k < product.getProductPrices().size(); k++) {
			ProductPrice price = product.getProductPrices().get(k);
			List<String> availTimes = new ArrayList<String>();
			//get all product time interval's
			if (price.getBranch().getAvailableTimes() == null) {
				availTimes = getBranchTimes(price.getBranch(),
						Integer.parseInt(price.getExpectedTime()), (hours * 60)
								+ minutes);
			}
			//iterate booking details to remove time interval from branch product time interval 
			for (BookingDetail bookingDetail : bookingDetails) {
				//check the booking details branch and price branch
				if (bookingDetail.getBranch().getId()
						.equals(price.getBranch().getId())) {
					int start = (hours * 60) + minutes;
					int end = endDate.get(Calendar.MINUTE)
							+ (endDate.get(Calendar.HOUR_OF_DAY) * 60);
					//iterate every product intervals to match booking detail interval
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
						//iterate booking detail interval and remove the interval from branch product time interval
						for (int j = existingBookStart; j < existingBookEnd; j = j
								+ Integer.parseInt(price.getExpectedTime())) {
							//check the booking start time greater or equal to already booked interval
							if (i >= j
									&& i <= (j + Integer.parseInt(price
											.getExpectedTime()))) {
								// check the already booked interval is in between branch product interval 
								int key = j;
								if ((j % Integer.parseInt(price
										.getExpectedTime())) != 0) {
									key = (j / Integer.parseInt(price
											.getExpectedTime()))
											* Integer.parseInt(price
													.getExpectedTime());
								}
								
								//book resource in that interval
								if (price.getBranch().getAvailableResource()
										.get(key) == null) {
									price.getBranch().getAvailableResource()
											.put(key, 1);
								} else {
									price.getBranch()
											.getAvailableResource()
											.put(key,
													price.getBranch()
															.getAvailableResource()
															.get(key) + 1);
								}
								//after book last resource remove the interval from the product branch interval to intimate time not available 
								if (price.getBranch().getAvailableResource()
										.get(key) == price.getBranch()
										.getNoOfResource()) {
									int mins = 0;
									int hrs = j / 60;
									if ((j % Integer.parseInt(price
											.getExpectedTime())) == 0) {
										mins = j % 60;
									}
									String time = hrs + ":" + mins;
									availTimes.remove(time);
								}

							}
						}
					}
				}
			}
			product.getProductPrices().get(k).getBranch()
					.setAvailableTimes(availTimes);
		}
		return product;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public Booking getBooking(User user) {
		return bookingDao.getBooking(user);
	}

	/**
	 * get available times of branch
	 * 
	 * @param branch
	 * @param productTime
	 * @return
	 */
	private List<String> getBranchTimes(Branch branch,
			int productEstimatedTime, int fromTime) {
		if (!StringUtil.isEmptyString(branch.getOpenTime())
				&& !StringUtil.isEmptyString(branch.getCloseTime())) {
			int openTime = (Integer
					.parseInt(branch.getOpenTime().split(":")[0]) * 60)
					+ Integer.parseInt(branch.getOpenTime().split(":")[1]);
			int closeTime = (Integer
					.parseInt(branch.getCloseTime().split(":")[0]) * 60)
					+ Integer.parseInt(branch.getCloseTime().split(":")[1]);
			return getTimeIntervals(openTime, closeTime, productEstimatedTime,
					fromTime);
		} else {
			return new ArrayList<String>();
		}
	}

	/**
	 * get time intervals
	 * 
	 * @param startTime
	 * @param endTime
	 * @param intervel
	 * @return
	 */
	private List<String> getTimeIntervals(int openTime, int closeTime,
			int interval, int fromTime) {
		List<String> timeIntervals = new ArrayList<String>();
		for (int i = openTime; i < closeTime; i = i + interval) {
			if ((i > fromTime) && ((i + interval) <= closeTime)) {
				int hours = i / 60;
				int minutes = i % 60;
				String time = hours + ":" + minutes;
				timeIntervals.add(time);
			}
		}
		return timeIntervals;
	}
}
