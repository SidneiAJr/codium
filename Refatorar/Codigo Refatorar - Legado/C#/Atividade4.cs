using System;
using System.Data.SqlClient;

class Banco {
    public void InserirRegistro(string query) {
        // Sem bloco 'using' ou Close(). A conexão fica aberta para sempre travando o banco
        SqlConnection conn = new SqlConnection("Server=myServerAddress;Database=myDataBase;");
        conn.Open();
        SqlCommand cmd = new SqlCommand(query, conn);
        cmd.ExecuteNonQuery();
    }
}
