<?php
$agenda = [
    ['Joao', '1234-5678', 'joao@x.com', 'Rua A, 123'],
    ['Maria', '8765-4321', 'maria@y.com', 'Rua B, 456'],
    ['Jose', '5555-5555', 'jose@z.com', 'Rua C, 789'],
    ['Ana', '9999-9999', 'ana@w.com', 'Rua D, 101'],
    ['Pedro', '1111-1111', 'pedro@v.com', 'Rua E, 202']
];

$acao = $_GET['do'] ?? '';
$idx = $_GET['i'] ?? 0;

if($acao == 'del') {
    unset($agenda[$idx]);
    $agenda = array_values($agenda);
}

if($_POST) {
    $novo = [
        $_POST['nome'],
        $_POST['tel'],
        $_POST['email'],
        $_POST['end']
    ];
    $agenda[] = $novo;
}

function buscar($texto) {
    global $agenda;
    $r = [];
    foreach($agenda as $c) {
        if(strpos($c[0], $texto) !== false || strpos($c[1], $texto) !== false) {
            $r[] = $c;
        }
    }
    return $r;
}

$busca = $_GET['q'] ?? '';
if($busca) {
    $agenda = buscar($busca);
}
?>

<html>
<body>
<h1>AGENDA</h1>
<form method="GET">
    <input name="q" placeholder="buscar..." value="<?= $busca ?>">
    <button>buscar</button>
</form>
<table border="1">
    <thead><th>Nome</th><th>Telefone</th><th>Email</th><th>Endereco</th><th></th></thead>
    <tbody>
    <? foreach($agenda as $i => $c): ?>
    <tr>
        <td><?= $c[0] ?></td>
        <td><?= $c[1] ?></td>
        <td><?= $c[2] ?></td>
        <td><?= $c[3] ?></td>
        <td><a href="?do=del&i=<?= $i ?>">[x]</a></td>
    </tr>
    <? endforeach ?>
    </tbody>
</table>

<h2>ADICIONAR</h2>
<form method="POST">
    <input name="nome" placeholder="nome"><br>
    <input name="tel" placeholder="telefone"><br>
    <input name="email" placeholder="email"><br>
    <input name="end" placeholder="endereco"><br>
    <button>salvar</button>
</form>
</body>
</html>
