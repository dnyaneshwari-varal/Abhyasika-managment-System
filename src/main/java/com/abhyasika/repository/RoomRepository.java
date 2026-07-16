package com.abhyasika.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	boolean existsByRoomName(String roomName);

    boolean existsByRoomCode(String roomCode);

    Optional<Room> findByRoomCode(String roomCode);

}