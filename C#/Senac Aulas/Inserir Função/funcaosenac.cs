using System;
namespace Teste
{
    class Teste
    {
        public void calculo(){
        int minhaBase = 5;
        int altura = 10;
        int area = minhaBase*altura;
        Console.WriteLine("A Area total do retangulo e:{0}",area);
        }
        static void Main(string[]args){
            Teste meuObj = new Teste();
            meuObj.calculo();
        }

    }
}
