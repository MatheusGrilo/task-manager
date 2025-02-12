package br.dev.grilo.taskmanager.user.business;

import br.dev.grilo.taskmanager.user.business.converter.UserConverter;
import br.dev.grilo.taskmanager.user.business.dto.AddressDTO;
import br.dev.grilo.taskmanager.user.business.dto.PhoneDTO;
import br.dev.grilo.taskmanager.user.business.dto.UserDTO;
import br.dev.grilo.taskmanager.user.infra.entity.Address;
import br.dev.grilo.taskmanager.user.infra.entity.Phone;
import br.dev.grilo.taskmanager.user.infra.entity.User;
import br.dev.grilo.taskmanager.user.infra.exceptions.ConflictExceptions;
import br.dev.grilo.taskmanager.user.infra.exceptions.ResourceNotFoundException;
import br.dev.grilo.taskmanager.user.infra.repository.AddressRepository;
import br.dev.grilo.taskmanager.user.infra.repository.PhoneRepository;
import br.dev.grilo.taskmanager.user.infra.repository.UserRepository;
import br.dev.grilo.taskmanager.user.infra.security.JwtUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;
    private final JwtUtil jwtUtil;

    public UserDTO saveUser(UserDTO userDTO) {

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ConflictExceptions("Username already used: " + userDTO.getUsername());
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ConflictExceptions("Email already used: " + userDTO.getEmail());
        }

        userDTO.setName(userDTO.getName() != null ? userDTO.getName() : userDTO.getUsername());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setCreatedAt(System.currentTimeMillis());

        User user = userConverter.toUser(userDTO);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }

    public UserDTO findUserByUsername(String username) {
        try {
            return userConverter.toUserDTO(
                    userRepository.findByUsername(username)
                            .orElseThrow(() -> new
                                    ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "User not found: " + username)
                            )
            );
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found: " + username
            );
        }
    }

    public void deleteUserByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found: " + username
            );
        }

        userRepository.deleteByUsername(username);
    }

    public UserDTO updateUser(String token, UserDTO dto) {
        String username = jwtUtil.extractUsername(token.substring(7));

        // Check password before update and encrypt, or just return null
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null);

        User userEntity = userRepository.findByUsername(username).
                orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Username not found: " + username)
                );

        User user = userConverter.updateUser(dto, userEntity);

        return userConverter.toUserDTO(userRepository.save(user));
    }

    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {

        Address entity = addressRepository.findById(addressId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Address not found: " + addressId)
        );

        Address address = userConverter.updateAddress(addressDTO, entity);

        addressRepository.save(address);

        return userConverter.toAddressDTO(addressRepository.save(address));
    }

    public PhoneDTO updatePhone(Long phoneId, PhoneDTO phoneDTO) {

        Phone entity = phoneRepository.findById(phoneId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Phone not found: " + phoneId)
        );

        Phone phone = userConverter.updatePhone(phoneDTO, entity);

        phoneRepository.save(phone);

        return userConverter.toPhoneDTO(phoneRepository.save(phone));
    }
}
