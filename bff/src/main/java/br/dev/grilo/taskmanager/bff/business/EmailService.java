package br.dev.grilo.taskmanager.bff.business;

import br.dev.grilo.taskmanager.bff.business.dto.out.TasksDTOResponse;
import br.dev.grilo.taskmanager.bff.infra.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void sendEmail(TasksDTOResponse tasksDTOResponse) {
        emailClient.sendEmail(tasksDTOResponse);
    }
}
