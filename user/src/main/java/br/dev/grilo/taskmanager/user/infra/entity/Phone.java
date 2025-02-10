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
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_code", length = 3)
    private String country_code;
    @Column(name = "phonenum", length = 32)
    private String phonenum;
    @Column(name = "label", length = 32)
    private String label;
}
