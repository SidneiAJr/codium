using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        int num=9;
        
        while(num>=0){
        if(num==0){
            Console.WriteLine("Boom");
        }else if(num %2 ==0){
           Console.WriteLine("TIC");  
        }else{
          Console.WriteLine("TAC");    
        }
       num--;
       
        }
        // Outra  Maneira de fazer é:
        //while(segundo>0)
        //if(Segundo%2==0)
         for(int voltas=0; voltas<5; voltas++){
        Console.WriteLine($"Voce testou{voltas}as voltas!");
        voltas=voltas+1;
        voltas--;
        
        }
        
    }
}
