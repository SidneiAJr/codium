using System;

public class HelloWorld
{
    public static void Main(string[] args)
    {
        int[] array = new int[5];
        for(int i=0; i<array.Length; i++){
            array[i] = i * 2;  
            Console.WriteLine(array[i]);
        }
    }
}
