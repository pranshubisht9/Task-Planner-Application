package com.paypal.taskplannerapp.repository;

import com.paypal.taskplannerapp.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepo extends JpaRepository<Sprint, Long>{

	public Sprint findBySprintName(String sprintName);
}
