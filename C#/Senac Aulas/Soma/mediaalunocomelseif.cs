using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
       //tipo de variaveis
        int n1,n2,n3;
        int soma;
        //input de informação
        Console.Write("Digite a Nota 1: ");
        n1=Convert.ToInt16(Console.ReadLine());
        Console.Write("Digite a Nota 2: ");
        n2=Convert.ToInt16(Console.ReadLine());
        Console.Write("Digite a Nota 3:  ");
        n3=Convert.ToInt16(Console.ReadLine());
       //condição do aluno aprovado,reprovado,em recuperaçãp
       soma = (n1+n2+n3)/3;
       if(soma>5){
        Console.Write("Aluno Aprovado");
       }else if(soma<5){
         Console.Write("Aluno em Recuperação");  
       }else{
        Console.Write("Aluno reprovado ");     
       }
      
      

        }
        
    }
}
