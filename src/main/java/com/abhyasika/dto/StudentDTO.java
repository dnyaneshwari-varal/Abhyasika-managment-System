package com.abhyasika.dto;


import com.abhyasika.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	
	    private String studentId;

	    @NotBlank(message = "Full Name is required")
	    private String fullName;

	    @NotBlank(message = "Mobile Number is required")
	    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Mobile Number")
	    private String mobile;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid Email Format")
	    private String email;

	    private Gender gender;

	    @NotBlank(message = "Address is required")
	    private String address;
	    
	    @NotBlank(message = "Password is required")
	    @Pattern(
	        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$",
	        message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number, and one special character"
	    )
	    private String password;
	    
	    @NotBlank(message = "Room Code is required")
	    private String roomCode;

	    @NotBlank(message = "Seat Number is required")
	    private String seatNumber;
}

