import express from 'express';
const app = express();
app.use(express.json());

let db: any[] = [];

app.get('/coisa', (req, res) => {
    res.send(db);
});

app.post('/coisa', (req, res) => {
    let z = req.body;
    db.push(z);
    console.log('add');
    res.send('ok');
});

app.put('/coisa/:id', (req, res) => {
    let i = Number(req.params.id);
    let n = req.body.nome;
    db[i].nome = n;
    res.send('atualizado');
});

app.delete('/coisa/:id', (req, res) => {
    let i = Number(req.params.id);
    db.splice(i, 1);
    res.send('deletado');
});

app.listen(3000);
