import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(3000);

        get("/user", (req, res) -> {
            String x = req.queryParams("id");
            return "Usuario " + x;
        });

        post("/user", (req, res) -> {
            String a = req.queryParams("nome");
            String b = req.queryParams("email");
            System.out.println("Salvou " + a);
            return "ok";
        });

        get("/lista", (req, res) -> {
            String sql = "SELECT * FROM usuarios";
            // simulando banco
            return "[{nome:'Joao'}]";
        });
    }
}
