package com.jamie.Task.service;

import com.jamie.Task.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto addTask(TaskDto taskDto);

    TaskDto getTask(Long id);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(TaskDto todoDto, Long id);

    void deleteTask(Long id);

    TaskDto completeTask(Long id);

    TaskDto inCompleteTask(Long id);
}
