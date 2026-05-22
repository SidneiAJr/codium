using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        int num;
        
       Console.Write("Digite um numero:");
        num=Convert.ToInt16(Console.ReadLine());
        switch(num){
            case 1:
            Console.WriteLine("Pokemom Escolhido e Charizard");
            break;
            case 2:
            Console.WriteLine("Pokemom Escolhido e Cyndaquil");
            break;
            case 3:
            Console.WriteLine("Pokemom Escolhido e o Pikachu");
            break;
            default:
            Console.WriteLine("Pokemom Não listado");
            break;
        }
        if(num>4){
           Console.WriteLine("Pokemom Não listado"); 
        }else {
          Console.WriteLine("Pokemom Não Existe");
        }
        
        }
        
    }
}
