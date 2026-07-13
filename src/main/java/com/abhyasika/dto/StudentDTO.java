package com.abhyasika.dto;


import com.abhyasika.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String fullName;

    private String mobile;

    private String email;

    private Gender gender;

    private String address;
}

