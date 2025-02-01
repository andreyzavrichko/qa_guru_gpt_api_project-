package com.booker.models.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequest {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

    @Data
    @Builder
    public static class Bookingdates{
        private String checkin;
        private String checkout;
    }
}
