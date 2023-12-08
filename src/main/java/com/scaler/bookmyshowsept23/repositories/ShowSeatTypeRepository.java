package com.scaler.bookmyshowsept23.repositories;

import com.scaler.bookmyshowsept23.models.Show;
import com.scaler.bookmyshowsept23.models.ShowSeatType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {
    @Override
    Optional<ShowSeatType> findById(Long aLong);

    List<ShowSeatType> findAllByShow(Long showId);
}
