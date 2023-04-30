package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePojo {


    private Integer bookingid;
    private HerOkuAppPojo booking;

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(Integer bookingid, HerOkuAppPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public HerOkuAppPojo getBooking() {
        return booking;
    }

    public void setBooking(HerOkuAppPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
