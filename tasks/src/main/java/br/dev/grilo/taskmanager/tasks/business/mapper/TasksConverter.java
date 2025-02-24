package br.dev.grilo.taskmanager.tasks.business.mapper;

import br.dev.grilo.taskmanager.tasks.business.dto.TasksDTO;
import br.dev.grilo.taskmanager.tasks.infra.entity.TasksEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TasksConverter {

    TasksEntity toTasksEntity(TasksDTO tasksDTO);

    TasksDTO toTasksDTO(TasksEntity tasksEntity);
}
