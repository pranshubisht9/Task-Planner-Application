package com.paypal.taskplannerapp.service.impl;
import com.paypal.taskplannerapp.enums.GenderEnum;
import com.paypal.taskplannerapp.enums.RoleOfUserEnum;
import com.paypal.taskplannerapp.exception.TaskException;
import com.paypal.taskplannerapp.exception.UserException;
import com.paypal.taskplannerapp.model.Task;
import com.paypal.taskplannerapp.model.User;
import com.paypal.taskplannerapp.repository.TaskRepo;
import com.paypal.taskplannerapp.repository.UserRepo;
import com.paypal.taskplannerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private UserRepo userRepo;



	@Override
	public User registerNewUser(User user) throws UserException {
		User existingUser = userRepo.findByMobileNumber(user.getMobileNumber());
		
		if(existingUser != null) {
			throw new UserException("User Already present with this mobile number");
		}
		
		User addUser = userRepo.save(user);
		return addUser;
	}

	@Override
	public User getUserById(Long userId) throws UserException {
		User user = userRepo.findById(userId)
				            .orElseThrow(() -> new UserException("User not found with this Id :-" + userId));
		return user;
	}

	@Override
	public User updateUser(Long userId, String fname, String lname, String email, String mobile, String password, RoleOfUserEnum role, GenderEnum gender) throws UserException {
		  
		User user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserException("User not found with given Id :-" + userId));
	
	 		Optional<User> newUser = userRepo.findById(userId);

	 		User newUser1 = newUser.get();

	 		if (fname != null) {
	 			newUser1.setFirstName(fname);;
	 		}
	 		if (lname != null) {
	 			newUser1.setLastName(lname);;
	 		}
	 		if (email != null) {
	 			newUser1.setEmail(email);
	 		}
	 		if (password != null) {
	 			newUser1.setPassword(password);
	 		}
	 		if(role != null) {
	 			newUser1.setRole(role);
	 		}
	 		if(gender != null) {
	 			newUser1.setGender(gender);
	 		}

	 		User updated = userRepo.save(newUser1);

	 		return updated;
	}

	@Override
	public User deleteUser(Long userId) throws UserException {
		User user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserException("User not found with given Id :-" + userId));
		
		userRepo.delete(user);
		return user;
	}

	@Override
	public String assignTaskToUser(Long userId, Long taskId) throws UserException, TaskException {
		User fUser = userRepo.findById(userId).orElseThrow(() -> new UserException("User not found with given Id :" + userId));
		Task fTask = taskRepo.findById(taskId).orElseThrow(() -> new TaskException("Task not added with given Id :" + taskId));
		
		if(fTask.getUser() != null) {
			throw new UserException("task already assign to user with given Id :" + userId);
		} else {
			fTask.setUser(fUser);
			fUser.getTasksList().add(fTask);
			taskRepo.save(fTask);
			userRepo.save(fUser);
			return "Task " + taskId + ":- Assigned to user with userId " + userId;	
		}
	}

}
