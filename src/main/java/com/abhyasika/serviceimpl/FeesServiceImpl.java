package com.abhyasika.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhyasika.dto.FeesDTO;
import com.abhyasika.entity.Fees;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.PaymentStatus;
import com.abhyasika.repository.FeesRepository;
import com.abhyasika.repository.StudentRepository;
import com.abhyasika.service.FeesService;

@Service
@Transactional
public class FeesServiceImpl implements FeesService {

    private final FeesRepository feesRepository;
    private final StudentRepository studentRepository;

    public FeesServiceImpl(FeesRepository feesRepository,
                           StudentRepository studentRepository) {

        this.feesRepository = feesRepository;
        this.studentRepository = studentRepository;
    }

	@Override
	public FeesDTO addFees(FeesDTO feesDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeesDTO> getAllFees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeesDTO getFeeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeesDTO> getStudentFees(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeesDTO> getPaidStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeesDTO> getPartialStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeesDTO> getPendingStudents() {
		// TODO Auto-generated method stub
		return null;
	}

}