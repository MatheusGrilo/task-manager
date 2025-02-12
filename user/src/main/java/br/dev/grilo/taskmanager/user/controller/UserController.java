package br.dev.grilo.taskmanager.user.controller;

import br.dev.grilo.taskmanager.user.business.UserService;
import br.dev.grilo.taskmanager.user.business.dto.AddressDTO;
import br.dev.grilo.taskmanager.user.business.dto.PhoneDTO;
import br.dev.grilo.taskmanager.user.business.dto.UserDTO;
import br.dev.grilo.taskmanager.user.infra.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<UserDTO> findUserByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }


    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String token, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(token, dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.saveAddress(token, dto));
    }

    @PatchMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO dto, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.updateAddress(id, dto));
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneDTO> savePhone(@RequestBody PhoneDTO dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.savePhone(token, dto));
    }

    @PatchMapping("/phone")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO dto, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.updatePhone(id, dto));
    }

}
