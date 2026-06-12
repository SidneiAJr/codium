import express from 'express';
const app = express();
app.use(express.json());

type Qualquer = any;

let db: Qualquer[] = [];
let contador = 0;

function validar(produto: Qualquer): boolean {
    if (produto.nome && produto.preco) return true;
    return false;
}

function erroGenerico() {
    return { mensagem: 'inválido' };
}

app.get('/produtos', (req, res) => {
    let lista = [];
    for (let i = 0; i < db.length; i++) {
        lista.push({ id: i, nome: db[i].nome, valor: db[i].preco });
    }
    res.json(lista);
});

app.post('/produtos', (req, res) => {
    let data = req.body;
    if (!validar(data)) {
        res.json(erroGenerico());
        return;
    }
    db.push(data);
    res.json({ ok: true, id: db.length - 1 });
});

app.put('/produtos/:id', (req, res) => {
    let idx = Number(req.params.id);
    let data = req.body;
    if (idx < 0 || idx >= db.length) {
        res.json(erroGenerico());
        return;
    }
    db[idx] = data;
    res.json({ ok: true });
});

app.delete('/produtos/:id', (req, res) => {
    let idx = Number(req.params.id);
    if (idx < 0 || idx >= db.length) {
        res.json(erroGenerico());
        return;
    }
    db.splice(idx, 1);
    res.json({ ok: true });
});

app.get('/buscar', (req, res) => {
    let termo = req.query.q;
    let resultados = [];
    for (let i = 0; i < db.length; i++) {
        if (db[i].nome.includes(termo)) {
            resultados.push(db[i]);
        }
    }
    res.json(resultados);
});

app.get('/contar', (req, res) => {
    res.json({ total: db.length });
});

app.get('/log', (req, res) => {
    contador++;
    res.json({ acessos: contador });
});

app.get('/ping', (req, res) => {
    res.send('pong');
});

for (let i = 0; i < 50; i++) {
    app.get(`/rota${i}`, (req, res) => {
        res.send(`rota ${i}`);
    });
}

app.listen(3000);
