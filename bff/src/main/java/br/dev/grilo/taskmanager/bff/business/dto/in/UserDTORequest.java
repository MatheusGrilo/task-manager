package br.dev.grilo.taskmanager.bff.business.dto.in;

import br.dev.grilo.taskmanager.bff.business.dto.out.AddressDTOResponse;
import br.dev.grilo.taskmanager.bff.business.dto.out.PhoneDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTORequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private Long createdAt;
    private Long updatedAt;

    private List<AddressDTOResponse> addresses;
    private List<PhoneDTOResponse> phones;

}
