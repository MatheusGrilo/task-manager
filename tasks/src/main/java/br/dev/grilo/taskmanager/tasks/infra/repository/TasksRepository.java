package br.dev.grilo.taskmanager.tasks.infra.repository;

import br.dev.grilo.taskmanager.tasks.infra.entity.TasksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends MongoRepository<TasksEntity, String> {
}
