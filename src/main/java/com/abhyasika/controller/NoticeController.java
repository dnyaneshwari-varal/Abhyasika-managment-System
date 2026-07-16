package com.abhyasika.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abhyasika.dto.NoticeDTO;
import com.abhyasika.dto.NoticeDashboardDTO;
import com.abhyasika.enums.NoticeStatus;
import com.abhyasika.service.NoticeService;

@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping
    public ResponseEntity<NoticeDTO> addNotice(
            @RequestBody NoticeDTO noticeDTO) {

        NoticeDTO response = noticeService.addNotice(noticeDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NoticeDTO>> getAllNotices() {

        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDTO> getNoticeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeDTO> updateNotice(
            @PathVariable Long id,
            @RequestBody NoticeDTO noticeDTO) {

        return ResponseEntity.ok(
                noticeService.updateNotice(id, noticeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotice(
            @PathVariable Long id) {

        noticeService.deleteNotice(id);

        return ResponseEntity.ok("Notice Deleted Successfully");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NoticeDTO>> getNoticesByStatus(
            @PathVariable NoticeStatus status) {

        return ResponseEntity.ok(
                noticeService.getNoticesByStatus(status));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<NoticeDashboardDTO> getNoticeDashboard() {

        return ResponseEntity.ok(
                noticeService.getNoticeDashboard());
    }
}