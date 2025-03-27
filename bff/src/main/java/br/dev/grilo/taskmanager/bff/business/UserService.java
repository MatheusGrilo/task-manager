package br.dev.grilo.taskmanager.bff.business;

import br.dev.grilo.taskmanager.bff.business.dto.in.AddressDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.LoginDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.PhoneDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.UserDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.out.AddressDTOResponse;
import br.dev.grilo.taskmanager.bff.business.dto.out.PhoneDTOResponse;
import br.dev.grilo.taskmanager.bff.business.dto.out.UserDTOResponse;
import br.dev.grilo.taskmanager.bff.infra.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient client;

    public UserDTOResponse saveUser(UserDTORequest userDTORequest) {
        return client.saveUser(userDTORequest);
    }

    public String userLogin(LoginDTORequest loginDTORequest) {
        return client.login(loginDTORequest);
    }

    public UserDTOResponse findUserByUsername(String username, String token) {
        return client.findUserByUsername(username, token);
    }

    public void deleteUserByUsername(String username, String token) {
        client.deleteUserByUsername(username, token);
    }

    public UserDTOResponse updateUser(UserDTORequest dto, String token) {
        return client.updateUser(dto, token);
    }

    public AddressDTOResponse updateAddress(Long addressId, AddressDTORequest addressDTORequest, String token) {
        return client.updateAddress(addressDTORequest, addressId, token);
    }

    public PhoneDTOResponse updatePhone(Long phoneId, PhoneDTORequest phoneDTORequest, String token) {
        return client.updatePhone(phoneDTORequest, phoneId, token);
    }

    public AddressDTOResponse saveAddress(AddressDTORequest addressDTORequest, String token) {
        return client.saveAddress(addressDTORequest, token);
    }

    public PhoneDTOResponse savePhone(PhoneDTORequest phoneDTORequest, String token) {
        return client.savePhone(phoneDTORequest, token);
    }

}
