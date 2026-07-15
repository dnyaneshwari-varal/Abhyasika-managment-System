package com.abhyasika.dto;

import com.abhyasika.enums.SeatStatus;

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
public class SeatDTO {

    @NotBlank(message = "Seat Number is required")
    private String seatNumber;

    @NotNull(message = "Seat Status is required")
    private SeatStatus seatStatus;

    @NotNull(message = "Room Id is required")
    private Long roomId;
}