package br.dev.grilo.taskmanager.tasks.infra.entity;

import br.dev.grilo.taskmanager.tasks.infra.enums.NotificationStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
public class TasksEntity {

    @Id
    private String id;
    private String taskName;
    private String taskDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime eventDate;
    private String userEmail;
    private NotificationStatusEnum notificationStatus;
}
