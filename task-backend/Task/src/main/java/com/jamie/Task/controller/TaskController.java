package com.jamie.Task.controller;

import com.jamie.Task.dto.TaskDto;
import com.jamie.Task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {
        TaskDto task = taskService.addTask(taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    // Build Get Task REST API
    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long taskId){
        TaskDto taskDto = taskService.getTask(taskId);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    // Build Get All Tasks REST API
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
        //return ResponseEntity.ok(tasks);
    }

    // Build Update Task REST API
    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.updateTask(taskDto, taskId);
        return ResponseEntity.ok(updatedTask);
    }

    // Build Delete Task REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully!");
    }

    // Build Complete Task REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TaskDto> completeTask(@PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.completeTask(taskId);
        return ResponseEntity.ok(updatedTask);
    }

    // Build In Complete Task REST API
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TaskDto> inCompleteTask(@PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.inCompleteTask(taskId);
        return ResponseEntity.ok(updatedTask);
    }

}
