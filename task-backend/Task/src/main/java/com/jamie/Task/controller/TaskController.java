package com.jamie.Task.controller;

import com.jamie.Task.dto.TaskDto;
import com.jamie.Task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private TaskService taskService;

    // Add Task REST API
    // Method Level Security Using PreAuthorize
    /* @Secured("ROLE_ADMIN")  same as @PreAuthorize("hasRole('ADMIN')") but
    PreAuthorize allows more expression based control and can be written in  SpEL (Spring Expression Language).
    e.g. @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    e.g. @PreAuthorize("#username == authentication.principal.username")*/
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {
        TaskDto task = taskService.addTask(taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    // Get Task REST API
    // Method Level Security Using PreAuthorize, Allowing User & Admin
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long taskId){
        TaskDto taskDto = taskService.getTask(taskId);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    // Get All Tasks REST API
    // Method Level Security Using PreAuthorize, Allowing User & Admin
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
        //return ResponseEntity.ok(tasks);
    }

    // Update Task REST API
    // Method Level Security Using PreAuthorize
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.updateTask(taskDto, taskId);
        return ResponseEntity.ok(updatedTask);
    }

    // Delete Task REST API
    // Method Level Security Using PreAuthorize
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully!");
    }

    // Complete Task REST API
    // Method Level Security Using PreAuthorize, Allowing User & Admin
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TaskDto> completeTask(@PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.completeTask(taskId);
        return ResponseEntity.ok(updatedTask);
    }

    // In Complete Task REST API
    // Method Level Security Using PreAuthorize, Allowing User & Admin
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TaskDto> incompleteTask(@PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.inCompleteTask(taskId);
        return ResponseEntity.ok(updatedTask);
    }

}
