package com.style.webapp.action;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.style.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.style.booking.service.BookingManager;
import com.style.model.Booking;
import com.style.model.Branch;
import com.style.model.Product;

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

    private String priceIdList;
	@Autowired
	public void setBookingManager(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}

	public String saveBooking() {
        if(StringUtil.isEmptyString(priceIdList)){
            return ERROR;
        }
        List<String> priceIds = Arrays.asList(priceIdList.split(","));
        bookingManager.saveBooking(priceIds);
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

    public String getPriceIdList() {
        return priceIdList;
    }

    public void setPriceIdList(String priceIdList) {
        this.priceIdList = priceIdList;
    }

    public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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

}
