using System;
namespace Teste
{
    class Teste
    {
        public double calculo(double num1,double num2){
        double resultado= num1 + num2;
        return resultado;
        }
        static void Main(string[]args){
            Teste meuObj = new Teste();
            double numero = meuObj.calculo(5,6);
            Console.WriteLine(numero+10);
        }

    }
}
