const express = require('express');
const mysql = require('mysql2');
const app = express();
app.use(express.json());

let db = mysql.createConnection({ host: 'localhost', user: 'root', password: '', database: 'loja' });
db.connect();

let lista = [];
let logs = [];

function x(y) { return y * 2; }
function z(w) { return w + 1; }

app.get('/p', (req, res) => {
    db.query('SELECT * FROM produtos', (err, rows) => {
        if (err) res.json([]);
        else res.json(rows);
    });
});

app.post('/p', (req, res) => {
    let a = req.body.a;
    let b = req.body.b;
    if (!a || !b) { res.json({ erro: 'falta' }); return; }
    db.query(`INSERT INTO produtos (nome, preco) VALUES ('${a}', '${b}')`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.put('/p/:id', (req, res) => {
    let i = req.params.id;
    let a = req.body.a;
    let b = req.body.b;
    db.query(`UPDATE produtos SET nome='${a}', preco='${b}' WHERE id=${i}`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.delete('/p/:id', (req, res) => {
    let i = req.params.id;
    db.query(`DELETE FROM produtos WHERE id=${i}`, (err) => {
        if (err) res.json({ erro: 'sql' });
        else res.json({ ok: true });
    });
});

app.get('/p/b', (req, res) => {
    let q = req.query.q;
    db.query(`SELECT * FROM produtos WHERE nome LIKE '%${q}%'`, (err, rows) => {
        if (err) res.json([]);
        else res.json(rows);
    });
});

app.get('/log', (req, res) => {
    logs.push(Date.now());
    res.json({ logs: logs.length });
});

for (let i = 0; i < 100; i++) {
    app.get(`/t${i}`, (req, res) => { res.send(`t${i}`); });
}

app.listen(3000);
