package br.dev.grilo.taskmanager.user.business;

import br.dev.grilo.taskmanager.user.business.converter.UserConverter;
import br.dev.grilo.taskmanager.user.business.dto.UserDTO;
import br.dev.grilo.taskmanager.user.infra.entity.User;
import br.dev.grilo.taskmanager.user.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO) {
        User user = userConverter.toUser(userDTO);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }
}
