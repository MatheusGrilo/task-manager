package br.dev.grilo.taskmanager.tasks.infra.repository;

import br.dev.grilo.taskmanager.tasks.infra.entity.TasksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TasksRepository extends MongoRepository<TasksEntity, String> {

    List<TasksEntity> findByEventDateBetween(LocalDateTime start, LocalDateTime end);

    List<TasksEntity> findByUserEmail(String email);
}
