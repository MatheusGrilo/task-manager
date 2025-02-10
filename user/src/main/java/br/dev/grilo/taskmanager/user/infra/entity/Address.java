package br.dev.grilo.taskmanager.user.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "zip_code", length = 32)
    private String zip_code;
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
    private String extra_information;
    @Column(name = "country", length = 9)
    private String country;
    @Column(name = "country_province", length = 2)
    private String country_province;
    @Column(name = "city", length = 128)
    private Integer city;
}
