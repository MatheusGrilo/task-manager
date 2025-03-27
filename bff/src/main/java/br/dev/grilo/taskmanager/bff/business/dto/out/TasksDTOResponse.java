package br.dev.grilo.taskmanager.bff.business.dto.out;

import br.dev.grilo.taskmanager.bff.infra.enums.NotificationStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksDTOResponse {
    private String id;
    private String taskName;
    private String taskDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
    private String userEmail;
    private NotificationStatusEnum notificationStatus;
}
