package com.abhyasika.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abhyasika.dto.FeesDTO;
import com.abhyasika.entity.Fees;
import com.abhyasika.entity.Room;
import com.abhyasika.entity.Seat;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.FeeStatus;
import com.abhyasika.repository.FeesRepository;
import com.abhyasika.repository.StudentRepository;
import com.abhyasika.service.FeesService;

@Service
public class FeesServiceImpl implements FeesService {

    private final FeesRepository feesRepository;
    private final StudentRepository studentRepository;

    public FeesServiceImpl(FeesRepository feesRepository,
                           StudentRepository studentRepository) {
        this.feesRepository = feesRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FeesDTO generateFees(FeesDTO feesDTO) {

        Student student = studentRepository.findById(feesDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (student.getSeat() == null) {
            throw new RuntimeException("Seat not allocated to student");
        }

        Seat seat = student.getSeat();
        Room room = seat.getRoom();

        BigDecimal monthlyFee = room.getMonthlyFee();

        int duration = 0;
        double discount = 0;

        switch (feesDTO.getMembershipType()) {

        case MONTHLY:
            duration = 1;
            discount = 0;
            break;

        case QUARTERLY:
            duration = 3;
            discount = 5;
            break;

        case HALF_YEARLY:
            duration = 6;
            discount = 10;
            break;

        case YEARLY:
            duration = 12;
            discount = 15;
            break;
        }

        BigDecimal originalAmount = monthlyFee.multiply(BigDecimal.valueOf(duration));

        BigDecimal discountAmount = originalAmount
                .multiply(BigDecimal.valueOf(discount))
                .divide(BigDecimal.valueOf(100));

        BigDecimal finalAmount = originalAmount.subtract(discountAmount);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(duration);
        LocalDate dueDate = startDate.plusDays(7);

        Fees fees = new Fees();

        fees.setStudent(student);
        fees.setMembershipType(feesDTO.getMembershipType());
        fees.setDurationInMonths(duration);
        fees.setMonthlyFee(monthlyFee);
        fees.setOriginalAmount(originalAmount);
        fees.setDiscountPercentage(discount);
        fees.setDiscountAmount(discountAmount);
        fees.setFinalAmount(finalAmount);
        fees.setMembershipStartDate(startDate);
        fees.setMembershipEndDate(endDate);
        fees.setDueDate(dueDate);
        fees.setFeeStatus(FeeStatus.PENDING);

        Fees savedFees = feesRepository.save(fees);

        return convertToDTO(savedFees);
    }
    
    private FeesDTO convertToDTO(Fees fees) {

        FeesDTO dto = new FeesDTO();

        dto.setId(fees.getId());
        dto.setStudentId(fees.getStudent().getId());
        dto.setMembershipType(fees.getMembershipType());
        dto.setDurationInMonths(fees.getDurationInMonths());
        dto.setMonthlyFee(fees.getMonthlyFee());
        dto.setOriginalAmount(fees.getOriginalAmount());
        dto.setDiscountPercentage(fees.getDiscountPercentage());
        dto.setDiscountAmount(fees.getDiscountAmount());
        dto.setFinalAmount(fees.getFinalAmount());
        dto.setMembershipStartDate(fees.getMembershipStartDate());
        dto.setMembershipEndDate(fees.getMembershipEndDate());
        dto.setDueDate(fees.getDueDate());
        dto.setFeeStatus(fees.getFeeStatus());
        dto.setPaymentDate(fees.getPaymentDate());

        return dto;
    }

    @Override
    public List<FeesDTO> getAllFees() {

        List<Fees> feesList = feesRepository.findAll();

        return feesList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeesDTO getFeesById(Long feeId) {

        Fees fees = feesRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fees record not found"));

        return convertToDTO(fees);
    }

    @Override
    public List<FeesDTO> getFeesByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Fees> feesList = feesRepository.findByStudent(student);

        return feesList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public FeesDTO payFees(Long feeId) {

        Fees fees = feesRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fees record not found"));

        fees.setFeeStatus(FeeStatus.PAID);
        fees.setPaymentDate(LocalDate.now());

        Fees updatedFees = feesRepository.save(fees);

        return convertToDTO(updatedFees);
    }

    @Override
    public String deleteFees(Long feeId) {

        Fees fees = feesRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fees record not found"));

        feesRepository.delete(fees);

        return "Fees record deleted successfully.";
    }
}
