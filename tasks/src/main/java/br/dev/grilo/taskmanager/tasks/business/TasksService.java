package br.dev.grilo.taskmanager.tasks.business;

import br.dev.grilo.taskmanager.tasks.business.dto.TasksDTO;
import br.dev.grilo.taskmanager.tasks.business.mapper.TasksConverter;
import br.dev.grilo.taskmanager.tasks.business.mapper.TasksUpdateConverter;
import br.dev.grilo.taskmanager.tasks.infra.entity.TasksEntity;
import br.dev.grilo.taskmanager.tasks.infra.enums.NotificationStatusEnum;
import br.dev.grilo.taskmanager.tasks.infra.exceptions.ResourceNotFoundException;
import br.dev.grilo.taskmanager.tasks.infra.repository.TasksRepository;
import br.dev.grilo.taskmanager.tasks.infra.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksConverter tasksConverter;
    private final JwtUtil jwtUtil;
    private final TasksUpdateConverter tasksUpdateConverter;

    public TasksDTO createTask(String token, TasksDTO tasksDTO) {
        String email = jwtUtil.extractEmail(token.substring(7));

        tasksDTO.setCreatedAt(LocalDateTime.now());
        tasksDTO.setNotificationStatus(NotificationStatusEnum.PENDING);
        tasksDTO.setUserEmail(email);

        TasksEntity tasksEntity = tasksConverter.toTasksEntity(tasksDTO);

        return tasksConverter.toTasksDTO(tasksRepository.save(tasksEntity));
    }

    public List<TasksDTO> listTasksByEventDate(
            LocalDateTime start,
            LocalDateTime end
    ) {
        return tasksConverter.toTasksDTOList(
                tasksRepository.findByEventDateBetween(start, end)
        );
    }

    public List<TasksDTO> listTasksByUserEmail(String token) {

        String email = jwtUtil.extractEmail(token.substring(7));
        List<TasksEntity> tasksEntities = tasksRepository.findByUserEmail(email);

        return tasksConverter.toTasksDTOList(tasksEntities);
    }

    public void deleteTaskById(String taskId) {
        try {
            tasksRepository.deleteById(taskId);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error trying to delete task by id: " + taskId, e.getCause());
        }
    }

    public TasksDTO changeStatus(NotificationStatusEnum status, String id) {
        try {
            TasksEntity tasksEntity = tasksRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));

            tasksEntity.setNotificationStatus(status);
            return tasksConverter.toTasksDTO(tasksRepository.save(tasksEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error trying to change task status: " + id, e.getCause());
        }
    }

    public TasksDTO updateTasks(TasksDTO tasksDTO, String id) {
        try {
            TasksEntity tasksEntity = tasksRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));

            tasksUpdateConverter.updateTasks(tasksDTO, tasksEntity);
            return tasksConverter.toTasksDTO(tasksRepository.save(tasksEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error trying to update task: " + id, e.getCause());
        }
    }
}
