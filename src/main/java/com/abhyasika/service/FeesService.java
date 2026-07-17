package com.abhyasika.service;

import java.util.List;

import com.abhyasika.dto.FeesDTO;

public interface FeesService {

    // Generate fees for a student
    FeesDTO generateFees(FeesDTO feesDTO);

    // Get all fees (Admin)
    List<FeesDTO> getAllFees();

    // Get fee by ID
    FeesDTO getFeesById(Long feeId);

    // Get all fees of a student
    List<FeesDTO> getFeesByStudent(Long studentId);

    // Update fee status after successful payment
    FeesDTO payFees(Long feeId);

    // Delete fee record
    String deleteFees(Long feeId);
}