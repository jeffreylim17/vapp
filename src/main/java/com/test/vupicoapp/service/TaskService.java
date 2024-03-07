package com.test.vupicoapp.service;

import com.test.vupicoapp.model.Status;
import com.test.vupicoapp.model.TaskDTO;
import com.test.vupicoapp.model.entity.Task;
import com.test.vupicoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO createTask(TaskDTO task) {
        return entityToDto(taskRepository.save(dtoToEntity(task)));
    }

    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream()
                .map(t -> TaskDTO.builder()
                        .taskId(t.getId())
                        .details(t.getDetails())
                        .status(t.getStatus())
                        .build())
                .toList();
    }

    public TaskDTO markTaskAsCompleted(Long taskId) {
        TaskDTO taskDTO = taskRepository.findById(taskId).map(t -> {
                    t.setStatus(Status.DONE);
                    taskRepository.save(t);
                    return entityToDto(t);
                })
                .orElseThrow(() -> new NullPointerException("Task does not exist"));
        return taskDTO;
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        if (taskRepository.findById(taskDTO.getTaskId()).isEmpty())
            throw new NullPointerException("Task does not exist");
        return entityToDto(taskRepository.save(dtoToEntity(taskDTO)));
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private Task dtoToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getTaskId())
                .details(taskDTO.getDetails())
                .status(taskDTO.getStatus())
                .build();
    }

    private TaskDTO entityToDto(Task task) {
        return TaskDTO.builder()
                .taskId(task.getId())
                .details(task.getDetails())
                .status(task.getStatus())
                .build();
    }
}