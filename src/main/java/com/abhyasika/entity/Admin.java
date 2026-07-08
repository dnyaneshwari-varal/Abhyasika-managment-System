	

package com.abhyasika.entity;
	
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
	
public class Admin {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
		private String name;
	
		private String email;
	
		private String password;
	}
