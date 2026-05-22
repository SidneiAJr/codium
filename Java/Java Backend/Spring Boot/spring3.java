public record DadosCadastroProduto(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull @DecimalMin(“1.00”) BigDecimal preco
) {
}
@PostMapping
public void cadastrar(@RequestBody DadosCadastroProduto dados) {
    repository.save(new Produto(dados));
}
