package br.dev.grilo.taskmanager.bff.infra.client;

import br.dev.grilo.taskmanager.bff.business.dto.out.TasksDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${email.url}")
public interface EmailClient {

    void sendEmail(@RequestBody TasksDTOResponse tasksDTOResponse);


}
