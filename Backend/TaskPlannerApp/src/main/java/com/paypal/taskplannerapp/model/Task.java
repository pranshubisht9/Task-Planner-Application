package com.paypal.taskplannerapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paypal.taskplannerapp.enums.StatusOfTaskEnum;
import com.paypal.taskplannerapp.enums.TypesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tId;
	
	private String desc;

	@Enumerated(EnumType.STRING)
	private TypesEnum type;
	
	private String assignTo;
	
	@Enumerated(EnumType.STRING)
	private StatusOfTaskEnum status;
	
	@ManyToOne
	@JsonIgnore
	private Sprint sprint;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;
}
