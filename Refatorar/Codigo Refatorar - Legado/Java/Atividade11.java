import static spark.Spark.*;
import java.sql.*;
import java.util.*;
import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        port(3000);
        Gson g = new Gson();

        get("/prod", (req, res) -> {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root", "");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM produtos");
            List<Map<String, Object>> l = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("id", rs.getInt("id"));
                m.put("n", rs.getString("nome"));
                m.put("p", rs.getDouble("preco"));
                l.add(m);
            }
            c.close();
            return g.toJson(l);
        });

        post("/prod", (req, res) -> {
            String b = req.body();
            Map<String, Object> m = g.fromJson(b, Map.class);
            String n = (String) m.get("n");
            Double p = (Double) m.get("p");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root", "");
            PreparedStatement st = c.prepareStatement("INSERT INTO produtos (nome, preco) VALUES (?, ?)");
            st.setString(1, n);
            st.setDouble(2, p);
            st.executeUpdate();
            c.close();
            return "ok";
        });

        put("/prod/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            String b = req.body();
            Map<String, Object> m = g.fromJson(b, Map.class);
            String n = (String) m.get("n");
            Double p = (Double) m.get("p");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root", "");
            PreparedStatement st = c.prepareStatement("UPDATE produtos SET nome=?, preco=? WHERE id=?");
            st.setString(1, n);
            st.setDouble(2, p);
            st.setInt(3, id);
            st.executeUpdate();
            c.close();
            return "ok";
        });

        delete("/prod/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root", "");
            PreparedStatement st = c.prepareStatement("DELETE FROM produtos WHERE id=?");
            st.setInt(1, id);
            st.executeUpdate();
            c.close();
            return "ok";
        });

        for (int i = 0; i < 200; i++) {
            final int idx = i;
            get("/t" + i, (req, res) -> "t" + idx);
        }
    }
}
