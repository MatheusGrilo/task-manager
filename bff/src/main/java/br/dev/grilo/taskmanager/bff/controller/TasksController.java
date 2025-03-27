package br.dev.grilo.taskmanager.bff.controller;

import br.dev.grilo.taskmanager.bff.business.TasksService;
import br.dev.grilo.taskmanager.bff.business.dto.in.TasksDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.out.TasksDTOResponse;
import br.dev.grilo.taskmanager.bff.infra.enums.NotificationStatusEnum;
import br.dev.grilo.taskmanager.bff.infra.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Tasks API")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    @Operation(summary = "Save a new task", description = "Create a new task in the database")
    @ApiResponse(responseCode = "200", description = "Task created successfully")
    @ApiResponse(responseCode = "400", description = "Task already exists")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<TasksDTOResponse> createTask(@RequestBody TasksDTORequest tasksDTORequest,
                                                       @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.createTask(tasksDTORequest, token));
    }

    @GetMapping("/events")
    @Operation(summary = "Find tasks by date", description = "Find tasks by date")
    @ApiResponse(responseCode = "200", description = "Task found")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<List<TasksDTOResponse>> listTasksByEventDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestHeader(name  = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tasksService.listTasksByEventDate(start, end, token));
    }

    @GetMapping
    @Operation(summary = "Find tasks", description = "Find tasks from current user")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @ApiResponse(responseCode = "404", description = "Tasks not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<List<TasksDTOResponse>> listTasksByUserEmail(@RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.listTasksByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete task", description = "Delete an task by Id")
    @ApiResponse(responseCode = "200", description = "Task deleted")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<Void> deleteTaskById(String taskId,
                                               @RequestHeader(name  = "Authorization", required = false) String token) {
        tasksService.deleteTaskById(taskId, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Update task status", description = "Update task status")
    @ApiResponse(responseCode = "200", description = "Updated task status")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<TasksDTOResponse> changeStatus(@RequestParam("status") NotificationStatusEnum status,
                                                         @RequestParam("taskId") String taskId,
                                                         @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.changeStatus(status, taskId, token));
    }

    @PutMapping
    @Operation(summary = "Update task", description = "Update task data")
    @ApiResponse(responseCode = "200", description = "Updated task")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<TasksDTOResponse> updateTasks(@RequestBody TasksDTORequest tasksDTORequest,
                                                        @RequestParam("taskId") String taskId,
                                                        @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.updateTasks(tasksDTORequest, taskId, token));
    }
}
