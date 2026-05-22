
using System;
class HelloWorld {
  static void Main() {
    
    Console.WriteLine("Favor Insira um numero");
    int num = int.Parse(Console.ReadLine());
    
    Console.WriteLine("Tabuada dos guri");
    
    for(int i =0; i<=10; i++){
        Console.WriteLine($"{num}x{i}={num*i}");
    }
    
  }
}
