using System;
namespace Teste
{
    class Program
    {
        static void soma()
        {
            int v1=10,v2=20,res;
            res=v1+v2;
            Console.WriteLine("O resultado é {0}", res);
        }
        static void dimi()
        {
            int v3=50,v4=10,res2;
            res2=v3-v4;
            Console.WriteLine("O resultado é {0}", res2);
        }
        static void Main()
        {
            int menu;
            Console.Write("Digite uma opção de 1 a 3: ");
            menu=Convert.ToInt16(Console.ReadLine());
            switch(menu)
            {
            case 1:
                soma();  //chama a função similar a JS
                break;
            case 2:
                dimi(); //chama a função similar a JS
                break;
            default:
                Console.WriteLine("Opção inválida.");
                break;
            }
        }
    }
}
