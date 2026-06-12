using System;

class GerenciadorTotal {
    // Uma única classe gerencia usuário, valida, calcula e manda log. Quebra o SRP.
    public void ExecutarTudo(string nome, double valor) {
        if (nome != "") {
            Console.WriteLine("Usuário válido");
            double imposto = valor * 0.15;
            Console.WriteLine($"Calculado: {imposto}");
            System.IO.File.WriteAllText("log.txt", $"Processado {nome}");
        }
    }
}
