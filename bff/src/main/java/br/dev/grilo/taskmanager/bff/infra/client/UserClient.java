package br.dev.grilo.taskmanager.bff.infra.client;

import br.dev.grilo.taskmanager.bff.business.dto.AddressDTO;
import br.dev.grilo.taskmanager.bff.business.dto.PhoneDTO;
import br.dev.grilo.taskmanager.bff.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTO findUserByUsername(
            @RequestParam("username") String username,
            @RequestHeader("Authorization") String token
    );

    @PostMapping
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @PostMapping("/login")
    String login(@RequestBody UserDTO userDTO);

    @DeleteMapping("{username}")
    void deleteUserByUsername(@PathVariable String username,
                              @RequestHeader("Authorization") String token);

    @PatchMapping
    UserDTO updateUser(@RequestBody UserDTO dto,
                       @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDTO saveAddress(@RequestBody AddressDTO dto,
                           @RequestHeader("Authorization") String token);


    @PatchMapping("/address")
    AddressDTO updateAddress(@RequestBody AddressDTO dto,
                             @RequestParam("id") Long id,
                             @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    PhoneDTO savePhone(@RequestBody PhoneDTO dto,
                       @RequestHeader("Authorization") String token);

    @PatchMapping("/phone")
    PhoneDTO updatePhone(@RequestBody PhoneDTO dto,
                         @RequestParam("id") Long id,
                         @RequestHeader("Authorization") String token);
}
