package estudo;
import java.util.ArrayList;
import java.util.List;

/*
============================
Codigo Estudo | Generics 
============================
*/
public class Estudo {

    public static void main(String[] args) {
         List<Object> listaQualquer = new ArrayList<>();
         List<String> listaString = new ArrayList<>();
         
         Pessoa p = new Pessoa("Joao", 30);
         p.falar(listaQualquer);
         p.falar(listaString);
         
         Gato g = new Gato("Gato",3);
         g.Miar(listaQualquer);
         g.Miar(listaString);
         
         Cachorro c = new Cachorro("Cachorro",3);
         c.Latir(listaQualquer);
         c.Latir(listaString);
         
         System.out.println("\n====================");
         for(Object item : listaQualquer){
             System.out.println(item);
         }
         System.out.println("\n====================");
         for(Object item: listaString){
             System.out.println(item);
         }
    }
}

class Entidade{
    private String Nome;
    private int Idade;

    public Entidade(String Nome, int Idade) {
        this.Nome = Nome;
        this.Idade = Idade;
    }

    public String getNome() {
        return Nome;
    }

    public int getIdade() {
        return Idade;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setIdade(int Idade) {
        this.Idade = Idade;
    }
}

class Pessoa extends Entidade{

    public Pessoa(String Nome,int Idade) {
        super(Nome, Idade);
    }
    
    public void falar(List<? super String>Lista){
        Lista.add("Olá Mundo"+getNome());
    }
}

class Cachorro extends Entidade{
    public Cachorro(String Nome,int Idade) {
        super(Nome, Idade);
    }
    
    public void Latir(List<?super String>Lista){
        Lista.add("Auuuuuuu"+getNome());
    }
}

class Gato extends Entidade{
    public Gato(String Nome,int Idade) {
        super(Nome, Idade);
    }
    
    public void Miar(List<?super String>Lista){
        Lista.add("Miauuuuuu"+getNome());
    }
}
