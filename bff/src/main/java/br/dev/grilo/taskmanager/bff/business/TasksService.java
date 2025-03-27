package br.dev.grilo.taskmanager.bff.business;

import br.dev.grilo.taskmanager.bff.business.dto.in.TasksDTORequest;
import br.dev.grilo.taskmanager.bff.infra.client.TasksClient;
import br.dev.grilo.taskmanager.bff.business.dto.out.TasksDTOResponse;
import br.dev.grilo.taskmanager.bff.infra.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksClient tasksClient;

    public TasksDTOResponse createTask(TasksDTORequest tasksDTORequest, String token) {
        return tasksClient.createTask(tasksDTORequest, token);
    }

    public List<TasksDTOResponse> listTasksByEventDate(
            LocalDateTime start,
            LocalDateTime end,
            String token
    ) {
        return tasksClient.listTasksByEventDate(start, end, token);

    }

    public List<TasksDTOResponse> listTasksByUserEmail(String token) {
        return tasksClient.listTasksByUserEmail(token);
    }

    public void deleteTaskById(String taskId, String token) {
        tasksClient.deleteTaskById(taskId, token);
    }

    public TasksDTOResponse changeStatus(NotificationStatusEnum status, String id, String token) {
        return tasksClient.changeStatus(status, id, token);
    }

    public TasksDTOResponse updateTasks(TasksDTORequest tasksDTORequest, String id, String token) {
       return tasksClient.updateTasks(tasksDTORequest, id, token);
    }
}
