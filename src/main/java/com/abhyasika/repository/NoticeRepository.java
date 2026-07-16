package com.abhyasika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Notice;
import com.abhyasika.enums.NoticeStatus;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByNoticeStatus(NoticeStatus noticeStatus);

    long countByNoticeStatus(NoticeStatus noticeStatus);

}