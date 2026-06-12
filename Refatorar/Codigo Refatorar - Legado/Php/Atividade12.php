<?php
function somar($a, $b) {
    $r = $a + $b;
    return $r;
}

function subtrair($a, $b) {
    $r = $a - $b;
    return $r;
}

function multiplicar($a, $b) {
    $r = $a * $b;
    return $r;
}

function dividir($a, $b) {
    $r = $a / $b;
    return $r;
}

function potencia($a, $b) {
    $r = 1;
    for($i=0; $i<$b; $i++) {
        $r = $r * $a;
    }
    return $r;
}

function fatorial($n) {
    $r = 1;
    for($i=1; $i<=$n; $i++) {
        $r = $r * $i;
    }
    return $r;
}

function par_impar($n) {
    if($n % 2 == 0) {
        return "par";
    } else {
        return "impar";
    }
}

function primo($n) {
    $c = 0;
    for($i=1; $i<=$n; $i++) {
        if($n % $i == 0) {
            $c++;
        }
    }
    if($c == 2) {
        return true;
    } else {
        return false;
    }
}

$num1 = $_GET['n1'] ?? 0;
$num2 = $_GET['n2'] ?? 0;
$op = $_GET['op'] ?? '';

if($op == 'somar') $resultado = somar($num1, $num2);
if($op == 'subtrair') $resultado = subtrair($num1, $num2);
if($op == 'multiplicar') $resultado = multiplicar($num1, $num2);
if($op == 'dividir') $resultado = dividir($num1, $num2);
if($op == 'potencia') $resultado = potencia($num1, $num2);
if($op == 'fatorial') $resultado = fatorial($num1);
if($op == 'par_impar') $resultado = par_impar($num1);
if($op == 'primo') $resultado = primo($num1) ? 'sim' : 'nao';
?>

<form>
    <input type="number" name="n1" value="<?= $num1 ?>">
    <input type="number" name="n2" value="<?= $num2 ?>">
    <select name="op">
        <option value="somar">+</option>
        <option value="subtrair">-</option>
        <option value="multiplicar">*</option>
        <option value="dividir">/</option>
        <option value="potencia">^</option>
        <option value="fatorial">!</option>
        <option value="par_impar">par/impar</option>
        <option value="primo">primo</option>
    </select>
    <button>=</button>
    <h2>Resultado: <?= $resultado ?? '' ?></h2>
</form>
