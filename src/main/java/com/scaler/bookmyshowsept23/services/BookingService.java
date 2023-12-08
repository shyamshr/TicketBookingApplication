package com.scaler.bookmyshowsept23.services;

import com.scaler.bookmyshowsept23.exceptions.ShowNotFoundException;
import com.scaler.bookmyshowsept23.exceptions.ShowSeatNotAvailableException;
import com.scaler.bookmyshowsept23.exceptions.UserNotFoundException;
import com.scaler.bookmyshowsept23.models.*;
import com.scaler.bookmyshowsept23.repositories.BookingRepository;
import com.scaler.bookmyshowsept23.repositories.ShowRepository;
import com.scaler.bookmyshowsept23.repositories.ShowSeatRepository;
import com.scaler.bookmyshowsept23.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository, ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,PriceCalculatorService priceCalculatorService,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userID, Long showID, List<Long> showSeatIDs ){
    //--------------------------Take a Lock----------------------------------------------
        //1.Get User from userID in DB.
        //2.Get Show from showID in DB.
        //3.Get List of showSeats from showSeatIDs in DB.
        //4.Check status of all the showSeats.
        //5.If all of them are not available, throw an exception
        //6.If all of them are available, change the status to locked for showSeat objects.
        //7.Change the status in DB as well.
        //8.Create booking object and store in DB.
    //--------------------------Release the Lock-------------------------------------------

        //1.Get User from userID in DB.
        Optional<User> optionalUser = userRepository.findById(userID);
        if(optionalUser.isEmpty())
            throw new UserNotFoundException("User: "+ userID+" is not valid");
        User bookedBy = optionalUser.get();

        //2.Get Show from showID in DB.
        Optional<Show> optionalShow = showRepository.findById(showID);
        if(optionalShow.isEmpty())
            throw new ShowNotFoundException("Show: "+ showID+" is not found");
        Show show = optionalShow.get();

        //3.Get List of showSeats from showSeatIDs in DB.
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIDs);

        //4.Check status of all the showSeats.
        //5.If all of them are not available, throw an exception
        for(ShowSeat showSeat:showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))
                throw new ShowSeatNotAvailableException("Show seat is not available");
        }

        //6.If all of them are available, change the status to locked for showSeat objects.
        List<ShowSeat> bookedSeats = new ArrayList<>();
        for(ShowSeat showSeat:showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            //7.Change the status in DB as well.
            bookedSeats.add(showSeatRepository.save(showSeat));
        }
        //8.Create booking object and store in DB.
        Booking booking = new Booking();
        booking.setUser(bookedBy);
        booking.setShowSeats(bookedSeats);
        booking.setCreatedAt(new Date());
        booking.setLastModifiedAt(new Date());
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setPayments(new ArrayList<>());
        booking.setAmount(priceCalculatorService.calculateBookingPrice(bookedSeats,show));
        return bookingRepository.save(booking);
    }
}
