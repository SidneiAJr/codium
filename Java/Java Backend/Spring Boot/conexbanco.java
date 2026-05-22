@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeProduto;
    private Integer valor;
    private Integer quantEstoque;
    private String fabricante;

    // Getters and Setters
}
