using System;

class Teste2{
//Tudo que eu quero que seja Mostrado no terminal,precisa ir dentro de main(ou ser chamado dentro dela)
//Tudo em C# vai dentro do que chamamos de classe
//Imprime no console a frase Hello Word("Hello Word")
   
   static void Main(){
    int teste2;
    int teste3;
    bool teste4;
    string teste5;
    char teste6;
    int soma;
   //Tipo de variaveis
   //Comando console readline le o que o usuario inputa.
   //comando convert.Toint16 converte para um inteiro.
   //

    Console.Write("Digite um Valor");
    teste2=Convert.ToInt16(Console.ReadLine());
    Console.Write("Digite um Valor");
    teste3=Convert.ToInt16(Console.ReadLine());
    soma=teste2+teste3;
    Console.WriteLine("O valor e:{0}",soma);


   }
   
   }
