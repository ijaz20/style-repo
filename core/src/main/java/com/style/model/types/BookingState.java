package com.style.model.types;

/**
 * Created by ijaz on 26/10/14.
 */
public enum BookingState {
    OPEN("Open"),
    PENDING("Pending"),
    PAYMENT_INITIATED("Payment Initiated"),
    CANCELLED("Cancelled"),
    CLOSED("Closed"),
    PAYMENT_SUCCESS("Payment Success");

    private final String value;
    BookingState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
