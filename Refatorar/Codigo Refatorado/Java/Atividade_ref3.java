package teste_codigos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
========================================================================
Codigo Refatorado | Atividade 3
- Adiciona Menu para o usuario Adicionar, Listar e Verificar emprestimo
- Adiciona Scanner Menu
- Adiciona um Variavel chama op vulgo Opção
- Loop While para verificar as opções dentro do mundo
========================================================================

*/


public class Teste_codigos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca minhaBiblioteca = new Biblioteca();
        int op = 0;

        System.out.println("==================================");
        System.out.println("Bem vindo | Codigo Refatorado");
        System.out.println("Menu Selecione | Opcoes");
        System.out.println("1 - Adicionar Novo Livro");
        System.out.println("2 - Listar todos os Livros");
        System.out.println("3 - Registrar Emprestimo");
        System.out.println("4 - Listar Emprestimos");
        System.out.println("5 - Sair");
        System.out.println("==================================");

        while (op != 5) {
            System.out.print("Escolha: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    Livros novoLivro = new Livros(titulo, autor);
                    minhaBiblioteca.AdicionarLivros(novoLivro);
                    break;
                case 2:
                    minhaBiblioteca.ListarLivros();
                    break;
                case 3:
                    System.out.print("Indice do livro: ");
                    int indice = scanner.nextInt();
                    minhaBiblioteca.RegistrarEmprestimo(indice);
                    break;
                case 4:
                    minhaBiblioteca.ListarEmprestimos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        scanner.close();
    }
}

// suas classes Livros e Biblioteca permanecem iguais

class Livros{
    private String Titulo_livro;
    private String autor;

    public Livros(String Titulo_livro, String autor) {
        this.Titulo_livro = Titulo_livro;
        this.autor = autor;
    }

    public String getTitulo_livro() {
        return Titulo_livro;
    }

    public String getAutor() {
        return autor;
    }

    public void setTitulo_livro(String Titulo_livro) {
        this.Titulo_livro = Titulo_livro;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    @Override
    public String toString(){
        return Titulo_livro + "-" + autor;
    }
}

class Biblioteca{
    private List<Livros> livros = new ArrayList<>();
    private List<Livros> emprestimos = new ArrayList<>();
    
    public void AdicionarLivros(Livros livro){
        livros.add(livro);
    }
    
    public void ListarLivros(){
        System.err.println("Livros Disponiveis");
        for(Livros livros:livros){
            System.err.println("- "+livros);
        }
    }
    public void RegistrarEmprestimo(int indice){
        if(indice>=0&&indice<livros.size()){
            Livros livro = livros.get(indice);
            emprestimos.add(livro);
            System.err.println("Emprestimo Realizado com sucesso!"+livro.getTitulo_livro());
        }else{
            System.out.println("Indice Invalido. Livro não Encontrado");
        }
    }
    
    public void ListarEmprestimos(){
        System.out.println("Livros Emprestimos");
        for(Livros livro: emprestimos){
            System.err.println(" - "+livros);
        }
    }
}


