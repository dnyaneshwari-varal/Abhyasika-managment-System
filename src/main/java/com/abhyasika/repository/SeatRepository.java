package com.abhyasika.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    boolean existsBySeatNumber(String seatNumber);
    Optional<Seat> findBySeatNumber(String seatNumber);

}