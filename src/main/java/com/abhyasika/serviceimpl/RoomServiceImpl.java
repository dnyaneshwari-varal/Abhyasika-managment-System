package com.abhyasika.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abhyasika.dto.RoomDTO;
import com.abhyasika.entity.Room;
import com.abhyasika.entity.Seat;
import com.abhyasika.enums.RoomType;
import com.abhyasika.enums.SeatStatus;
import com.abhyasika.repository.RoomRepository;
import com.abhyasika.repository.SeatRepository;
import com.abhyasika.service.RoomService;
@Service

public class RoomServiceImpl implements RoomService{
	
	
	private final RoomRepository roomRepository;
	private final SeatRepository seatRepository;
	
	
	public RoomServiceImpl(RoomRepository roomRepository,
            SeatRepository seatRepository) {

			this.roomRepository = roomRepository;
			this.seatRepository = seatRepository;
	}

	@Override
	public RoomDTO addRoom(RoomDTO roomDTO) {
		
		

	    // Check if room already exists
	    if (roomRepository.existsByRoomName(roomDTO.getRoomName())) {
	        throw new RuntimeException("Room already exists");
	    }

	    // DTO -> Entity
	    Room room = new Room();

	    
	    room.setRoomName(roomDTO.getRoomName());
	    room.setRoomType(roomDTO.getRoomType());
	    room.setRoomCode(roomDTO.getRoomCode());
	    room.setSeatCount(roomDTO.getSeatCount());
	    room.setDescription(roomDTO.getDescription());
	    room.setMonthlyFee(roomDTO.getMonthlyFee());

	    // Save in Database
	    Room savedRoom = roomRepository.save(room);

	    String prefix;

	    if (savedRoom.getRoomType() == RoomType.AC) {
	        prefix = "A";
	    } else {
	        prefix = "B";
	    }
	    
	    for (int i = 1; i <= savedRoom.getSeatCount(); i++) {

	        Seat seat = new Seat();

//	        seat.setSeatNumber("R" + savedRoom.getId() + "-" + prefix + String.format("%02d", i));
	        seat.setSeatNumber(savedRoom.getRoomCode() + "-" + prefix + String.format("%02d", i));
	        seat.setSeatStatus(SeatStatus.AVAILABLE);

	        seat.setRoom(savedRoom);

	        seatRepository.save(seat);
	    }
	    // Entity -> DTO
	    RoomDTO response = new RoomDTO();

	    
	    response.setRoomName(savedRoom.getRoomName());
	    response.setRoomType(savedRoom.getRoomType());
	    response.setRoomCode(savedRoom.getRoomCode());
	    response.setSeatCount(savedRoom.getSeatCount());
	    response.setDescription(savedRoom.getDescription());

	    return response;
	}

	@Override
	public List<RoomDTO> getAllRooms() {

	    List<Room> rooms = roomRepository.findAll();

	    List<RoomDTO> roomDTOList = new ArrayList<>();

	    for (Room room : rooms) {

	        RoomDTO dto = new RoomDTO();

	        dto.setRoomName(room.getRoomName());
	        dto.setRoomType(room.getRoomType());
	        dto.setSeatCount(room.getSeatCount());
	        dto.setDescription(room.getDescription());
	        dto.setMonthlyFee(room.getMonthlyFee());
	        dto.setRoomCode(room.getRoomCode());

	        roomDTOList.add(dto);
	    }

	    return roomDTOList;
	}

	@Override
	public RoomDTO getRoomById(Long id) {

	    Room room = roomRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Room not found"));

	    RoomDTO dto = new RoomDTO();

	    dto.setSeatCount(room.getSeatCount());
	    dto.setRoomName(room.getRoomName());
	    dto.setRoomType(room.getRoomType());
	    dto.setDescription(room.getDescription());

	    return dto;
	}

	@Override
	public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {

	    Room room = roomRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Room not found"));

	    room.setRoomName(roomDTO.getRoomName());
	    room.setRoomType(roomDTO.getRoomType());
	    room.setSeatCount(roomDTO.getSeatCount());
	    room.setDescription(roomDTO.getDescription());

	    Room updatedRoom = roomRepository.save(room);

	    RoomDTO response = new RoomDTO();

	    response.setSeatCount(updatedRoom.getSeatCount());
	    response.setRoomName(updatedRoom.getRoomName());
	    response.setRoomType(updatedRoom.getRoomType());
	    response.setDescription(updatedRoom.getDescription());

	    return response;
	}
	
	@Override
	public void deleteRoom(String roomCode) {

	    Room room = roomRepository.findByRoomCode(roomCode)
	            .orElseThrow(() -> new RuntimeException("Room not found"));

	    roomRepository.delete(room);
	}

	

}
