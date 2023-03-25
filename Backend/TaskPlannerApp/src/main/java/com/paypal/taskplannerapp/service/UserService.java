package com.paypal.taskplannerapp.service;

import com.paypal.taskplannerapp.enums.GenderEnum;
import com.paypal.taskplannerapp.enums.RoleOfUserEnum;
import com.paypal.taskplannerapp.exception.TaskException;
import com.paypal.taskplannerapp.exception.UserException;
import com.paypal.taskplannerapp.model.User;

public interface UserService {
	
	public User registerNewUser(User user) throws UserException;
	
	public User getUserById(Long userId) throws UserException;
	
	public User updateUser(Long userId, String firstName, String lastName, String email, String mobile, String password, RoleOfUserEnum role, GenderEnum gender) throws UserException;
	
	public User deleteUser(Long userId) throws UserException;
	
	public String assignTaskToUser (Long userId, Long taskId) throws UserException, TaskException;

}
