package com.abhyasika.service;

import java.util.List;

import com.abhyasika.dto.SeatDTO;

public interface SeatService {

    SeatDTO addSeat(SeatDTO seatDTO);

    List<SeatDTO> getAllSeats();

    SeatDTO getSeatById(Long id);

    SeatDTO updateSeat(Long id, SeatDTO seatDTO);

    void deleteSeat(Long id);
}