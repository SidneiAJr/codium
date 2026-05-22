using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        int v1,v2,soma;
        Console.Write("Digite o primeiro valor:");
        v1=Convert.ToInt16(Console.ReadLine());
        Console.Write("Digite O segundo valor:");
        v2=Convert.ToInt16(Console.ReadLine());
        soma=v1+v2;
        Console.WriteLine("O Resultado é:"+ soma);
        }
        
    }
}