using System;
namespace Teste
{
    class Teste
    {
        public void calculo(){
        string Nome;
        double altura;
        
        Console.Write("Digite o Seu Nome:");
        Nome=Console.ReadLine();
        Console.Write("Digite sua altura");
        altura=Convert.ToDouble(Console.ReadLine());
        
        Console.WriteLine ($"Ola Bem vindo ao Sistema:{Nome}");
        Console.WriteLine ($"Ola sua Altura: {altura}!");
        }
        static void Main(string[]args){
            Teste meuObj = new Teste();
            meuObj.calculo();
        }

    }
}
