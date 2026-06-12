package com.mycompany.mavenproject1;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(unique = true, nullable = false)
    private String nome;

    @ToString.Exclude
    private String senha;

    @Column(name = "data_criacao")
    private LocalDateTime datacriacao;
}
