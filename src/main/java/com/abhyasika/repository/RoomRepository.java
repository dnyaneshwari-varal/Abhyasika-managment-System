package com.abhyasika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByRoomName(String roomName);

}