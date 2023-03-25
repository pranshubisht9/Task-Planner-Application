package com.paypal.taskplannerapp.service.impl;
import com.paypal.taskplannerapp.enums.StatusOfTaskEnum;
import com.paypal.taskplannerapp.enums.TypesEnum;
import com.paypal.taskplannerapp.exception.TaskException;
import com.paypal.taskplannerapp.exception.UserException;
import com.paypal.taskplannerapp.model.Task;
import com.paypal.taskplannerapp.model.User;
import com.paypal.taskplannerapp.repository.TaskRepo;
import com.paypal.taskplannerapp.repository.UserRepo;
import com.paypal.taskplannerapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TaskRepo taskRepo;


	@Override
	public Task addTask(Task task) throws TaskException {
		return taskRepo.save(task);
	}

	@Override
	public Task changeAssignee(Long taskId, String newAssign)throws TaskException  {
		Task task = taskRepo.findById(taskId).get();
		task.setAssignTo(newAssign);
		
		return taskRepo.save(task);
	}

	@Override
	public Task getTaskById(Long taskId) throws TaskException {
		Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskException("Task not added with given Id :-" + taskId));
		return task;
	}

	@Override
	public Task updateTask(Long tId, String desc, TypesEnum types, String assignTo, StatusOfTaskEnum status) throws TaskException {
		Task task = taskRepo.findById(tId)
				.orElseThrow(() -> new TaskException("Task not added with given Id :-" + tId));


		Optional<Task> newtask = taskRepo.findById(tId);

		Task newTask1 = newtask.get();

		if (desc != null) {
			newTask1.setDesc(desc);
		}
		if (types != null) {
			newTask1.setType(types);
		}
		if (assignTo != null) {
			newTask1.setAssignTo(assignTo);
		}
		if (status != null) {
			newTask1.setStatus(status);
		}

		Task updated = taskRepo.save(newTask1);

		return updated;
	}

	@Override
	public Task deleteTaskById(Long taskId) throws TaskException {
		Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskException("Task not added with given Id :-" + taskId));
		taskRepo.delete(task);
		return task;
	}

	@Override
	public List<Task> getAllTasksAssignToUser(Long uId) throws UserException {
		User user = userRepo.findById(uId).orElseThrow(() -> new UserException("User not found with given id :"+uId));
		return user.getTasksList();	
	}
}
