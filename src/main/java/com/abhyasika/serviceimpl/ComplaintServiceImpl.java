package com.abhyasika.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhyasika.dto.ComplaintDTO;
import com.abhyasika.dto.ComplaintDashboardDTO;
import com.abhyasika.entity.Complaint;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.ComplaintStatus;
import com.abhyasika.repository.ComplaintRepository;
import com.abhyasika.repository.StudentRepository;
import com.abhyasika.service.ComplaintService;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final StudentRepository studentRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                StudentRepository studentRepository) {

        this.complaintRepository = complaintRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ComplaintDTO raiseComplaint(ComplaintDTO complaintDTO) {

        Student student = studentRepository.findByStudentId(complaintDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Complaint complaint = new Complaint();

        complaint.setStudent(student);
        complaint.setComplaintCategory(complaintDTO.getComplaintCategory());
        complaint.setTitle(complaintDTO.getTitle());
        complaint.setDescription(complaintDTO.getDescription());

        // Automatically Set
        complaint.setComplaintDate(LocalDate.now());
        complaint.setComplaintStatus(ComplaintStatus.OPEN);

        Complaint savedComplaint = complaintRepository.save(complaint);

        ComplaintDTO response = new ComplaintDTO();

        response.setId(savedComplaint.getId());
        response.setStudentId(student.getStudentId());
        response.setStudentName(student.getFullName());
        response.setComplaintCategory(savedComplaint.getComplaintCategory());
        response.setTitle(savedComplaint.getTitle());
        response.setDescription(savedComplaint.getDescription());
        response.setComplaintDate(savedComplaint.getComplaintDate());
        response.setComplaintStatus(savedComplaint.getComplaintStatus());

        return response;
    }

    @Override
    public List<ComplaintDTO> getAllComplaints() {

        List<Complaint> complaintList = complaintRepository.findAll();

        List<ComplaintDTO> response = new ArrayList<>();

        for (Complaint complaint : complaintList) {

            ComplaintDTO dto = new ComplaintDTO();

            dto.setStudentId(complaint.getStudent().getStudentId());
            dto.setId(complaint.getId());
            dto.setStudentName(complaint.getStudent().getFullName());
            dto.setComplaintCategory(complaint.getComplaintCategory());
            dto.setTitle(complaint.getTitle());
            dto.setDescription(complaint.getDescription());
            dto.setComplaintDate(complaint.getComplaintDate());
            dto.setComplaintStatus(complaint.getComplaintStatus());
            

            response.add(dto);
        }

        return response;
    }

    @Override
    public ComplaintDTO getComplaintById(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        ComplaintDTO dto = new ComplaintDTO();

        dto.setStudentId(complaint.getStudent().getStudentId());
        dto.setId(complaint.getId());
        dto.setStudentName(complaint.getStudent().getFullName());
        dto.setComplaintCategory(complaint.getComplaintCategory());
        dto.setTitle(complaint.getTitle());
        dto.setDescription(complaint.getDescription());
        dto.setComplaintDate(complaint.getComplaintDate());
        dto.setComplaintStatus(complaint.getComplaintStatus());
        

        return dto;
    }

    @Override
    public List<ComplaintDTO> getStudentComplaints(String studentId) {

        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Complaint> complaintList = complaintRepository.findByStudent(student);

        List<ComplaintDTO> response = new ArrayList<>();

        for (Complaint complaint : complaintList) {

            ComplaintDTO dto = new ComplaintDTO();

            dto.setStudentId(student.getStudentId());
            dto.setStudentName(student.getFullName());
            dto.setId(complaint.getId());
            dto.setComplaintCategory(complaint.getComplaintCategory());
            dto.setTitle(complaint.getTitle());
            dto.setDescription(complaint.getDescription());
            dto.setComplaintDate(complaint.getComplaintDate());
            dto.setComplaintStatus(complaint.getComplaintStatus());

            response.add(dto);
        }

        return response;
    }

    @Override
    public List<ComplaintDTO> getComplaintsByStatus(ComplaintStatus complaintStatus) {

        List<Complaint> complaintList =
                complaintRepository.findByComplaintStatus(complaintStatus);

        List<ComplaintDTO> response = new ArrayList<>();

        for (Complaint complaint : complaintList) {

            ComplaintDTO dto = new ComplaintDTO();

            dto.setStudentId(complaint.getStudent().getStudentId());
            dto.setId(complaint.getId());
            dto.setStudentName(complaint.getStudent().getFullName());
            dto.setComplaintCategory(complaint.getComplaintCategory());
            dto.setTitle(complaint.getTitle());
            dto.setDescription(complaint.getDescription());
            dto.setComplaintDate(complaint.getComplaintDate());
            dto.setComplaintStatus(complaint.getComplaintStatus());

            response.add(dto);
        }

        return response;
    }

    @Override
    public ComplaintDTO updateComplaintStatus(Long id, ComplaintStatus complaintStatus) {

    	Complaint complaint = complaintRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Complaint not found"));

    	ComplaintStatus currentStatus = complaint.getComplaintStatus();

    	// Business Validations
    	if (currentStatus == ComplaintStatus.OPEN
    	        && complaintStatus == ComplaintStatus.RESOLVED) {

    	    throw new RuntimeException(
    	            "Complaint must first move to IN_PROGRESS.");
    	}

    	if (currentStatus == ComplaintStatus.RESOLVED
    	        && complaintStatus == ComplaintStatus.OPEN) {

    	    throw new RuntimeException(
    	            "Resolved complaint cannot be changed back to OPEN.");
    	}

    	// Update Status
    	complaint.setComplaintStatus(complaintStatus);

    	Complaint updatedComplaint = complaintRepository.save(complaint);

        ComplaintDTO dto = new ComplaintDTO();

        dto.setStudentId(updatedComplaint.getStudent().getStudentId());
        dto.setId(updatedComplaint.getId());
        dto.setStudentName(updatedComplaint.getStudent().getFullName());
        dto.setComplaintCategory(updatedComplaint.getComplaintCategory());
        dto.setTitle(updatedComplaint.getTitle());
        dto.setDescription(updatedComplaint.getDescription());
        dto.setComplaintDate(updatedComplaint.getComplaintDate());
        dto.setComplaintStatus(updatedComplaint.getComplaintStatus());

        return dto;
    }
    
    @Override
    public ComplaintDashboardDTO getComplaintDashboard() {

        long totalComplaints = complaintRepository.count();

        long openComplaints =
                complaintRepository.countByComplaintStatus(ComplaintStatus.OPEN);

        long inProgressComplaints =
                complaintRepository.countByComplaintStatus(ComplaintStatus.IN_PROGRESS);

        long resolvedComplaints =
                complaintRepository.countByComplaintStatus(ComplaintStatus.RESOLVED);

        ComplaintDashboardDTO dashboard = new ComplaintDashboardDTO();

        dashboard.setTotalComplaints(totalComplaints);
        dashboard.setOpenComplaints(openComplaints);
        dashboard.setInProgressComplaints(inProgressComplaints);
        dashboard.setResolvedComplaints(resolvedComplaints);

        return dashboard;
    }

}