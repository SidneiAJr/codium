<?php

use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use Slim\Factory\AppFactory;

require __DIR__ . '/../vendor/autoload.php';

$app = AppFactory::create();

// 🔥 ROTA OBRIGATÓRIA - SEM ISSO DÁ 404 🔥
$app->get('/', function (Request $request, Response $response) {
    $response->getBody()->write('Slim rodando!');
    return $response;
});

// 🔥 ROTA /usuarios
$app->get('/usuarios', function (Request $request, Response $response) {
    $pdo = new PDO('mysql:host=localhost;dbname=teste;charset=utf8', 'root', '');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $stmt = $pdo->query("SELECT * FROM t");
    $usuarios = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
    return $response->withJson($usuarios);
});

$app->run();
