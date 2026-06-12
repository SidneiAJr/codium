// Sistema.java - Sistema de Biblioteca/Pedidos/Estoque Tudo Misturado
// Autor: Desconhecido
// Data: Não sei
// TODO: Refatorar tudo

import java.util.*;
import java.text.*;
import java.io.*;

public class Sistema {
    
    static ArrayList lista1 = new ArrayList();
    static ArrayList lista2 = new ArrayList();
    static ArrayList lista3 = new ArrayList();
    static ArrayList lista4 = new ArrayList();
    static ArrayList lista5 = new ArrayList();
    static ArrayList logs = new ArrayList();
    
    static int contador = 0;
    static int c = 0;
    static int x = 0;
    static int y = 0;
    static int z = 0;
    static String temp = "";
    static Double total = 0.0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        
        while(op != 9) {
            System.out.println("===================");
            System.out.println("1 - Livros");
            System.out.println("2 - Usuarios");
            System.out.println("3 - Pedidos");
            System.out.println("4 - Relatorios");
            System.out.println("5 - Estoque");
            System.out.println("6 - Vendas");
            System.out.println("7 - Backup");
            System.out.println("8 - Config");
            System.out.println("9 - Sair");
            System.out.println("===================");
            System.out.print("Opcao: ");
            op = sc.nextInt();
            sc.nextLine();
            
            if(op == 1) menuLivros();
            if(op == 2) menuUsuarios();
            if(op == 3) menuPedidos();
            if(op == 4) menuRelatorios();
            if(op == 5) menuEstoque();
            if(op == 6) menuVendas();
            if(op == 7) backup();
            if(op == 8) config();
        }
    }
    
    static void menuLivros() {
        Scanner sc = new Scanner(System.in);
        int o = 0;
        
        while(o != 5) {
            System.out.println("----- LIVROS -----");
            System.out.println("1 - Add");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Remover");
            System.out.println("5 - Voltar");
            o = sc.nextInt();
            sc.nextLine();
            
            if(o == 1) addLivro();
            if(o == 2) listarLivros();
            if(o == 3) buscarLivro();
            if(o == 4) removerLivro();
        }
    }
    
    static void addLivro() {
        Scanner sc = new Scanner(System.in);
        Map m = new HashMap();
        
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Editora: ");
        String editora = sc.nextLine();
        System.out.print("Ano: ");
        String ano = sc.nextLine();
        System.out.print("Preco: ");
        String preco = sc.nextLine();
        System.out.print("Estoque: ");
        String estoque = sc.nextLine();
        
        m.put("id", id);
        m.put("titulo", titulo);
        m.put("autor", autor);
        m.put("editora", editora);
        m.put("ano", ano);
        m.put("preco", preco);
        m.put("estoque", estoque);
        m.put("data_cadastro", new Date().toString());
        
        lista1.add(m);
        logs.add("Add livro: " + titulo + " em " + new Date());
        
        System.out.println("Livro adicionado! ID: " + id);
    }
    
    static void listarLivros() {
        if(lista1.isEmpty()) {
            System.out.println("Nenhum livro.");
            return;
        }
        
        System.out.println("====================================");
        for(int i=0; i<lista1.size(); i++) {
            Map m = (Map) lista1.get(i);
            System.out.println("ID: " + m.get("id"));
            System.out.println("Titulo: " + m.get("titulo"));
            System.out.println("Autor: " + m.get("autor"));
            System.out.println("Preco: R$ " + m.get("preco"));
            System.out.println("Estoque: " + m.get("estoque"));
            System.out.println("------------------------------------");
        }
    }
    
    static void buscarLivro() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o titulo ou ID: ");
        String busca = sc.nextLine();
        boolean achou = false;
        
        for(int i=0; i<lista1.size(); i++) {
            Map m = (Map) lista1.get(i);
            if(m.get("titulo").toString().toLowerCase().contains(busca.toLowerCase()) ||
               m.get("id").toString().equals(busca)) {
                System.out.println("Encontrado: " + m.get("titulo") + " - R$ " + m.get("preco"));
                achou = true;
            }
        }
        if(!achou) System.out.println("Nao encontrado.");
    }
    
    static void removerLivro() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID do livro: ");
        String id = sc.nextLine();
        
        for(int i=0; i<lista1.size(); i++) {
            Map m = (Map) lista1.get(i);
            if(m.get("id").toString().equals(id)) {
                lista1.remove(i);
                logs.add("Removeu livro ID: " + id);
                System.out.println("Livro removido!");
                return;
            }
        }
        System.out.println("ID nao encontrado.");
    }
    
    static void menuUsuarios() {
        Scanner sc = new Scanner(System.in);
        int o = 0;
        
        while(o != 5) {
            System.out.println("----- USUARIOS -----");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Bloquear");
            System.out.println("5 - Voltar");
            o = sc.nextInt();
            sc.nextLine();
            
            if(o == 1) cadastrarUsuario();
            if(o == 2) listarUsuarios();
            if(o == 3) buscarUsuario();
            if(o == 4) bloquearUsuario();
        }
    }
    
    static void cadastrarUsuario() {
        Scanner sc = new Scanner(System.in);
        Map m = new HashMap();
        
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Endereco: ");
        String end = sc.nextLine();
        
        m.put("cpf", cpf);
        m.put("nome", nome);
        m.put("email", email);
        m.put("telefone", telefone);
        m.put("endereco", end);
        m.put("bloqueado", "N");
        m.put("data_cad", new Date().toString());
        
        lista2.add(m);
        System.out.println("Usuario cadastrado! CPF: " + cpf);
    }
    
    static void listarUsuarios() {
        if(lista2.isEmpty()) {
            System.out.println("Nenhum usuario.");
            return;
        }
        
        for(int i=0; i<lista2.size(); i++) {
            Map m = (Map) lista2.get(i);
            System.out.println(m.get("cpf") + " - " + m.get("nome") + " - " + m.get("email"));
            if(m.get("bloqueado").equals("S")) System.out.print(" [BLOQUEADO]");
            System.out.println();
        }
    }
    
    static void buscarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("CPF ou nome: ");
        String b = sc.nextLine();
        
        for(int i=0; i<lista2.size(); i++) {
            Map m = (Map) lista2.get(i);
            if(m.get("cpf").toString().equals(b) || 
               m.get("nome").toString().toLowerCase().contains(b.toLowerCase())) {
                System.out.println("--- DADOS ---");
                System.out.println("Nome: " + m.get("nome"));
                System.out.println("Email: " + m.get("email"));
                System.out.println("Telefone: " + m.get("telefone"));
                System.out.println("Endereco: " + m.get("endereco"));
                return;
            }
        }
        System.out.println("Nao encontrado.");
    }
    
    static void bloquearUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("CPF do usuario: ");
        String cpf = sc.nextLine();
        
        for(int i=0; i<lista2.size(); i++) {
            Map m = (Map) lista2.get(i);
            if(m.get("cpf").toString().equals(cpf)) {
                m.put("bloqueado", "S");
                logs.add("Bloqueou usuario: " + cpf);
                System.out.println("Usuario bloqueado!");
                return;
            }
        }
        System.out.println("CPF nao encontrado.");
    }
    
    static void menuPedidos() {
        Scanner sc = new Scanner(System.in);
        int o = 0;
        
        while(o != 5) {
            System.out.println("----- PEDIDOS -----");
            System.out.println("1 - Novo Pedido");
            System.out.println("2 - Listar Pedidos");
            System.out.println("3 - Cancelar Pedido");
            System.out.println("4 - Status Pedido");
            System.out.println("5 - Voltar");
            o = sc.nextInt();
            sc.nextLine();
            
            if(o == 1) novoPedido();
            if(o == 2) listarPedidos();
            if(o == 3) cancelarPedido();
            if(o == 4) statusPedido();
        }
    }
    
    static void novoPedido() {
        Scanner sc = new Scanner(System.in);
        Map pedido = new HashMap();
        List itens = new ArrayList();
        
        System.out.print("CPF do cliente: ");
        String cpf = sc.nextLine();
        
        boolean clienteExiste = false;
        for(int i=0; i<lista2.size(); i++) {
            Map u = (Map) lista2.get(i);
            if(u.get("cpf").toString().equals(cpf) && !u.get("bloqueado").equals("S")) {
                clienteExiste = true;
                break;
            }
        }
        
        if(!clienteExiste) {
            System.out.println("Cliente nao existe ou esta bloqueado.");
            return;
        }
        
        double totalPedido = 0;
        while(true) {
            System.out.print("ID do produto (0 para finalizar): ");
            String idProduto = sc.nextLine();
            if(idProduto.equals("0")) break;
            
            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();
            sc.nextLine();
            
            for(int i=0; i<lista1.size(); i++) {
                Map p = (Map) lista1.get(i);
                if(p.get("id").toString().equals(idProduto)) {
                    int est = Integer.parseInt(p.get("estoque").toString());
                    if(est >= qtd) {
                        double preco = Double.parseDouble(p.get("preco").toString());
                        double subtotal = preco * qtd;
                        totalPedido += subtotal;
                        
                        Map item = new HashMap();
                        item.put("produto_id", idProduto);
                        item.put("produto_nome", p.get("titulo"));
                        item.put("quantidade", qtd);
                        item.put("preco_unitario", preco);
                        item.put("subtotal", subtotal);
                        itens.add(item);
                        
                        p.put("estoque", (est - qtd));
                        
                        System.out.println("Item adicionado! Subtotal: R$ " + subtotal);
                    } else {
                        System.out.println("Estoque insuficiente! Disponível: " + est);
                    }
                }
            }
        }
        
        double frete = calcularFrete();
        double desconto = calcularDesconto(totalPedido);
        double totalFinal = totalPedido + frete - desconto;
        
        pedido.put("numero", gerarNumeroPedido());
        pedido.put("cpf_cliente", cpf);
        pedido.put("data", new Date().toString());
        pedido.put("itens", itens);
        pedido.put("subtotal", totalPedido);
        pedido.put("frete", frete);
        pedido.put("desconto", desconto);
        pedido.put("total", totalFinal);
        pedido.put("status", "PENDENTE");
        
        lista3.add(pedido);
        logs.add("Pedido criado: " + pedido.get("numero"));
        
        System.out.println("========== RESUMO DO PEDIDO ==========");
        System.out.println("Numero: " + pedido.get("numero"));
        System.out.println("Subtotal: R$ " + totalPedido);
        System.out.println("Frete: R$ " + frete);
        System.out.println("Desconto: R$ " + desconto);
        System.out.println("Total: R$ " + totalFinal);
        System.out.println("Status: " + pedido.get("status"));
    }
    
    static double calcularFrete() {
        // frete aleatorio entre 10 e 50
        return 10 + (Math.random() * 40);
    }
    
    static double calcularDesconto(double total) {
        if(total > 500) return total * 0.1;
        if(total > 200) return total * 0.05;
        return 0;
    }
    
    static String gerarNumeroPedido() {
        return "PED-" + System.currentTimeMillis();
    }
    
    static void listarPedidos() {
        if(lista3.isEmpty()) {
            System.out.println("Nenhum pedido.");
            return;
        }
        
        for(int i=0; i<lista3.size(); i++) {
            Map p = (Map) lista3.get(i);
            System.out.println(p.get("numero") + " - " + p.get("data") + " - R$ " + p.get("total") + " - " + p.get("status"));
        }
    }
    
    static void cancelarPedido() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Numero do pedido: ");
        String num = sc.nextLine();
        
        for(int i=0; i<lista3.size(); i++) {
            Map p = (Map) lista3.get(i);
            if(p.get("numero").toString().equals(num)) {
                p.put("status", "CANCELADO");
                logs.add("Pedido cancelado: " + num);
                System.out.println("Pedido cancelado!");
                
                // devolve estoque
                List itens = (List) p.get("itens");
                for(Object obj : itens) {
                    Map item = (Map) obj;
                    String prodId = item.get("produto_id").toString();
                    int qtd = Integer.parseInt(item.get("quantidade").toString());
                    
                    for(int j=0; j<lista1.size(); j++) {
                        Map prod = (Map) lista1.get(j);
                        if(prod.get("id").toString().equals(prodId)) {
                            int est = Integer.parseInt(prod.get("estoque").toString());
                            prod.put("estoque", est + qtd);
                        }
                    }
                }
                return;
            }
        }
        System.out.println("Pedido nao encontrado.");
    }
    
    static void statusPedido() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Numero do pedido: ");
        String num = sc.nextLine();
        
        for(int i=0; i<lista3.size(); i++) {
            Map p = (Map) lista3.get(i);
            if(p.get("numero").toString().equals(num)) {
                System.out.println("Status: " + p.get("status"));
                return;
            }
        }
        System.out.println("Pedido nao encontrado.");
    }
    
    static void menuRelatorios() {
        Scanner sc = new Scanner(System.in);
        int o = 0;
        
        while(o != 5) {
            System.out.println("----- RELATORIOS -----");
            System.out.println("1 - Vendas por periodo");
            System.out.println("2 - Produtos mais vendidos");
            System.out.println("3 - Clientes fiéis");
            System.out.println("4 - Logs do sistema");
            System.out.println("5 - Voltar");
            o = sc.nextInt();
            sc.nextLine();
            
            if(o == 1) relVendasPeriodo();
            if(o == 2) relProdutosMaisVendidos();
            if(o == 3) relClientesFieis();
            if(o == 4) relLogs();
        }
    }
    
    static void relVendasPeriodo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Data inicial (dd/MM/yyyy): ");
        String d1 = sc.nextLine();
        System.out.print("Data final (dd/MM/yyyy): ");
        String d2 = sc.nextLine();
        
        double totalVendas = 0;
        int qtdPedidos = 0;
        
        for(int i=0; i<lista3.size(); i++) {
            Map p = (Map) lista3.get(i);
            if(p.get("status").equals("ENTREGUE")) {
                totalVendas += (Double) p.get("total");
                qtdPedidos++;
            }
        }
        
        System.out.println("Periodo: " + d1 + " a " + d2);
        System.out.println("Total de pedidos: " + qtdPedidos);
        System.out.println("Valor total: R$ " + totalVendas);
        System.out.println("Ticket medio: R$ " + (qtdPedidos > 0 ? totalVendas / qtdPedidos : 0));
    }
    
    static void relProdutosMaisVendidos() {
        Map<String, Integer> vendas = new HashMap();
        
        for(int i=0; i<lista3.size(); i++) {
            Map p = (Map) lista3.get(i);
            List itens = (List) p.get("itens");
            for(Object obj : itens) {
                Map item = (Map) obj;
                String nome = item.get("produto_nome").toString();
                int qtd = Integer.parseInt(item.get("quantidade").toString());
                vendas.put(nome, vendas.getOrDefault(nome, 0) + qtd);
            }
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList(vendas.entrySet());
        list.sort((a,b) -> b.getValue().compareTo(a.getValue()));
        
        System.out.println("TOP 5 PRODUTOS MAIS VENDIDOS:");
        for(int i=0; i<Math.min(5, list.size()); i++) {
            System.out.println((i+1) + " - " + list.get(i).getKey() + " (" + list.get(i).getValue() + " unidades)");
        }
    }
    
    static void relClientesFieis() {
        Map<String, Double> gastos = new HashMap();
        
        for(int i=0; i<lista3.size(); i++) {
            Map p = (Map) lista3.get(i);
            String cpf = p.get("cpf_cliente").toString();
            double total = (Double) p.get("total");
            gastos.put(cpf, gastos.getOrDefault(cpf, 0.0) + total);
        }
        
        List<Map.Entry<String, Double>> list = new ArrayList(gastos.entrySet());
        list.sort((a,b) -> b.getValue().compareTo(a.getValue()));
        
        System.out.println("TOP 5 CLIENTES QUE MAIS GASTARAM:");
        for(int i=0; i<Math.min(5, list.size()); i++) {
            String cpf = list.get(i).getKey();
            String nome = "";
            for(int j=0; j<lista2.size(); j++) {
                Map u = (Map) lista2.get(j);
                if(u.get("cpf").toString().equals(cpf)) {
                    nome = u.get("nome").toString();
                    break;
                }
            }
            System.out.println((i+1) + " - " + nome + " (R$ " + list.get(i).getValue() + ")");
        }
    }
    
    static void relLogs() {
        System.out.println("===== LOGS DO SISTEMA =====");
        for(int i=0; i<logs.size(); i++) {
            System.out.println(logs.get(i));
        }
    }
    
    static void menuEstoque() {
        Scanner sc = new Scanner(System.in);
        int o = 0;
        
        while(o != 4) {
            System.out.println("----- ESTOQUE -----");
            System.out.println("1 - Verificar estoque baixo");
            System.out.println("2 - Ajustar estoque");
            System.out.println("3 - Relatorio de estoque");
            System.out.println("4 - Voltar");
            o = sc.nextInt();
            sc.nextLine();
            
            if(o == 1) verificarEstoqueBaixo();
            if(o == 2) ajustarEstoque();
            if(o == 3) relatorioEstoque();
        }
    }
    
    static void verificarEstoqueBaixo() {
        System.out.println("PRODUTOS COM ESTOQUE BAIXO (< 10):");
        for(int i=0; i<lista1.size(); i++) {
            Map p = (Map) lista1.get(i);
            int est = Integer.parseInt(p.get("estoque").toString());
            if(est < 10) {
                System.out.println(p.get("titulo") + " - Estoque: " + est);
            }
        }
    }
    
    static void ajustarEstoque() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID do produto: ");
        String id = sc.nextLine();
        
        for(int i=0; i<lista1.size(); i++) {
            Map p = (Map) lista1.get(i);
            if(p.get("id").toString().equals(id)) {
                System.out.print("Novo estoque: ");
                int novo = sc.nextInt();
                p.put("estoque", novo);
                System.out.println("Estoque atualizado!");
                return;
            }
        }
        System.out.println("Produto nao encontrado.");
    }
    
    static void relatorioEstoque() {
        System.out.println("===== RELATORIO DE ESTOQUE =====");
        double valorTotal = 0;
        for(int i=0; i<lista1.size(); i++) {
            Map p = (Map) lista1.get(i);
            int est = Integer.parseInt(p.get("estoque").toString());
            double preco = Double.parseDouble(p.get("preco").toString());
            valorTotal += est * preco;
            System.out.println(p.get("titulo") + " - " + est + " unidades");
        }
        System.out.println("Valor total em estoque: R$ " + valorTotal);
    }
    
    static void menuVendas() {
        Scanner sc = new Scanner(System.in);
        int o = 0;
        
        while(o != 4) {
            System.out.println("----- VENDAS -----");
            System.out.println("1 - Registrar venda");
            System.out.println("2 - Relatorio diario");
            System.out.println("3 - Comissoes");
            System.out.println("4 - Voltar");
            o = sc.nextInt();
            sc.nextLine();
            
            if(o == 1) registrarVenda();
            if(o == 2) relatorioDiario();
            if(o == 3) calcularComissoes();
        }
    }
    
    static void registrarVenda() {
        System.out.println("Funcao em desenvolvimento...");
    }
    
    static void relatorioDiario() {
        System.out.println("Funcao em desenvolvimento...");
    }
    
    static void calcularComissoes() {
        System.out.println("Funcao em desenvolvimento...");
    }
    
    static void backup() {
        System.out.println("===== BACKUP =====");
        System.out.println("Backup iniciado...");
        
        try {
            FileWriter fw = new FileWriter("backup_livros.txt");
            for(int i=0; i<lista1.size(); i++) {
                fw.write(lista1.get(i).toString() + "\n");
            }
            fw.close();
            
            fw = new FileWriter("backup_usuarios.txt");
            for(int i=0; i<lista2.size(); i++) {
                fw.write(lista2.get(i).toString() + "\n");
            }
            fw.close();
            
            fw = new FileWriter("backup_pedidos.txt");
            for(int i=0; i<lista3.size(); i++) {
                fw.write(lista3.get(i).toString() + "\n");
            }
            fw.close();
            
            System.out.println("Backup concluido!");
            logs.add("Backup realizado em " + new Date());
        } catch(Exception e) {
            System.out.println("Erro no backup: " + e.getMessage());
        }
    }
    
    static void config() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== CONFIGURACAO =====");
        System.out.println("1 - Limpar logs");
        System.out.println("2 - Limpar todos os dados");
        System.out.println("3 - Informacoes do sistema");
        System.out.print("Opcao: ");
        int o = sc.nextInt();
        
        if(o == 1) {
            logs.clear();
            System.out.println("Logs limpos!");
        }
        if(o == 2) {
            lista1.clear();
            lista2.clear();
            lista3.clear();
            logs.clear();
            System.out.println("Todos os dados foram removidos!");
        }
        if(o == 3) {
            System.out.println("Livros: " + lista1.size());
            System.out.println("Usuarios: " + lista2.size());
            System.out.println("Pedidos: " + lista3.size());
            System.out.println("Logs: " + logs.size());
        }
    }
}
