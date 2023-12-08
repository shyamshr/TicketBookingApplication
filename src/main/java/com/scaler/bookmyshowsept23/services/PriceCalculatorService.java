package com.scaler.bookmyshowsept23.services;

import com.scaler.bookmyshowsept23.models.SeatType;
import com.scaler.bookmyshowsept23.models.Show;
import com.scaler.bookmyshowsept23.models.ShowSeat;
import com.scaler.bookmyshowsept23.models.ShowSeatType;
import com.scaler.bookmyshowsept23.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    private int amount;
    public int calculateBookingPrice(List<ShowSeat> showSeats, Show show){
        HashMap<SeatType,Integer> showSeatTypePriceMap = new HashMap<>();
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show.getId());
        for(ShowSeatType showSeatType:showSeatTypes){
            showSeatTypePriceMap.put(showSeatType.getSeatType(),showSeatType.getPrice());
        }
        for(ShowSeat showSeat:showSeats){
            amount+=showSeatTypePriceMap.get(showSeat.getSeat().getSeatType());
        }
        return amount;
    }
}
