
using System;

public class HelloWorld
{
    public static void Main(string[] args)
    {
        Console.WriteLine ("================Bem vindo==============");
        int num1 = 10;
        int num2 = 20;

        Console.WriteLine("A comparacao E:{0}",num1>num2);
        Console.WriteLine("A comparacao E:{0}",num1<num2);
        Console.WriteLine("A comparacao E:{0}",num1 == num2);
        Console.WriteLine("A comparacao E:{0}",num1 != num2);
        Console.WriteLine("A comparacao E:{0}",num1>=num2);
        Console.WriteLine("A comparacao E:{0}",num1<=num2);
        Console.WriteLine ("================Fim==============");
    }
}

