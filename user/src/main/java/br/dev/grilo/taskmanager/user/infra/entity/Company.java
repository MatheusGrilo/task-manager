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
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "created_at")
    private String created_at;
    @Column(name = "updated_at")
    private String updated_at;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "active")
    private boolean active;
    @Column(name = "blocked")
    private boolean blocked;
    @Column(name = "website")
    private String website;
    @Column(name = "logo")
    private String logo;
    @Column(name = "description")
    private String description;


}
