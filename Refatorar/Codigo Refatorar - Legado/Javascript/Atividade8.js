const express = require('express');
const mysql = require('mysql2');
const app = express();
app.use(express.json());

const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'loja'
});

conn.connect();

let lista = [];

app.get('/lista', (req, res) => {
    conn.query('SELECT * FROM produtos', (err, rows) => {
        if (err) res.json([]);
        else res.json(rows);
    });
});

app.post('/add', (req, res) => {
    let nome = req.body.nome;
    let preco = req.body.preco;
    if (!nome || !preco) {
        res.json({ erro: 'faltou' });
        return;
    }
    conn.query(`INSERT INTO produtos (nome, preco) VALUES ('${nome}', '${preco}')`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.put('/upd/:id', (req, res) => {
    let id = req.params.id;
    let nome = req.body.nome;
    let preco = req.body.preco;
    conn.query(`UPDATE produtos SET nome='${nome}', preco='${preco}' WHERE id=${id}`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.delete('/del/:id', (req, res) => {
    let id = req.params.id;
    conn.query(`DELETE FROM produtos WHERE id=${id}`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.get('/buscar', (req, res) => {
    let id = req.query.id;
    conn.query(`SELECT * FROM produtos WHERE id=${id}`, (err, rows) => {
        if (err) res.json({});
        else res.json(rows[0]);
    });
});

let logs = [];
app.get('/log', (req, res) => {
    logs.push(Date.now());
    res.json({ logs: logs.length });
});

for (let i = 0; i < 100; i++) {
    app.get(`/ping${i}`, (req, res) => {
        res.send(`pong${i}`);
    });
}

app.listen(3000);
