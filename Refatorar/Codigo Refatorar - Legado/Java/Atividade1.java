/*
===================================
Refatorando | Codigo Java 
1 - I 0 até 10 | For Vai de 0 a 10 
2 - J 0 até 10 | For vai de 0 a 10
3 - If i+j for maior 5 | Então ele faz o Resto de i da soma 2 e compara com 0
4 - Se for par ele imprime i se for impar ele imprime j
5 - Outro for que incrementa k++
*/



class Main {
    public static void main(String[] args) {

for(int i=0;i<10;i++){
 for(int j=0;j<10;j++){
  if(i+j>5){
   if(i%2==0){
    System.out.println(i);
   }else{
    System.out.println(j);
   }
  }else{
   for(int k=0;k<5;k++){
    if(k==i){
     System.out.println(k);
    }
   }
  }
 }
}
    }
}
