package br.dev.grilo.taskmanager.tasks.business.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private String password;

}
