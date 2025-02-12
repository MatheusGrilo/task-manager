package br.dev.grilo.taskmanager.user.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTO {

    private Long id;
    private Long createdAt;
    private Long updatedAt;
    private String countryCode;
    private String phoneNumber;
    private String label;
    private boolean isPublic;
    private boolean active;

}
