package com.abhyasika.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhyasika.dto.ComplaintDTO;
import com.abhyasika.dto.ComplaintDashboardDTO;
import com.abhyasika.enums.ComplaintStatus;
import com.abhyasika.service.ComplaintService;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // Student Raises Complaint
    @PostMapping
    public ResponseEntity<ComplaintDTO> raiseComplaint(
            @RequestBody ComplaintDTO complaintDTO) {

        ComplaintDTO response = complaintService.raiseComplaint(complaintDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Admin View All Complaints
    @GetMapping
    public ResponseEntity<List<ComplaintDTO>> getAllComplaints() {

        List<ComplaintDTO> response = complaintService.getAllComplaints();

        return ResponseEntity.ok(response);
    }

    // Get Complaint By Id
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> getComplaintById(
            @PathVariable Long id) {

        ComplaintDTO response = complaintService.getComplaintById(id);

        return ResponseEntity.ok(response);
    }

    // Student Complaint History
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ComplaintDTO>> getStudentComplaints(
            @PathVariable String studentId) {

        List<ComplaintDTO> response =
                complaintService.getStudentComplaints(studentId);

        return ResponseEntity.ok(response);
    }

    // Get Complaints By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ComplaintDTO>> getComplaintsByStatus(
            @PathVariable ComplaintStatus status) {

        List<ComplaintDTO> response =
                complaintService.getComplaintsByStatus(status);

        return ResponseEntity.ok(response);
    }

    // Update Complaint Status
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<ComplaintDTO> updateComplaintStatus(
            @PathVariable Long id,
            @PathVariable ComplaintStatus status) {

        ComplaintDTO response =
                complaintService.updateComplaintStatus(id, status);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/dashboard")
    public ResponseEntity<ComplaintDashboardDTO> getComplaintDashboard() {

        ComplaintDashboardDTO response =
                complaintService.getComplaintDashboard();

        return ResponseEntity.ok(response);
    }

}