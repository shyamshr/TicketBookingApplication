package com.scaler.bookmyshowsept23.controller;

import com.scaler.bookmyshowsept23.dto.BookMovieRequestDto;
import com.scaler.bookmyshowsept23.dto.BookMovieResponseDto;
import com.scaler.bookmyshowsept23.models.Booking;
import com.scaler.bookmyshowsept23.models.ResponseStatus;
import com.scaler.bookmyshowsept23.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto){
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookMovie(bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowId(), bookMovieRequestDto.getShowSeatIds());

            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getAmount());
            bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (RuntimeException e){
            bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            bookMovieResponseDto.setFailureMessage(e.getMessage());
        }

        return bookMovieResponseDto;
    }
}
