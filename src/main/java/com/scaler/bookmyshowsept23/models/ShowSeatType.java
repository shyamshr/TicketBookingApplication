package com.scaler.bookmyshowsept23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends  BaseModel{
    @ManyToOne
    private Show show;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    private int price;

    /*
        1   -   1
    ShowSeatType - show
         m   -    1
     */
}
