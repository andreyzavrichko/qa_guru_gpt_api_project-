package com.booker.models.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostBookingResponse {
    private Integer bookingid;
    private Booking booking;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Booking {
        private String firstname;
        private String lastname;
        private Integer totalprice;
        private Boolean depositpaid;
        private Bookingdates bookingdates;
        private String additionalneeds;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Bookingdates {
        private String checkin;
        private String checkout;
    }
}
