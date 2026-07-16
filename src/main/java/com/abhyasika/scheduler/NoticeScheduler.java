package com.abhyasika.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.abhyasika.entity.Notice;
import com.abhyasika.enums.NoticeStatus;
import com.abhyasika.repository.NoticeRepository;

@Component
public class NoticeScheduler {

    private final NoticeRepository noticeRepository;

    public NoticeScheduler(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateExpiredNotices() {

    	List<Notice> notices =
    	        noticeRepository.findByNoticeStatusAndExpiryDateBefore(
    	                NoticeStatus.ACTIVE,
    	                LocalDate.now());

    	for (Notice notice : notices) {

    	    notice.setNoticeStatus(NoticeStatus.EXPIRED);

    	    noticeRepository.save(notice);
    	}

        System.out.println("Expired Notices Updated");
    }
}