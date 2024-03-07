package com.test.vupicoapp.controller;

import com.test.vupicoapp.model.TaskDTO;
import com.test.vupicoapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(description = "Method used for getting all task", tags = "task")
    public ResponseEntity<List<TaskDTO>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping
    @Operation(description = "Method used for creating task", tags = "task")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskService.createTask(task));

    }

    @PostMapping("/complete")
    @Operation(description = "Method used for setting task to complete", tags = "task")
    public ResponseEntity<TaskDTO> completeTask(@RequestParam("id") long id) {
        return ResponseEntity.ok(taskService.markTaskAsCompleted(id));
    }

    @PutMapping
    @Operation(description = "Method used for updating task values", tags = "task")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping
    @Operation(description = "Method used for deleting task", tags = "task")
    public ResponseEntity<?> deleteTask(@RequestParam("id") long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}