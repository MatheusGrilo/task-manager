package br.dev.grilo.taskmanager.bff.business;

import br.dev.grilo.taskmanager.bff.business.dto.AddressDTO;
import br.dev.grilo.taskmanager.bff.business.dto.PhoneDTO;
import br.dev.grilo.taskmanager.bff.business.dto.UserDTO;
import br.dev.grilo.taskmanager.bff.infra.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient client;

    public UserDTO saveUser(UserDTO userDTO) {
        return client.saveUser(userDTO);
    }

    public String userLogin(UserDTO userDTO) {
        return client.login(userDTO);
    }

    public UserDTO findUserByUsername(String username, String token) {
        return client.findUserByUsername(username, token);
    }

    public void deleteUserByUsername(String username, String token) {
        client.deleteUserByUsername(username, token);
    }

    public UserDTO updateUser(UserDTO dto, String token) {
        return client.updateUser(dto, token);
    }

    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO, String token) {
        return client.updateAddress(addressDTO, addressId, token);
    }

    public PhoneDTO updatePhone(Long phoneId, PhoneDTO phoneDTO, String token) {
        return client.updatePhone(phoneDTO, phoneId, token);
    }

    public AddressDTO saveAddress(AddressDTO addressDTO, String token) {
        return client.saveAddress(addressDTO, token);
    }

    public PhoneDTO savePhone(PhoneDTO phoneDTO, String token) {
        return client.savePhone(phoneDTO, token);
    }

}
