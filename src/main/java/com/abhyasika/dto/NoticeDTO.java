package com.abhyasika.dto;

import java.time.LocalDate;

import com.abhyasika.enums.NoticeStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeDTO {

    private Long id;

    private String title;

    private String description;

    private LocalDate noticeDate;

    private LocalDate expiryDate;

    private NoticeStatus noticeStatus;

}