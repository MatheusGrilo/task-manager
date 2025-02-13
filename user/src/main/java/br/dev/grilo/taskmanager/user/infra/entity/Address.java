package br.dev.grilo.taskmanager.user.infra.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "zip_code", length = 32)
    private String zipCode;
    // Avenue, Street, etc
    @Column(name = "address", length = 128)
    private String address;
    // Number, Apartment, etc
    @Column(name = "number", length = 128)
    private String number;
    @Column(name = "complement", length = 128)
    private String complement;
    @Column(name = "neighborhood", length = 128)
    private String neighborhood;
    @Column(name = "extra_information", length = 128)
    private String extraInformation;
    @Column(name = "country")
    private String country;
    @Column(name = "country_province")
    private String countryProvince;
    @Column(name = "city", length = 128)
    private String city;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
    @Column(name= "user_id")
    private Long userId;
}
