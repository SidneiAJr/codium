import static spark.Spark.*;
import java.util.*;
import java.sql.*;

public class Principal {
    public static void main(String[] args) {
        port(3000);
        List<Map<String, Object>> a = new ArrayList<>();
        List<Map<String, Object>> b = new ArrayList<>();
        Map<String, String> c = new HashMap<>();
        c.put("url", "jdbc:mysql://localhost:3306/loja");
        c.put("user", "");
        c.put("pass", "");

        get("/", (req, res) -> "ok");

        get("/lista", (req, res) -> {
            List<Map<String, Object>> x = new ArrayList<>();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(c.get("url"), c.get("user"), c.get("pass"));
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM produtos");
                while (rs.next()) {
                    Map<String, Object> m = new HashMap<>();
                    m.put("id", rs.getInt("id"));
                    m.put("nome", rs.getString("nome"));
                    m.put("preco", rs.getDouble("preco"));
                    x.add(m);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                return "erro";
            }
            return new Gson().toJson(x);
        });

        post("/add", (req, res) -> {
            String n = req.queryParams("nome");
            String p = req.queryParams("preco");
            if (n == null || p == null) return "faltou";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(c.get("url"), c.get("user"), c.get("pass"));
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO produtos (nome, preco) VALUES (?, ?)");
                stmt.setString(1, n);
                stmt.setDouble(2, Double.parseDouble(p));
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                return "erro";
            }
            return "ok";
        });

        for (int i = 0; i < 100; i++) {
            String r = "/rota" + i;
            get(r, (req, res) -> "rota " + i);
        }

        get("/usuario", (req, res) -> {
            String id = req.queryParams("id");
            String sql = "SELECT * FROM usuarios WHERE id=" + id;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(c.get("url"), c.get("user"), c.get("pass"));
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                Map<String, Object> m = new HashMap<>();
                if (rs.next()) {
                    m.put("id", rs.getInt("id"));
                    m.put("nome", rs.getString("nome"));
                }
                rs.close();
                stmt.close();
                conn.close();
                return new Gson().toJson(m);
            } catch (Exception e) {
                return "erro";
            }
        });

        post("/update", (req, res) -> {
            String id = req.queryParams("id");
            String n = req.queryParams("nome");
            if (id == null || n == null) return "faltou";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(c.get("url"), c.get("user"), c.get("pass"));
                PreparedStatement stmt = conn.prepareStatement("UPDATE produtos SET nome = ? WHERE id = ?");
                stmt.setString(1, n);
                stmt.setInt(2, Integer.parseInt(id));
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                return "erro";
            }
            return "ok";
        });

        delete("/del", (req, res) -> {
            String id = req.queryParams("id");
            if (id == null) return "faltou";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(c.get("url"), c.get("user"), c.get("pass"));
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM produtos WHERE id = ?");
                stmt.setInt(1, Integer.parseInt(id));
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                return "erro";
            }
            return "ok";
        });

        for (int i = 0; i < 200; i++) {
            final int idx = i;
            get("/teste" + i, (req, res) -> "teste " + idx);
        }
    }
}
