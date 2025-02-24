package br.dev.grilo.taskmanager.tasks.controller;

import br.dev.grilo.taskmanager.tasks.business.TasksService;
import br.dev.grilo.taskmanager.tasks.business.dto.TasksDTO;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/events")
    public ResponseEntity<List<TasksDTO>> listTasksByEventDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return ResponseEntity.ok(tasksService.listTasksByEventDate(start, end));
    }
}
