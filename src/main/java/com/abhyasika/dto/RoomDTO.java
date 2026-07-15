package com.abhyasika.dto;

import com.abhyasika.enums.RoomType;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    @NotBlank(message = "Room Name is required")
    private String roomName;

    @NotNull(message = "Room Type is required")
    private RoomType roomType;
    
    @NotNull(message = "Seat Count is required")
    private Integer seatCount;

    @NotBlank(message = "Description is required")
    private String description;
}