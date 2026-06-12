package teste_codigos;
import java.util.ArrayList;
import java.util.List;

public class Teste_codigos {
    
public static void main(String[] args) {
    Sitema s = new Sitema();
        s.adicionar("O Programador Pragmático", "Andy Hunt");
        s.adicionar("Clean Code", "Robert Martin");
        s.listarLivros();

        s.registrarEmprestimo(0);
        s.listarEmprestimos();
}
}
class Sitema {
    private List<Object> livros = new ArrayList<>();
    private List<Object> emp = new ArrayList<>();

    public void adicionar(String titulo, String autor) {
        class Livro {
            String t;
            String a;
            Livro(String t, String a) { this.t = t; this.a = a; }
        }
        livros.add(new Livro(titulo, autor));
    }

    public void listarLivros() {
        for (Object obj : livros) {
            System.out.println(obj.toString());
        }
    }

    public void registrarEmprestimo(int indice) {
        if (indice < livros.size()) {
            emp.add(livros.get(indice));
            System.out.println("Emprestimo registrado.");
        } else {
            System.out.println("Indice invalido");
        }
    }

    public void listarEmprestimos() {
        for (Object obj : emp) {
            System.out.println(obj.toString());
        }
    }
}


