package pojos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseDataPojo {

    private Integer bookingid;
    private BookingDataPojo booking;

    public BookingResponseDataPojo() {
    }

    public BookingResponseDataPojo(Integer bookingid, BookingDataPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingDataPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingDataPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponseDataPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
