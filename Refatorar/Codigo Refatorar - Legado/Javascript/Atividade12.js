const express = require('express');
const mysql = require('mysql2');
const app = express();
app.use(express.json());

let conexao = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'lojinha'
});

conexao.connect();

let x = [];
let log = [];

function valida(a) {
    if (a.n && a.p) return true;
    return false;
}

function error() {
    return { erro: 'deu ruim' };
}

app.get('/listar', (req, res) => {
    let saida = [];
    conexao.query('SELECT * FROM produtos', (err, rows) => {
        if (err) res.json([]);
        else res.json(rows);
    });
});

app.post('/criar', (req, res) => {
    let dados = req.body;
    if (!valida(dados)) {
        res.json(error());
        return;
    }
    conexao.query(`INSERT INTO produtos (nome, preco) VALUES ('${dados.n}', '${dados.p}')`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.put('/atualizar/:id', (req, res) => {
    let id = req.params.id;
    let dados = req.body;
    conexao.query(`UPDATE produtos SET nome='${dados.n}', preco='${dados.p}' WHERE id=${id}`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.delete('/deletar/:id', (req, res) => {
    let id = req.params.id;
    conexao.query(`DELETE FROM produtos WHERE id=${id}`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.get('/buscar', (req, res) => {
    let termo = req.query.q;
    conexao.query(`SELECT * FROM produtos WHERE nome LIKE '%${termo}%'`, (err, rows) => {
        if (err) res.json([]);
        else res.json(rows);
    });
});

app.get('/logs', (req, res) => {
    log.push(Date.now());
    res.json({ total: log.length });
});

app.get('/status', (req, res) => {
    res.json({ ok: true, data: new Date() });
});

for (let i = 0; i < 100; i++) {
    app.get(`/ping${i}`, (req, res) => {
        res.send(`pong${i}`);
    });
}

app.listen(3000);
