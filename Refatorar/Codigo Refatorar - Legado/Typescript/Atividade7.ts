import express from 'express';
const app = express();
app.use(express.json());

let db: any[] = [];
let logs: any[] = [];

function valida(x: any): boolean {
    if (x.nome && x.preco) return true;
    return false;
}

function erro(r: any) {
    return { erro: "deu ruim" };
}

app.get('/listar', (req, res) => {
    let out = [];
    for (let i = 0; i < db.length; i++) {
        out.push({ id: i, nome: db[i].nome, preco: db[i].preco });
    }
    res.json(out);
});

app.post('/criar', (req, res) => {
    let d = req.body;
    if (!valida(d)) {
        res.json(erro(d));
        return;
    }
    db.push(d);
    logs.push({ acao: 'create', data: new Date() });
    res.json({ ok: true });
});

app.put('/atualizar/:id', (req, res) => {
    let id = Number(req.params.id);
    let d = req.body;
    if (id >= db.length || id < 0) {
        res.json(erro(null));
        return;
    }
    db[id] = d;
    logs.push({ acao: 'update', data: new Date() });
    res.json({ ok: true });
});

app.delete('/remover/:id', (req, res) => {
    let id = Number(req.params.id);
    if (id >= db.length || id < 0) {
        res.json(erro(null));
        return;
    }
    db.splice(id, 1);
    logs.push({ acao: 'delete', data: new Date() });
    res.json({ ok: true });
});

app.get('/log', (req, res) => {
    res.json(logs);
});

for (let i = 0; i < 50; i++) {
    app.get(`/rota${i}`, (req, res) => {
        res.send(`rota ${i}`);
    });
}

app.get('/busca', (req, res) => {
    let q = req.query.q;
    let out = [];
    for (let i = 0; i < db.length; i++) {
        if (db[i].nome.includes(q)) out.push(db[i]);
    }
    res.json(out);
});

app.get('/detalhe/:id', (req, res) => {
    let id = Number(req.params.id);
    if (id >= db.length || id < 0) {
        res.json(erro(null));
        return;
    }
    res.json(db[id]);
});

app.post('/multi', (req, res) => {
    let lista = req.body;
    for (let i = 0; i < lista.length; i++) {
        if (valida(lista[i])) {
            db.push(lista[i]);
        }
    }
    res.json({ ok: true, count: lista.length });
});

app.get('/contar', (req, res) => {
    res.json({ total: db.length });
});

app.delete('/limpar', (req, res) => {
    db = [];
    logs = [];
    res.json({ ok: true });
});

function backup() {
    return db;
}

app.get('/backup', (req, res) => {
    res.json(backup());
});

for (let i = 0; i < 100; i++) {
    app.get(`/ping${i}`, (req, res) => {
        res.send(`pong${i}`);
    });
}

app.listen(3000, () => {
    console.log('rodando');
});
