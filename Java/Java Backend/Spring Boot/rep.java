import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Métodos customizados de busca podem ser adicionados aqui
}
