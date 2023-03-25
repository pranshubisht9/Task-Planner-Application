package com.paypal.taskplannerapp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.paypal.taskplannerapp.exception.SprintException;
import com.paypal.taskplannerapp.exception.TaskException;
import com.paypal.taskplannerapp.model.Sprint;
import com.paypal.taskplannerapp.model.Task;
import com.paypal.taskplannerapp.repository.SprintRepo;
import com.paypal.taskplannerapp.repository.TaskRepo;
import com.paypal.taskplannerapp.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintServiceImpl implements SprintService {

	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private SprintRepo sprintRepo;


	@Override
	public Sprint createSprint(Sprint sprint) throws SprintException {
		Sprint existingSprint = sprintRepo.findBySprintName(sprint.getSName());
		
		if(existingSprint != null) {
			throw new SprintException("Sprint Already created with this sprintname :" + sprint.getSName());
		}
		
		Sprint addSprint = sprintRepo.save(sprint);
		return addSprint;
	}

	@Override
	public List<Task> getAllTasksInSprint(Long sId) throws SprintException {
		return sprintRepo.findById(sId).get().getTasksList();
	}

	@Override
	public Sprint getSprintById(Long sprintId) throws SprintException {
		Sprint sprint = sprintRepo.findById(sprintId)
	            .orElseThrow(() -> new SprintException("Sprint not found with given Id :-" + sprintId));
		return sprint;
	}

	@Override
	public Sprint updateSprint(Long sprintId, String sprintName, String description, LocalDate startDate,
			LocalDate endDate) throws SprintException {
		Sprint sprint = sprintRepo.findById(sprintId)
	            .orElseThrow(() -> new SprintException("Sprint not found with given Id :-" + sprintId));
	 		
	 		
	 		Optional<Sprint> newSprint = sprintRepo.findById(sprintId);

	 		Sprint newSprint1 = newSprint.get();

	 		if (sprintName != null) {
	 			newSprint1.setSName(sprintName);
	 		}
	 		if (description != null) {
	 			newSprint1.setDesc(description);
	 		}
	 		if (startDate != null) {
	 			newSprint1.setStartDate(startDate);
	 		}
	 		if (endDate != null) {
	 			newSprint1.setEndDate(endDate);
	 		}

	 		Sprint updated = sprintRepo.save(newSprint1);

	 		return updated;
	}

	@Override
	public Sprint deleteSprintById(Long sprintId) throws SprintException {
		Sprint sprint = sprintRepo.findById(sprintId)
	            .orElseThrow(() -> new SprintException("Sprint not found with given Id :-" + sprintId));
		
		sprintRepo.delete(sprint);
		return sprint;
	}

	@Override
	public String addTaskToSprint(Long tId, Long sId) throws SprintException, TaskException {
				
		Task fTask = taskRepo.findById(tId).orElseThrow(() -> new TaskException("Task not added with given Id :" + tId));
		Sprint fSprint = sprintRepo.findById(sId).orElseThrow(() -> new SprintException("Sprint not found with given Id :" + sId));
		
		if(fTask.getSprint() != null) {
			throw new SprintException("task already added in given sprint :" + sId);
		} else {
			fTask.setSprint(fSprint);
			fSprint.getTasksList().add(fTask);
			sprintRepo.save(fSprint);
			taskRepo.save(fTask);
			return "Task " + tId + ":- added to spring having sprint " + sId;
		}
	}

}
