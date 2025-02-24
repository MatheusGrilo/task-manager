package br.dev.grilo.taskmanager.tasks.controller;

import br.dev.grilo.taskmanager.tasks.business.TasksService;
import br.dev.grilo.taskmanager.tasks.business.dto.TasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity<TasksDTO> createTask(@RequestBody TasksDTO tasksDTO,
                                               @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.createTask(token, tasksDTO));
    }
}
