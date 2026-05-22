using System;

public class OperadoresLogicos
{
    public static void Main(string[] args)
    {
       
        bool temCarteira = false;
        int idade = 26;
        bool maiorIdade= idade >= 18;

        Console.WriteLine("Para poder dirigir voce precisa ter 18 anos ou mais",maiorIdade);
        Console.WriteLine("Voce tem carteira",temCarteira);
        

        //Operador Logico E(AND) retorna true apenas se todos forem true
        Console.WriteLine(temCarteira==true&& maiorIdade == true);
       
        if(idade>18){
            Console.WriteLine("Voce e maior de idade!");
        }else{
           Console.WriteLine("Voce e menor de idade!"); 
        }

       //true&&true TRUE
        //false&false FALSE
       //false&&true FALSE
        //false&&false FALSE
        //true || true
        //true || true 
        //true || false
        //false || false


        //!true retorna false
        //!false retorna true



        


      
      

    }
}
