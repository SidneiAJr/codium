<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

// 🔥 USA O CONEX.PHP QUE JÁ EXISTE 🔥
require_once 'conex.php';

$method = $_SERVER['REQUEST_METHOD'];

switch ($method) {
    case 'GET':
        $stmt = $pdo->query("SELECT * FROM t");
        echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
        break;

    case 'POST':
        $dados = json_decode(file_get_contents('php://input'), true);
        if (empty($dados['nome']) || empty($dados['idade'])) {
            http_response_code(400);
            echo json_encode(['error' => 'Nome e idade obrigatórios']);
            break;
        }
        $stmt = $pdo->prepare("INSERT INTO t (nome, idade) VALUES (?, ?)");
        $stmt->execute([$dados['nome'], $dados['idade']]);
        echo json_encode(['message' => 'Usuário criado']);
        break;
    

    case 'DELETE':
        if (!isset($_GET['id'])) {
            http_response_code(400);
            echo json_encode(['error' => 'ID não informado']);
            break;
        }
        $stmt = $pdo->prepare("DELETE FROM t WHERE id = ?");
        $stmt->execute([$_GET['id']]);
        echo json_encode(['message' => 'Usuário deletado']);
        break;

    default:
        http_response_code(405);
        echo json_encode(['error' => 'Método não permitido']);
}
?>
