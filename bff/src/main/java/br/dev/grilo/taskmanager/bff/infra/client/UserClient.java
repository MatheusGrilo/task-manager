package br.dev.grilo.taskmanager.bff.infra.client;

import br.dev.grilo.taskmanager.bff.business.dto.in.AddressDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.LoginDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.PhoneDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.UserDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.out.AddressDTOResponse;
import br.dev.grilo.taskmanager.bff.business.dto.out.PhoneDTOResponse;
import br.dev.grilo.taskmanager.bff.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTOResponse findUserByUsername(
            @RequestParam("username") String username,
            @RequestHeader("Authorization") String token
    );

    @PostMapping
    UserDTOResponse saveUser(@RequestBody UserDTORequest userDTORequest);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTORequest);

    @DeleteMapping("{username}")
    void deleteUserByUsername(@PathVariable String username,
                              @RequestHeader("Authorization") String token);

    @PatchMapping
    UserDTOResponse updateUser(@RequestBody UserDTORequest dto,
                               @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDTOResponse saveAddress(@RequestBody AddressDTORequest dto,
                                   @RequestHeader("Authorization") String token);


    @PatchMapping("/address")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest dto,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    PhoneDTOResponse savePhone(@RequestBody PhoneDTORequest dto,
                               @RequestHeader("Authorization") String token);

    @PatchMapping("/phone")
    PhoneDTOResponse updatePhone(@RequestBody PhoneDTORequest dto,
                                 @RequestParam("id") Long id,
                                 @RequestHeader("Authorization") String token);
}
