package br.dev.grilo.taskmanager.tasks.business;

import br.dev.grilo.taskmanager.tasks.business.dto.TasksDTO;
import br.dev.grilo.taskmanager.tasks.business.mapper.TasksConverter;
import br.dev.grilo.taskmanager.tasks.infra.entity.TasksEntity;
import br.dev.grilo.taskmanager.tasks.infra.enums.NotificationStatusEnum;
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

        List<TasksEntity> tasksEntityList = tasksRepository.findByEventDateBetween(start, end);

        return tasksConverter.toTasksDTOList(
                tasksRepository.findByEventDateBetween(start, end)
        );
    }
}
