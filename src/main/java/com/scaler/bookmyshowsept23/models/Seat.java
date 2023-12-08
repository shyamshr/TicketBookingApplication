package com.scaler.bookmyshowsept23.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String number;

    private int rowValue;
    private int colValue;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
