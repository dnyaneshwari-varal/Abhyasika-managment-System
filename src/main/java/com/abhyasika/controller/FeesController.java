package com.abhyasika.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhyasika.dto.FeesDTO;
import com.abhyasika.service.FeesService;

@RestController
@RequestMapping("/fees")
public class FeesController {

    private final FeesService feesService;

    public FeesController(FeesService feesService) {
        this.feesService = feesService;
    }

    // Generate Fees
    @PostMapping("/generate")
    public FeesDTO generateFees(@RequestBody FeesDTO feesDTO) {
        return feesService.generateFees(feesDTO);
    }

    // Get All Fees
    @GetMapping
    public List<FeesDTO> getAllFees() {
        return feesService.getAllFees();
    }

    // Get Fees By Id
    @GetMapping("/{feeId}")
    public FeesDTO getFeesById(@PathVariable Long feeId) {
        return feesService.getFeesById(feeId);
    }

    // Get Fees By Student
    @GetMapping("/student/{studentId}")
    public List<FeesDTO> getFeesByStudent(@PathVariable Long studentId) {
        return feesService.getFeesByStudent(studentId);
    }

    // Pay Fees
    @PutMapping("/pay/{feeId}")
    public FeesDTO payFees(@PathVariable Long feeId) {
        return feesService.payFees(feeId);
    }

    // Delete Fees
    @DeleteMapping("/{feeId}")
    public String deleteFees(@PathVariable Long feeId) {
        return feesService.deleteFees(feeId);
    }
}