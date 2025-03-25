package br.dev.grilo.taskmanager.bff.controller;

import br.dev.grilo.taskmanager.bff.business.UserService;
import br.dev.grilo.taskmanager.bff.business.dto.AddressDTO;
import br.dev.grilo.taskmanager.bff.business.dto.PhoneDTO;
import br.dev.grilo.taskmanager.bff.business.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<UserDTO> findUserByUsername(@RequestParam("username") String username,
                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.findUserByUsername(username, token));
    }


    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO dto,
                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateUser(dto, token));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.userLogin(userDTO);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username,
                                                     @RequestHeader("Authorization") String token) {
        userService.deleteUserByUsername(username, token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.saveAddress(dto, token));
    }

    @PatchMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO dto,
                                                    @RequestParam("id") Long id,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateAddress(id, dto, token));
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneDTO> savePhone(@RequestBody PhoneDTO dto,
                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.savePhone(dto, token));
    }

    @PatchMapping("/phone")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO dto,
                                                @RequestParam("id") Long id,
                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updatePhone(id, dto, token));
    }

}
