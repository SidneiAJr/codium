using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        int mes;
        

        
        Console.Write("Digite um mes");
        mes=Convert.ToInt16(Console.ReadLine());
        switch(mes){
            case 1:
            Console.WriteLine("O mes é Janeiro");
            break;
             case 2:
            Console.WriteLine("O mes é Fev");
            break;
            case 3:
            Console.WriteLine("O mes é Março");
            break;
             case 4:
            Console.WriteLine("O mes é Abril");
            break;
             case 5:
            Console.WriteLine("O mes é Maio");
            break;
             case 6:
            Console.WriteLine("O mes é Jun");
            break;
             case 7:
            Console.WriteLine("O mes é Jul");
            break;
             case 8:
            Console.WriteLine("O mes é Ago");
            break;
             case 9:
            Console.WriteLine("O mes é Set");
            break;
             case 10:
            Console.WriteLine("O mes é Out");
            break;
             case 11:
            Console.WriteLine("O mes é Nov");
            break;
             case 12:
            Console.WriteLine("O mes é Dez");
            break;
            default:
            Console.WriteLine("Mes Invalido");
            break;
        }
        
        Console.WriteLine("O mes escolhido:{0}",mes);
      

        }
        
    }
}
