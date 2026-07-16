package com.abhyasika.service;

import java.util.List;

import com.abhyasika.dto.NoticeDTO;
import com.abhyasika.dto.NoticeDashboardDTO;
import com.abhyasika.enums.NoticeStatus;

public interface NoticeService {

    NoticeDTO addNotice(NoticeDTO noticeDTO);

    List<NoticeDTO> getAllNotices();

    NoticeDTO getNoticeById(Long id);

    NoticeDTO updateNotice(Long id, NoticeDTO noticeDTO);

    void deleteNotice(Long id);

    List<NoticeDTO> getNoticesByStatus(NoticeStatus noticeStatus);

    NoticeDashboardDTO getNoticeDashboard();

}