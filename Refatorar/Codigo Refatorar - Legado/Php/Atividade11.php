<?php
// index.php
$conn = mysqli_connect("localhost", "root", "", "tasks");
if(!$conn) die("deu ruim");

$a = $_GET['a'] ?? '';

if($a == 'add') {
    $t = $_POST['task'];
    $sql = "INSERT INTO tarefas (task) VALUES ('$t')";
    mysqli_query($conn, $sql);
    header("Location: index.php");
}

if($a == 'del') {
    $id = $_GET['id'];
    mysqli_query($conn, "DELETE FROM tarefas WHERE id = $id");
    header("Location: index.php");
}

if($a == 'up') {
    $id = $_GET['id'];
    mysqli_query($conn, "UPDATE tarefas SET status = !status WHERE id = $id");
    header("Location: index.php");
}

$rs = mysqli_query($conn, "SELECT * FROM tarefas");
?>

<html>
<body>
<h1>MINHAS TAREFAS</h1>
<form method="POST" action="?a=add">
    <input type="text" name="task">
    <button>+</button>
</form>
<ul>
<? while($row = mysqli_fetch_array($rs)) { ?>
    <li>
        <input type="checkbox" <?= $row['status'] ? 'checked' : '' ?> onclick="location.href='?a=up&id=<?= $row['id'] ?>'">
        <?= $row['task'] ?>
        <a href="?a=del&id=<?= $row['id'] ?>" onclick="return confirm('deletar?')">[x]</a>
    </li>
<? } ?>
</ul>
</body>
</html>
