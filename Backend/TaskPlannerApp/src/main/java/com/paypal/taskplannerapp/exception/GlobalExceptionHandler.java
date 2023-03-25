package com.paypal.taskplannerapp.exception;


import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(SprintException.class)
	public ResponseEntity<ErrorDetails> sprintExceptionHandler(SprintException sprint, WebRequest request){
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(sprint.getMessage());
		error.setDetails(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
			
	}
	
	@ExceptionHandler(TaskException.class)
	public ResponseEntity<ErrorDetails> taskExceptionHandler(TaskException task, WebRequest request){
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(task.getMessage());
		error.setDetails(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException user, WebRequest request){
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(user.getMessage());
		error.setDetails(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception other, WebRequest req){
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(other.getMessage());
		error.setDetails(req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
