package br.dev.grilo.taskmanager.bff.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTOResponse {

    private Long id;
    private Long userId;
    private Long createdAt;
    private Long updatedAt;
    private String countryCode;
    private String phoneNumber;
    private String label;
    private boolean isPublic;
    private boolean active;

}
