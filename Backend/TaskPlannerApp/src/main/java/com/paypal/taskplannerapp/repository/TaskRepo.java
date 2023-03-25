package com.paypal.taskplannerapp.repository;

import com.paypal.taskplannerapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{

}
