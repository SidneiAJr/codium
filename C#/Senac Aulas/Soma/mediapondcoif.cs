using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        int n1,n2,n3;
        string nome;

        Console.Write("Digite o Seu Nome:");
        nome=Console.ReadLine();
        Console.Write("Digite A Primeira nota:");
        n1=Convert.ToInt16(Console.ReadLine());
        Console.Write("Digite A Segunda Nota:");
        n2=Convert.ToInt16(Console.ReadLine());
        Console.Write("Digite A Terceira Nota:");
        n3=Convert.ToInt16(Console.ReadLine());
        var media = (n1+n2+n3)/3;
        if(media>6){
        Console.Write("Aluno Aprovado!");   
        }else if(media >4){
        Console.Write("Aluno Em recuperacao!");    
        }else{
        Console.Write("Aluno Reprovado!");       
        }
        
        Console.WriteLine("\nSeu Nome{0}" ,nome);
        Console.WriteLine("\nSua Media é{0}",media);
      

        }
        
    }
}
