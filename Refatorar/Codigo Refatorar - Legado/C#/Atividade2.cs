using System;

class Processador {
    // Código perdeu toda a segurança de tipos do C# usando dynamic para tudo
    public dynamic ProcessarDados(dynamic dados) {
        dynamic resultado = dados.Valor * 10;
        if (dados.Status == "OK") {
            resultado += dados.Bonus;
        }
        return resultado;
    }
}
