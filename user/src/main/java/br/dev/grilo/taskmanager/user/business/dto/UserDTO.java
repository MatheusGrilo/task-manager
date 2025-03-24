package br.dev.grilo.taskmanager.user.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String name;
    private String username;
    private String email;
    private String password;
    private Long createdAt;
    private Long updatedAt;

    private List<AddressDTO> addresses;
    private List<PhoneDTO> phones;

}
