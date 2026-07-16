package com.abhyasika.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Notice;
import com.abhyasika.enums.NoticeStatus;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByNoticeStatus(NoticeStatus noticeStatus);

    long countByNoticeStatus(NoticeStatus noticeStatus);

    List<Notice> findByNoticeStatusAndExpiryDateBefore(
            NoticeStatus noticeStatus,
            LocalDate expiryDate);

}

/*
 If an interviewer asks:

Why did you use findByNoticeStatusAndExpiryDateBefore() instead of findAll()?

You can answer:

"Using findAll() loads every notice into memory and then filters them in Java. With findByNoticeStatusAndExpiryDateBefore(), the database returns only the notices that are ACTIVE and already expired. This reduces unnecessary processing and is more efficient."
 */
