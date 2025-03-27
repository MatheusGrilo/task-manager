package br.dev.grilo.taskmanager.bff.infra.client;

import br.dev.grilo.taskmanager.bff.business.dto.in.TasksDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.out.TasksDTOResponse;
import br.dev.grilo.taskmanager.bff.infra.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks", url = "${tasks.url}")
public interface TasksClient {

    @PostMapping
    TasksDTOResponse createTask(@RequestBody TasksDTORequest tasksDTORequest,
                                @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TasksDTOResponse> listTasksByEventDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestHeader("Authorization") String token
    );

    @GetMapping
    List<TasksDTOResponse> listTasksByUserEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String taskId,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TasksDTOResponse changeStatus(@RequestParam("status") NotificationStatusEnum status,
                                  @RequestParam("taskId") String taskId,
                                  @RequestHeader("Authorization") String token);

    @PutMapping
    TasksDTOResponse updateTasks(@RequestBody TasksDTORequest tasksDTORequest,
                                 @RequestParam("taskId") String taskIdEnums,
                                 @RequestHeader("Authorization") String token);
}
