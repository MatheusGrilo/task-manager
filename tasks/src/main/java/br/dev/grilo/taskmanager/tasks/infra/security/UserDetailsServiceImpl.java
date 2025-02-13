package br.dev.grilo.taskmanager.tasks.infra.security;

import br.dev.grilo.taskmanager.tasks.business.dto.UserDTO;
import br.dev.grilo.taskmanager.tasks.infra.security.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    @Autowired
    private UserClient userClient;

    public UserDetails loadUserData(String username, String token) {

        UserDTO userDTO = userClient.findUserByUsername(username, token);
                return User
                        .withUsername(userDTO.getUsername())
                        .password(userDTO.getPassword())
                        .build();
    }
}
