package com.scaler.bookmyshowsept23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{ //Ticket
    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;

    private int amount;

    @OneToMany
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;


    /*
        1   -   1
    Booking - User
         m   -    1

        1   -   m
    Booking - ShowSeat
        m   -    1

        1   -   m
    Booking - Payment
         1   -    1
     */


}
