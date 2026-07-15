package com.abhyasika.service;

import java.util.List;

import com.abhyasika.dto.RoomDTO;

public interface RoomService {

    RoomDTO addRoom(RoomDTO roomDTO);

    List<RoomDTO> getAllRooms();

    RoomDTO getRoomById(Long id);

    RoomDTO updateRoom(Long id, RoomDTO roomDTO);

    void deleteRoom(Long id);
}