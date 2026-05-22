//comentario em um linha //
// Comentario de mais de uma linha//
using System;

public class HelloWorld
{
    public static void Main(string[] args)
    {
        int numero1= 15;
        int numero2 = 30;
        int numero3 = 40;
        int numero4 = 50;
        int soma;
        int soma2;
        int soma3;
        int dividir;
        int multiplicar;
        int dimiuir;
        //int inteiro
        //double numero quebrado
        //char caractere
        //string texto
        //bool verdadeiro falso
        
        Console.WriteLine ("=====Bem vindo======");
      Console.WriteLine("O Numero e:{0}",numero1);
      Console.WriteLine("O Numero e:{0}",numero2);
      Console.WriteLine("O Numero e:{0}",numero3);
      Console.WriteLine("O Numero e:{0}",numero4);
      soma = numero1+numero2;
      soma2 = numero2+numero3;
      soma3 = numero3+numero4 ;
      Console.WriteLine("A soma e:{0}",soma);
      Console.WriteLine("A soma e:{0}",soma2);
      Console.WriteLine("A soma e:{0}",soma3);
      dividir= numero1/numero2/numero3/numero4;
      multiplicar = numero1*numero2*numero3*numero4;
      dimiuir = numero1-numero2-numero3-numero4;
       Console.WriteLine("A Divisão e:{0}",dividir);
       Console.WriteLine("A Multiplicacao e:{0}",multiplicar);
       Console.WriteLine("Subtracao:{0}",dimiuir);
       if (soma==4){
        Console.WriteLine("A soma e Verdadeira");
       }else if(soma<2){
        Console.WriteLine("Quem ganha vai perde, quem perde vai ganha");   
       }else{
         Console.WriteLine("A soma e Falsa");  
       }
        Console.WriteLine("===Fim do programa===");
      
      

    }
}

