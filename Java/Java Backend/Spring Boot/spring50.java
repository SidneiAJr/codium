@Entity
public class Usuario {
    @Id
    private Long id;
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}

@Entity
public class Endereco {
    @Id
    private Long id;
    private String logradouro;
    private String bairro;
    private String cidade;
    // outros atributos...
    
    @OneToOne(mappedBy = "endereco")
    private Usuario usuario;
}
