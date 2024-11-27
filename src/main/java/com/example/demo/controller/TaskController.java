package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id){
        Optional<Task> optional =  taskRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        } else {
            throw new NotFoundException();
        }
    }
}
