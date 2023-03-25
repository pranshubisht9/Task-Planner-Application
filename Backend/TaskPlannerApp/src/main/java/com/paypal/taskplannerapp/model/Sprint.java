package com.paypal.taskplannerapp.model;

import java.time.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sId;
	
	private String sName;
	
	private String desc;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sprint")
	private List<Task> tasksList;
}
