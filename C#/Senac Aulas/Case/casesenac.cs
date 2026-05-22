using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        
        string meuTime;
        
        Console.Write("Selecione um Numero de 1 a 3: ");
        meuTime=Convert.ToString(Console.ReadLine());
        switch(meuTime){
            case "1":
            Console.WriteLine("Meu time e gremio");
            break;
            case "2":
            Console.WriteLine("Meu time e Inter");
            break;
            case "3":
            Console.WriteLine("Meu time e Flamengo");
            break;
            default:
            Console.WriteLine("Time Nao encontrado!");
            break;
        }

        }
        
    }
}
