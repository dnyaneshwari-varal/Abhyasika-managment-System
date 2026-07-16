package com.abhyasika.service;

import java.util.List;

import com.abhyasika.dto.FeesDTO;

public interface FeesService {

	FeesDTO addFees(FeesDTO feesDTO);

	List<FeesDTO> getAllFees();

	FeesDTO getFeeById(Long id);

	List<FeesDTO> getStudentFees(String studentId);

	List<FeesDTO> getPaidStudents();

	List<FeesDTO> getPartialStudents();

	List<FeesDTO> getPendingStudents();
    
    

}