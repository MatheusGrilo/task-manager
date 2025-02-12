package br.dev.grilo.taskmanager.user.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;
    private String zipCode;
    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String extraInformation;
    private String country;
    private String countryProvince;
    private Integer city;

}
