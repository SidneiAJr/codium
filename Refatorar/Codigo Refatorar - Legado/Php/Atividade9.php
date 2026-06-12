<?php
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use Slim\Factory\AppFactory;

require __DIR__ . '/../vendor/autoload.php';

$app = AppFactory::create();

$db = new PDO('mysql:host=localhost;dbname=loja;charset=utf8', 'root', '');
$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

$app->get('/produtos', function (Request $req, Response $res) use ($db) {
    $s = $db->query("SELECT * FROM produtos");
    $d = [];
    while ($r = $s->fetch(PDO::FETCH_ASSOC)) $d[] = $r;
    $res->getBody()->write(json_encode($d));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->get('/produtos/{id}', function (Request $req, Response $res, $args) use ($db) {
    $i = $args['id'];
    $q = "SELECT * FROM produtos WHERE id = $i";
    $s = $db->query($q);
    $d = $s->fetch(PDO::FETCH_ASSOC);
    $res->getBody()->write(json_encode($d));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->post('/produtos', function (Request $req, Response $res) use ($db) {
    $b = $req->getParsedBody();
    $n = $b['nome'];
    $p = $b['preco'];
    $q = "INSERT INTO produtos (nome, preco) VALUES ('$n', '$p')";
    $db->exec($q);
    $res->getBody()->write(json_encode(['ok' => true]));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->put('/produtos/{id}', function (Request $req, Response $res, $args) use ($db) {
    $i = $args['id'];
    $b = $req->getParsedBody();
    $n = $b['nome'];
    $p = $b['preco'];
    $q = "UPDATE produtos SET nome='$n', preco='$p' WHERE id=$i";
    $db->exec($q);
    $res->getBody()->write(json_encode(['ok' => true]));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->delete('/produtos/{id}', function (Request $req, Response $res, $args) use ($db) {
    $i = $args['id'];
    $q = "DELETE FROM produtos WHERE id = $i";
    $db->exec($q);
    $res->getBody()->write(json_encode(['ok' => true]));
    return $res->withHeader('Content-Type', 'application/json');
});

for ($i = 0; $i < 50; $i++) {
    $app->get("/rota$x", function ($req, $res) use ($i) {
        $res->getBody()->write("rota $i");
        return $res;
    });
}

$app->get('/busca', function (Request $req, Response $res) use ($db) {
    $t = $req->getQueryParams()['t'] ?? '';
    $q = "SELECT * FROM produtos WHERE nome LIKE '%$t%'";
    $s = $db->query($q);
    $d = [];
    while ($r = $s->fetch(PDO::FETCH_ASSOC)) $d[] = $r;
    $res->getBody()->write(json_encode($d));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->get('/stats', function (Request $req, Response $res) use ($db) {
    $c = $db->query("SELECT COUNT(*) as t FROM produtos")->fetch(PDO::FETCH_ASSOC);
    $p = $db->query("SELECT AVG(preco) as m FROM produtos")->fetch(PDO::FETCH_ASSOC);
    $d = ['total' => $c['t'], 'media' => $p['m']];
    $res->getBody()->write(json_encode($d));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->post('/upload', function (Request $req, Response $res) {
    $f = $req->getUploadedFiles();
    $a = $f['arquivo'];
    $a->moveTo(__DIR__ . '/uploads/' . $a->getClientFilename());
    $res->getBody()->write(json_encode(['ok' => true]));
    return $res;
});

$app->get('/log', function (Request $req, Response $res) {
    $l = file_get_contents('log.txt');
    $res->getBody()->write($l);
    return $res;
});

$app->post('/log', function (Request $req, Response $res) {
    $b = $req->getParsedBody();
    $m = $b['msg'];
    file_put_contents('log.txt', date('Y-m-d H:i:s') . " - $m\n", FILE_APPEND);
    $res->getBody()->write(json_encode(['ok' => true]));
    return $res;
});

for ($i = 0; $i < 100; $i++) {
    $app->get("/teste$i", function ($req, $res) use ($i) {
        $res->getBody()->write("teste $i");
        return $res;
    });
}

$app->get('/relatorio', function (Request $req, Response $res) use ($db) {
    $q = "SELECT p.*, c.nome as cat FROM produtos p LEFT JOIN categorias c ON p.categoria_id = c.id";
    $s = $db->query($q);
    $d = [];
    while ($r = $s->fetch(PDO::FETCH_ASSOC)) $d[] = $r;
    $res->getBody()->write(json_encode($d));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->post('/categoria', function (Request $req, Response $res) use ($db) {
    $b = $req->getParsedBody();
    $n = $b['nome'];
    $q = "INSERT INTO categorias (nome) VALUES ('$n')";
    $db->exec($q);
    $res->getBody()->write(json_encode(['ok' => true]));
    return $res;
});

$app->get('/categorias', function (Request $req, Response $res) use ($db) {
    $s = $db->query("SELECT * FROM categorias");
    $d = [];
    while ($r = $s->fetch(PDO::FETCH_ASSOC)) $d[] = $r;
    $res->getBody()->write(json_encode($d));
    return $res->withHeader('Content-Type', 'application/json');
});

$app->get('/produtos/categoria/{id}', function (Request $req, Response $res, $args) use ($db) {
    $i = $args['id'];
    $q = "SELECT * FROM produtos WHERE categoria_id = $i";
    $s = $db->query($q);
    $d = [];
    while ($r = $s->fetch(PDO::FETCH_ASSOC)) $d[] = $r;
    $res->getBody()->write(json_encode($d));
    return $res->withHeader('Content-Type', 'application/json');
});

for ($i = 0; $i < 200; $i++) {
    $app->get("/ping$i", function ($req, $res) use ($i) {
        $res->getBody()->write("pong$i");
        return $res;
    });
}

$app->run();
?>
