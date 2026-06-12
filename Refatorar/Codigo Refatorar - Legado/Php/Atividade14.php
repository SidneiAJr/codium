<?php
function j($d) {
    $t = 0;
    for($i=0;$i<$d;$i++) {
        $t += rand(1,6);
    }
    return $t;
}

function p($j1, $j2) {
    if($j1 > $j2) return 1;
    if($j2 > $j1) return 2;
    return 0;
}

function x($a, $b) {
    $r = [];
    for($i=$a; $i<=$b; $i++) {
        $r[] = $i;
    }
    return $r;
}

$dados = $_GET['d'] ?? 1;
$j1 = j($dados);
$j2 = j($dados);
$v = p($j1, $j2);

$historico = $_COOKIE['hist'] ?? '';
$novo = "$j1 x $j2 = $v";
$historico = $novo . "<br>" . $historico;
setcookie('hist', $historico, time()+86400);
?>

<html>
<body>
<h1>JOGO DE DADOS</h1>
<p>Jogador 1: <?= $j1 ?></p>
<p>Jogador 2: <?= $j2 ?></p>
<p>Resultado: 
<? if($v == 1) echo "Jogador 1 venceu!"; ?>
<? if($v == 2) echo "Jogador 2 venceu!"; ?>
<? if($v == 0) echo "Empate!"; ?>
</p>
<a href="?d=<?= $dados ?>">Jogar novamente</a>
<a href="?d=2">2 dados</a>
<a href="?d=3">3 dados</a>
<a href="?d=4">4 dados</a>

<h3>Histórico</h3>
<?= $historico ?>
</body>
</html>
