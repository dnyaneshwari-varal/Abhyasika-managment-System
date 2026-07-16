package com.abhyasika.service;

import java.util.List;

import com.abhyasika.dto.ComplaintDTO;
import com.abhyasika.dto.ComplaintDashboardDTO;
import com.abhyasika.enums.ComplaintStatus;

public interface ComplaintService {

    ComplaintDTO raiseComplaint(ComplaintDTO complaintDTO);

    List<ComplaintDTO> getAllComplaints();

    ComplaintDTO getComplaintById(Long id);

    List<ComplaintDTO> getStudentComplaints(String studentId);

    List<ComplaintDTO> getComplaintsByStatus(ComplaintStatus complaintStatus);

    ComplaintDTO updateComplaintStatus(Long id, ComplaintStatus complaintStatus);
    
    ComplaintDashboardDTO getComplaintDashboard();

}