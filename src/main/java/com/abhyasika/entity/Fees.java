package com.abhyasika.entity;

import java.time.LocalDate;

import com.abhyasika.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate month;

	private Double amount;

	private LocalDate paymentDate;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
}
