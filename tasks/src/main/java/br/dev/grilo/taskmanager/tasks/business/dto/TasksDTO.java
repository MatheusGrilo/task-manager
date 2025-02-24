package br.dev.grilo.taskmanager.tasks.business.dto;

import br.dev.grilo.taskmanager.tasks.infra.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksDTO {
    private String id;
    private String taskName;
    private String taskDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime eventDate;
    private String userEmail;
    private NotificationStatusEnum notificationStatus;
}
