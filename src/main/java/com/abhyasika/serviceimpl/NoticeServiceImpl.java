package com.abhyasika.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhyasika.dto.NoticeDTO;
import com.abhyasika.dto.NoticeDashboardDTO;
import com.abhyasika.entity.Notice;
import com.abhyasika.enums.NoticeStatus;
import com.abhyasika.repository.NoticeRepository;
import com.abhyasika.service.NoticeService;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public NoticeDTO addNotice(NoticeDTO noticeDTO) {

        Notice notice = new Notice();

        notice.setTitle(noticeDTO.getTitle());
        notice.setDescription(noticeDTO.getDescription());

        // Auto Set
        notice.setNoticeDate(LocalDate.now());
        notice.setExpiryDate(noticeDTO.getExpiryDate());
        if (noticeDTO.getExpiryDate().isBefore(LocalDate.now())) {
            notice.setNoticeStatus(NoticeStatus.EXPIRED);
        } else {
            notice.setNoticeStatus(NoticeStatus.ACTIVE);
        }

        

        Notice savedNotice = noticeRepository.save(notice);
        
        
        NoticeDTO response = new NoticeDTO();

        response.setId(savedNotice.getId());
        response.setTitle(savedNotice.getTitle());
        response.setDescription(savedNotice.getDescription());
        response.setNoticeDate(savedNotice.getNoticeDate());
        response.setExpiryDate(savedNotice.getExpiryDate());
        response.setNoticeStatus(savedNotice.getNoticeStatus());

        return response;
    }
    

    @Override
    public List<NoticeDTO> getAllNotices() {

        List<Notice> noticeList = noticeRepository.findAll();

        List<NoticeDTO> response = new ArrayList<>();

        for (Notice notice : noticeList) {

            NoticeDTO dto = new NoticeDTO();

            dto.setId(notice.getId());
            dto.setTitle(notice.getTitle());
            dto.setDescription(notice.getDescription());
            dto.setNoticeDate(notice.getNoticeDate());
            dto.setExpiryDate(notice.getExpiryDate());
            dto.setNoticeStatus(notice.getNoticeStatus());

            response.add(dto);
        }

        return response;
    }

    @Override
    public NoticeDTO getNoticeById(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        NoticeDTO dto = new NoticeDTO();

        dto.setId(notice.getId());
        dto.setTitle(notice.getTitle());
        dto.setDescription(notice.getDescription());
        dto.setNoticeDate(notice.getNoticeDate());
        dto.setExpiryDate(notice.getExpiryDate());
        dto.setNoticeStatus(notice.getNoticeStatus());

        return dto;
    }
    

    @Override
    public NoticeDTO updateNotice(Long id, NoticeDTO noticeDTO) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        notice.setTitle(noticeDTO.getTitle());
        notice.setDescription(noticeDTO.getDescription());
        notice.setExpiryDate(noticeDTO.getExpiryDate());

        if (notice.getExpiryDate().isBefore(LocalDate.now())) {
            notice.setNoticeStatus(NoticeStatus.EXPIRED);
        } else {
            notice.setNoticeStatus(NoticeStatus.ACTIVE);
        }

        Notice updatedNotice = noticeRepository.save(notice);

        NoticeDTO dto = new NoticeDTO();

        dto.setId(updatedNotice.getId());
        dto.setTitle(updatedNotice.getTitle());
        dto.setDescription(updatedNotice.getDescription());
        dto.setNoticeDate(updatedNotice.getNoticeDate());
        dto.setExpiryDate(updatedNotice.getExpiryDate());
        dto.setNoticeStatus(updatedNotice.getNoticeStatus());

        return dto;
    }

    @Override
    public void deleteNotice(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        noticeRepository.delete(notice);
    }

    @Override
    public List<NoticeDTO> getNoticesByStatus(NoticeStatus noticeStatus) {

        List<Notice> noticeList =
                noticeRepository.findByNoticeStatus(noticeStatus);

        List<NoticeDTO> response = new ArrayList<>();

        for (Notice notice : noticeList) {

            NoticeDTO dto = new NoticeDTO();

            dto.setId(notice.getId());
            dto.setTitle(notice.getTitle());
            dto.setDescription(notice.getDescription());
            dto.setNoticeDate(notice.getNoticeDate());
            dto.setExpiryDate(notice.getExpiryDate());
            dto.setNoticeStatus(notice.getNoticeStatus());

            response.add(dto);
        }

        return response;
    }
    @Override
    public NoticeDashboardDTO getNoticeDashboard() {

        long totalNotices = noticeRepository.count();

        long activeNotices =
                noticeRepository.countByNoticeStatus(NoticeStatus.ACTIVE);

        long expiredNotices =
                noticeRepository.countByNoticeStatus(NoticeStatus.EXPIRED);

        NoticeDashboardDTO dashboard = new NoticeDashboardDTO();

        dashboard.setTotalNotices(totalNotices);
        dashboard.setActiveNotices(activeNotices);
        dashboard.setExpiredNotices(expiredNotices);

        return dashboard;
    }
}