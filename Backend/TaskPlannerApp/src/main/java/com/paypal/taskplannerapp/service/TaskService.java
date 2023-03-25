package com.paypal.taskplannerapp.service;

import com.paypal.taskplannerapp.enums.StatusOfTaskEnum;
import com.paypal.taskplannerapp.exception.TaskException;
import com.paypal.taskplannerapp.exception.UserException;
import com.paypal.taskplannerapp.model.Task;

import java.sql.Types;
import java.util.List;

public interface TaskService {

	public Task addTask(Task task) throws TaskException;
	
	public Task changeAssignee(Long taskId, String newAssign) throws TaskException ;
	
	public List<Task> getAllTasksAssignToUser(Long userId) throws UserException;
	
	public Task getTaskById(Long taskId) throws TaskException;
	
	public Task updateTask(Long tId, String desc, Types types, String assignTo, StatusOfTaskEnum status ) throws TaskException;
	
	public Task deleteTaskById (Long taskId) throws TaskException;
}
