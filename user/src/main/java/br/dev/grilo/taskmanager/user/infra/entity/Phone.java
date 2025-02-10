package br.dev.grilo.taskmanager.user.infra.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
@Builder
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="created_at")
    private Long createdAt;
    @Column(name="updated_at")
    private Long updatedAt;
    @Column(name = "country_code", length = 3)
    private String countryCode;
    @Column(name = "phone_number", length = 32)
    private String phoneNumber;
    @Column(name = "label", length = 32)
    private String label;
    @Column(name = "public", columnDefinition = "boolean default false")
    private boolean isPublic;
    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;
}
