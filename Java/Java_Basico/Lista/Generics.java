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
        List<String> nomes = new ArrayList<>();
        nomes.add("Joao");
        nomes.add(123); // Erro Numero
        System.out.println("Lista: "+nomes);
    }
    
}
