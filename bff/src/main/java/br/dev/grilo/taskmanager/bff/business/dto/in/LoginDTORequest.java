package br.dev.grilo.taskmanager.bff.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTORequest {
    private String username;
    private String password;
}
