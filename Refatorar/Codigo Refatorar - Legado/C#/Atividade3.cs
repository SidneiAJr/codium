using System;
using System.IO;

class Leitor {
    public string LerArquivo(string caminho) {
        try {
            return File.ReadAllText(caminho);
        }
        catch (Exception) {
            // Engole o erro e o sistema morre em silêncio sem ninguém saber o motivo
            return ""; 
        }
    }
}
