import java.util.ArrayList;

class Program7 {
   public static void main(String[] args) {
       ArrayList<Integer> numeros = new ArrayList<>();

       for (int i = 0; i < 10; i++) {
           numeros.add(i);
           System.out.printf("%d ", i);
       }

       System.out.println("\nArray Completo: " + numeros);
   }
}
