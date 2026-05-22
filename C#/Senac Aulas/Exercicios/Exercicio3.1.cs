using System;

public class OperadoresLogicos
{
    public static void Main(string[] args)
    {
     int idade = 18;
     bool maiorIdade = idade >= 18;
     bool concluiuMedio = true;
     bool estaEmFaculdade = false;
     Console.WriteLine(maiorIdade&& concluiuMedio &&estaEmFaculdade );

    }
}
