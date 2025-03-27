package br.dev.grilo.taskmanager.bff.controller;

import br.dev.grilo.taskmanager.bff.business.UserService;
import br.dev.grilo.taskmanager.bff.business.dto.in.AddressDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.LoginDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.PhoneDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.in.UserDTORequest;
import br.dev.grilo.taskmanager.bff.business.dto.out.AddressDTOResponse;
import br.dev.grilo.taskmanager.bff.business.dto.out.PhoneDTOResponse;
import br.dev.grilo.taskmanager.bff.business.dto.out.UserDTOResponse;
import br.dev.grilo.taskmanager.bff.infra.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User Sign Up, Login, Update and Delete")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Save a new user", description = "Create a new user in the database")
    @ApiResponse(responseCode = "200", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "User already exists")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<UserDTOResponse> saveUser(@RequestBody UserDTORequest userDTORequest) {
        return ResponseEntity.ok(userService.saveUser(userDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "User Login")
    @ApiResponse(responseCode = "200", description = "User logged in successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public String login(@RequestBody LoginDTORequest loginDTORequest) {
        return userService.userLogin(loginDTORequest);
    }

    @GetMapping
    @Operation(summary = "Find user", description = "Find an user by username")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<UserDTOResponse> findUserByUsername(@RequestParam("username") String username,
                                                              @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.findUserByUsername(username, token));
    }

    @DeleteMapping("{username}")
    @Operation(summary = "Delete user", description = "Delete an user by username")
    @ApiResponse(responseCode = "200", description = "User deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username,
                                                     @RequestHeader(name  = "Authorization", required = false) String token) {
        userService.deleteUserByUsername(username, token);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @Operation(summary = "Update user", description = "Update user data")
    @ApiResponse(responseCode = "200", description = "Updated user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<UserDTOResponse> updateUser(@RequestBody UserDTORequest dto,
                                                      @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateUser(dto, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Create user's address", description = "Create user's address")
    @ApiResponse(responseCode = "200", description = "Address created")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<AddressDTOResponse> saveAddress(@RequestBody AddressDTORequest dto,
                                                          @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.saveAddress(dto, token));
    }

    @PatchMapping("/address")
    @Operation(summary = "Update user's address", description = "Update user's address")
    @ApiResponse(responseCode = "200", description = "Address updated")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestBody AddressDTORequest dto,
                                                            @RequestParam("id") Long id,
                                                            @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateAddress(id, dto, token));
    }

    @PostMapping("/phone")
    @Operation(summary = "Create user's phone", description = "Create user's phone")
    @ApiResponse(responseCode = "200", description = "Phone created")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<PhoneDTOResponse> savePhone(@RequestBody PhoneDTORequest dto,
                                                      @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.savePhone(dto, token));
    }

    @PatchMapping("/phone")
    @Operation(summary = "Update user's phone", description = "Update user's phone")
    @ApiResponse(responseCode = "200", description = "Phone created")
    @ApiResponse(responseCode = "404", description = "Phone not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<PhoneDTOResponse> updatePhone(@RequestBody PhoneDTORequest dto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader(name  = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updatePhone(id, dto, token));
    }

}
