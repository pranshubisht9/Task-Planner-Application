package com.paypal.taskplannerapp.service;

import java.time.LocalDate;
import java.util.List;

import com.paypal.taskplannerapp.exception.SprintException;
import com.paypal.taskplannerapp.exception.TaskException;
import com.paypal.taskplannerapp.model.Sprint;
import com.paypal.taskplannerapp.model.Task;

public interface SprintService {

	public Sprint createSprint(Sprint sprint) throws SprintException;
	
	public List<Task> getAllTasksInSprint(Long sprintId) throws SprintException;

	public String addTaskToSprint (Long taskId, Long sprintId) throws SprintException, TaskException;

	public Sprint getSprintById(Long sprintId) throws SprintException;

	public Sprint updateSprint(Long sprintId, String sprintName, String description, LocalDate startDate, LocalDate endDate) throws SprintException;

	public Sprint deleteSprintById (Long sprintId) throws SprintException;
}
