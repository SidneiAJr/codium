using System;

public class OperadoresLogicos
{
    public static void Main(string[] args)
    {
     int idade;
     string nome;
     string faculdade;
     
      Console.WriteLine("Qual e seu nome?");
      nome=(Console.ReadLine());
      Console.WriteLine("Qual e sua idade?");
      idade=Convert.ToInt16(Console.ReadLine());
      Console.WriteLine("Voce termino a faculdade");
      faculdade=(Console.ReadLine());
      
      if(idade>18){
        Console.WriteLine("Parabens Agora voce pode cursar a faculdade");  
      }else{
      Console.WriteLine("Desculpe Voce não pode cursar a faculdade!"); 
      }
      if(faculdade=="sim"){
          Console.WriteLine("Parabens Agora voce pode cursar a faculdade");  
      }else{
          Console.WriteLine("Desculpe Voce nao pode cursar a faculdade!");  
      }
      
      
      

        
      
      

    }
}
