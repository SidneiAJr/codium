using System;

namespace Teste
{
    class Program
    {
        static void Main(string[]args)
        {
            int num=0;
            string nome = "Bruno";
            var aux=nome;
            int soma = 1+1;
            int mult = 2*2;
            int div = 2/2;
            int dimi = 2-2;
            var resultado = soma;
            var resultado2 = mult;
            var resultado3 = div;
            var resultado4 = dimi;
            
            Console.WriteLine("O Valor da Variavel:"+aux);
            Console.WriteLine("O Valor do calculo e:"+resultado);
            Console.WriteLine("O Valor do calculo e:"+resultado2);
            Console.WriteLine("O Valor do calculo e:"+resultado3);
            Console.WriteLine("O Valor do calculo e:"+resultado4);

        }
    }
}