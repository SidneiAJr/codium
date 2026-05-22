using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        decimal n1,n2;

        Console.Write("Digite A Primeira nota:");
        n1=decimal.Parse(Console.ReadLine());
        Console.Write("Digite A Segunda Nota:");
        n2=decimal.Parse(Console.ReadLine());
        var res = n1+n2;
        
        
        Console.WriteLine("A soma e:{0,0}",res);
        
        
      

        }
        
    }
}
