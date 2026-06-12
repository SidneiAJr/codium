package com.mycompany.mavenproject1;

import java.sql.*;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;


public class a {
    public static void main(String[] args) {
         Usuario usuario = Usuario.builder()
            .nome("")
            .email("")
            .senha("")
            .datacriacao(LocalDateTime.now())
            .build();
        
        System.out.println(usuario);
   }
}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Usuarios")
class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false,length = 100)
    private String email;
    @Column(unique = true , nullable = false)
    private String nome;
    @ToString.Exclude
    private String senha;
    @Column(name = "data_criacao")
    private LocalDateTime datacriacao;
}
