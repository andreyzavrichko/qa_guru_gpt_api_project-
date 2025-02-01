package com.booker.models.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBookingResponse {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

    @Data
    public static class Bookingdates{
        private String checkin;
        private String checkout;
    }
}
