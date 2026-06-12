import express from 'express';
const app = express();
app.use(express.json());

let produtos: any[] = [];
let cont = 0;

function val(p: any): boolean {
    if (p.n && p.v) return true;
    return false;
}

function err() {
    return { e: 'inválido' };
}

app.get('/list', (req, res) => {
    let out = [];
    for (let i = 0; i < produtos.length; i++) {
        out.push({ id: i, nome: produtos[i].n, valor: produtos[i].v });
    }
    res.json(out);
});

app.post('/add', (req, res) => {
    let d = req.body;
    if (!val(d)) { res.json(err()); return; }
    produtos.push(d);
    res.json({ ok: true });
});

app.put('/up/:id', (req, res) => {
    let id = Number(req.params.id);
    let d = req.body;
    if (id >= produtos.length || id < 0) { res.json(err()); return; }
    produtos[id] = d;
    res.json({ ok: true });
});

app.delete('/rm/:id', (req, res) => {
    let id = Number(req.params.id);
    if (id >= produtos.length || id < 0) { res.json(err()); return; }
    produtos.splice(id, 1);
    res.json({ ok: true });
});

app.get('/ct', (req, res) => {
    res.json({ t: produtos.length });
});

for (let i = 0; i < 50; i++) {
    app.get(`/r${i}`, (req, res) => { res.send(`r${i}`); });
}

app.listen(3000);
