package br.dev.grilo.taskmanager.tasks.business.mapper;

import br.dev.grilo.taskmanager.tasks.business.dto.TasksDTO;
import br.dev.grilo.taskmanager.tasks.infra.entity.TasksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TasksUpdateConverter {

    void updateTasks(TasksDTO tasksDTO, @MappingTarget TasksEntity tasksEntity);
}
