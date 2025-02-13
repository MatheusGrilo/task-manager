package br.dev.grilo.taskmanager.tasks.infra.security.client;

import br.dev.grilo.taskmanager.tasks.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user")
    UserDTO findUserByUsername(
            @RequestParam("username") String username,
            @RequestHeader("Authorization") String token
    );
}
