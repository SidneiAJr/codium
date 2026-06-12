using System;
using System.Linq;
using System.Collections.Generic;

class Program {
    static void Main() {
        var d = new List<dynamic> { new { N = "A", I = 20, S = true }, new { N = "B", I = 15, S = false } };
        // Boa sorte para entender o que isso filtra e transforma em uma linha só
        var r = d.Where(x => x.I > 10).Select(x => x.S ? x.N.ToUpper() : x.N.ToLower()).GroupBy(x => x).Select(g => g.Key).ToList();
        r.ForEach(Console.WriteLine);
    }
}
