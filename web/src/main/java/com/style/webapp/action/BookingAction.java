package com.style.webapp.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.style.Constants;
import com.style.exception.AppException;
import com.style.util.CommonUtil;
import com.style.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.Preparable;
import com.style.booking.service.BookingManager;
import com.style.model.Booking;
import com.style.model.Branch;
import com.style.model.Product;
import com.style.model.ProductPrice;
import com.style.model.User;

/**
 * Action for facilitating booking Management feature.
 * 
 * @auther mathi
 */
public class BookingAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 6776558938712115191L;
	private BookingManager bookingManager;
	private Branch branch;
	private Product product;
	private String branchId;
	private String productId;
	private String id;
	private Booking booking;
	private List<Booking> bookings;
	private Calendar bookingDate;
	private Calendar startTime;
	private Calendar endTime;
	private String timeStart;
	private String bookingDateString;
	private List<ProductPrice> branchProductPrices;
	private List<String> branchAvailableTimes;
    private String offerId;
    private String bookingId;
	@Autowired
	public void setBookingManager(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}

	public String saveBooking() {
        if(StringUtil.isEmptyString(offerId)){
            return ERROR;
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        bookingDate  = new GregorianCalendar();
        try {
			bookingDate.setTime(df.parse(bookingDateString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        bookingDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStart.split(":")[0]));
        bookingDate.set(Calendar.MINUTE, Integer.parseInt(timeStart.split(":")[1]));
        booking = bookingManager.saveBooking(offerId, bookingId, bookingDate);
		return SUCCESS;
	}

	/**
	 * show branch booking
	 * 
	 * @return
	 */
	public String showBranchBooking(){
		if (!CommonUtil.isAnonymous()) {
			User branchUser = (User) (SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal());
			branchProductPrices = bookingManager.getBranchProductPrices(branchUser.getUserBranch().getId());
		}
		booking = new Booking();
		return Constants.SUCCESS;
	}
	
	
	public List<String> getBranchProductAvailableTimes(){
		if (!CommonUtil.isAnonymous()) {
			User branchUser = (User) (SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal());
			Calendar now = new GregorianCalendar();
			/*if (bookingDate.get(Calendar.DAY_OF_MONTH) == now
					.get(Calendar.DAY_OF_MONTH)) {
				bookingDate.set(Calendar.HOUR_OF_DAY,
						now.get(Calendar.HOUR_OF_DAY) + 2);
			}*/
			return branchAvailableTimes = bookingManager
					.getBranchProductAvailableTimes(branchUser.getUserBranch(),
							bookingManager.getProduct(productId),
							new GregorianCalendar());
		} else {
			return new ArrayList<String>();
		}
		
	}
	
	/**
	 * show branch booking schedules
	 * 
	 * @return
	 */
	public String showSchedules(){
		if (!CommonUtil.isAnonymous()) {
			User branchUser = (User) (SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal());
			bookings = bookingManager.getBranchBookingSchedules(branchUser.getUserBranch().getId());
		}
		return Constants.SUCCESS;
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<ProductPrice> getBranchProductPrices() {
		return branchProductPrices;
	}

	public void setBranchProductPrices(List<ProductPrice> branchProductPrices) {
		this.branchProductPrices = branchProductPrices;
	}

	public List<String> getBranchAvailableTimes() {
		return branchAvailableTimes;
	}

	public void setBranchAvailableTimes(List<String> branchAvailableTimes) {
		this.branchAvailableTimes = branchAvailableTimes;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Calendar getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Calendar bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public BookingManager getBookingManager() {
		return bookingManager;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getBookingDateString() {
		return bookingDateString;
	}

	public void setBookingDateString(String bookingDateString) {
		this.bookingDateString = bookingDateString;
	}

}
