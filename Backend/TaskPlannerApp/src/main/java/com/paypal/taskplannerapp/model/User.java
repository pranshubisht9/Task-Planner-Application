package com.paypal.taskplannerapp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paypal.taskplannerapp.enums.GenderEnum;
import com.paypal.taskplannerapp.enums.RoleOfUserEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long uId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String mobileNumber;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RoleOfUserEnum role;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Task> tasksList;
}
