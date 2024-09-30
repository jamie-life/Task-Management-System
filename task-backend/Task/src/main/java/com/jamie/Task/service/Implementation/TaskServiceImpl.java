package com.jamie.Task.service.Implementation;

import com.jamie.Task.dto.TaskDto;
import com.jamie.Task.entity.Task;
import com.jamie.Task.exception.TaskAPIException;
import com.jamie.Task.repository.TaskRepository;
import com.jamie.Task.service.TaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private ModelMapper modelMapper;

    @Override
    public TaskDto addTask(TaskDto taskDto) {

        // convert TaskDto into Task Entity
        Task task = modelMapper.map(taskDto, Task.class);

        Task savedTask = taskRepository.save(task);

        // convert Task into TaskDto and return
        return modelMapper.map(savedTask, TaskDto.class);
    }

    @Override
    public TaskDto getTask(Long id) {
        Task task = findTaskById(id);

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream().map((task) -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Long id) {
        Task task = findTaskById(id);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDto.class);
    }

    @Override
    public void deleteTask(Long id) {
        findTaskById(id);

        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto completeTask(Long id) {
        Task task = findTaskById(id);

        task.setCompleted(Boolean.TRUE);

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDto.class);
    }

    @Override
    public TaskDto inCompleteTask(Long id) {
        Task task = findTaskById(id);

        task.setCompleted(Boolean.FALSE);

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDto.class);
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskAPIException(HttpStatus.NOT_FOUND,"Task not found with id : " + id));
    }
}
