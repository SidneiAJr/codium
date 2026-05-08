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
    $pdo = new PDO('mysql:host=localhost;dbname=teste;charset=utf8', 'root', 'root');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $stmt = $pdo->prepare("INSERT INTO t (nome, idade) VALUES (?, ?)");
    $stmt->execute([$nome, $idade]);
    
    $usuarios = $stmt->fetchAll(PDO::FETCH_ASSOC);
            // 3️⃣ Retorna uma mensagem de sucesso
    $resultado = [
            'mensagem' => 'Usuário cadastrado com sucesso',
            'id' => $pdo->lastInsertId()
    ];
    
    $response->getBody()->write(json_encode($usuarios));
return $response->withHeader('Content-Type', 'application/json');
});

$app->run();
