<?php

use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use Slim\Factory\AppFactory;

require __DIR__ . '/../vendor/autoload.php';

$app = AppFactory::create();

// Rota raiz explícita
$app->get('/', function (Request $request, Response $response) {
    $response->getBody()->write('Slim funcionando!');
    return $response;
});

$app->get('/usuarios', function (Request $request, Response $response) {
    $pdo = new PDO('mysql:host=localhost;dbname=teste;charset=utf8', 'root', 'root');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $stmt = $pdo->query("SELECT * FROM t");
    $usuarios = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
    $response->getBody()->write(json_encode($usuarios));
return $response->withHeader('Content-Type', 'application/json');
});

$app->post('/cadastro', function (Request $request, Response $response) {
    $dados = json_decode($request->getBody(), true);
    
    $nome = $dados['nome'] ?? null;
    $idade = $dados['idade'] ?? null;
    
    // 🔥 VALIDAÇÃO (evita erro)
    if (!$nome || !$idade) {
        $response->getBody()->write(json_encode(['erro' => 'Nome e idade obrigatórios']));
        return $response->withHeader('Content-Type', 'application/json')->withStatus(400);
    }
    
    $pdo = new PDO('mysql:host=localhost;dbname=teste;charset=utf8', 'root', 'root');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $stmt = $pdo->prepare("INSERT INTO t (nome, idade) VALUES (?, ?)");
    $stmt->execute([$nome, $idade]);
    
    // 🔥 REMOVA O fetchAll() — INSERT não retorna dados para buscar
    $resultado = [
        'mensagem' => 'Usuário cadastrado com sucesso',
        'id' => $pdo->lastInsertId()
    ];
    
    $response->getBody()->write(json_encode($resultado));
    return $response->withHeader('Content-Type', 'application/json')->withStatus(201);
});


$app->get('/cadastro/{id}', function (Request $request, Response $response, $args) {
    $id = $args['id']; // 🔥 PARÂMETRO DA ROTA
    
    if (!$id) {
        $response->getBody()->write(json_encode(['erro' => 'ID é obrigatório']));
        return $response->withHeader('Content-Type', 'application/json')->withStatus(400);
    }
    
    $pdo = new PDO('mysql:host=localhost;dbname=teste;charset=utf8', 'root', 'root');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $stmt = $pdo->prepare("SELECT * FROM t WHERE id = ?");
    $stmt->execute([$id]);
    $usuario = $stmt->fetch(PDO::FETCH_ASSOC);
    
    if (!$usuario) {
        $response->getBody()->write(json_encode(['erro' => 'Usuário não encontrado']));
        return $response->withHeader('Content-Type', 'application/json')->withStatus(404);
    }
    
    $response->getBody()->write(json_encode($usuario));
    return $response->withHeader('Content-Type', 'application/json');
});





$app->run();
