package br.dev.grilo.taskmanager.bff.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOResponse {

    private String name;
    private String username;
    private String email;
    private String password;
    private Long createdAt;
    private Long updatedAt;

    private List<AddressDTOResponse> addresses;
    private List<PhoneDTOResponse> phones;

}
