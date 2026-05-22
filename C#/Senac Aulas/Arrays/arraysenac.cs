using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        int [] arrayts = new int[5]{1,2,3,4,5};
        
        
        Console.WriteLine("O Arrey e:{0}",arrayts[0]);
        Console.WriteLine("O Arrey e:{0}",arrayts[1]);
        Console.WriteLine("O Arrey e:{0}",arrayts[2]);
        Console.WriteLine("O Arrey e:{0}",arrayts[3]);
        Console.WriteLine("O Arrey e:{0}",arrayts[4]);
        
        }
        
    }
}
using System;
namespace Teste
{
    class Program
    {
        static void Main()
        {
        string [] arrayts = new string[5]{"1-BF3","2-WF","3-Warthunder","4-BF1","5-PB"};
        
        
        
        for(int i = 0; i<arrayts.Length; i++){
        Console.WriteLine("A posicao e:{0}",(arrayts[i]));
        }
        }
        
    }
}
