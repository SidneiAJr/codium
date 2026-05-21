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
      Caixa<String> caixaDeTexto = new Caixa<>("Martelo");
      caixaDeTexto.guardar("Martelo");
      System.out.println(caixaDeTexto.Pegar());
      Caixa<Integer> caixaDeTexto2 = new Caixa<>(0);
      caixaDeTexto2.guardar(42);
      System.out.println(caixaDeTexto2.Pegar());
      Caixa<Double> caixaDePreco = new Caixa<>(0.0);
      caixaDePreco.guardar(19.90);
      System.out.println(caixaDePreco.Pegar());  // 19.9
    }
}


class Caixa<T>{
    private T item;

    public Caixa(T item) {
        this.item = item;
    }
    
    public void guardar(T item){
        this.item = item;
    }
    
    public T Pegar(){
        return item;
    }
    
    public boolean estaVazio(){
        return item == null;
    }
}
