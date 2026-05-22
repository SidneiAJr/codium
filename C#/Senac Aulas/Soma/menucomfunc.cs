using System;
namespace Teste
{
    class Program
    {
        static void soma()
        {
            int v1=10,v2=20,res;
            res=v1+v2;
            Console.WriteLine("O resultado é{0}:", res);
        }
        static void Main()
        {
            int menu;
            Console.Write("Digite um mes");
            menu=Convert.ToInt16(Console.ReadLine());
            switch(menu)
            {
            case 1:
                soma();  // Chama a FunçãoA
                break;
                default:
                Console.WriteLine("Opção inválida.");
                break;
                
            }
        }
    }
}
