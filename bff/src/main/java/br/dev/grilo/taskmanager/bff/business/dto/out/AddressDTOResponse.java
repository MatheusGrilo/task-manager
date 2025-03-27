package br.dev.grilo.taskmanager.bff.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTOResponse {

    private Long id;
    private Long userId;
    private String zipCode;
    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String extraInformation;
    private String country;
    private String countryProvince;
    private String city;
    private Long createdAt;
    private Long updatedAt;

}
