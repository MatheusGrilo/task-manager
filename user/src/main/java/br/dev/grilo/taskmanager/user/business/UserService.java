package br.dev.grilo.taskmanager.user.business;

import br.dev.grilo.taskmanager.user.business.converter.UserConverter;
import br.dev.grilo.taskmanager.user.business.dto.UserDTO;
import br.dev.grilo.taskmanager.user.infra.entity.User;
import br.dev.grilo.taskmanager.user.infra.exceptions.ConflictExceptions;
import br.dev.grilo.taskmanager.user.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDTO) {

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ConflictExceptions("Username already used: " + userDTO.getUsername());
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ConflictExceptions("Email already used: " + userDTO.getEmail());
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = userConverter.toUser(userDTO);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new
                        ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found: " + username)
                );
    }

    public void deleteUserByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found: " + username
            );
        }

        userRepository.deleteByUsername(username);
    }
}
