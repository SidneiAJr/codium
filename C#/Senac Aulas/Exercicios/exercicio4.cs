
using System;

public class HelloWorld
{
    public static void Main(string[] args)
    {
        
        int hora = 20;
        
       if(hora>=6&&hora<12){
       Console.WriteLine ("Manhã"); 
       }else if(hora>= 12 && hora <18){
        Console.WriteLine ("Tarde");
       }else if(hora >= 18 && hora <= 23){
        Console.WriteLine ("E de noite");   
       }else{
        Console.WriteLine ("E de madruga");    
       }

        
    }
}
