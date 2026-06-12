const express = require('express');
const mysql = require('mysql2');
const app = express();
app.use(express.json());


// Conexção do Banco
let db = mysql.createConnection({ 
    host: 'localhost', 
    user: '', 
    password: '', 
    database: 'loja' 
});

db.connect();

// Rota de Selecionar todos os produtos
app.get('/Produtos', (req, res) => {
    db.query('SELECT * FROM produtos', (err, rows) => {
        if (err) res.status(500).json({erro:'Erro ao buscar produtos'});
        else res.json(rows);
    });
});

// Rota de Inserir Informação
app.post('/Produtos', (req, res) => {
     let nome = req.body.nome;
     let preco = req.body.preco;
    if (!nome || !preco) { res.json({ erro: 'Nome e Preço são Obrigatorio' });
     return; }
    db.query(`INSERT INTO produtos (nome, preco) VALUES (?,?)`,[nome,preco],(err) => {
        if(err){
            res.status(500).json({erro:'Erro ao inserir produto'});
        }else{
            res.status(201).json({ok:true,mensagem:'Produto Inserir'});
        }
    });
});

// Rota de Atualização por ID
app.put('/Produtos/:id', (req, res) => {
    const id = req.params.id;
    const nome = req.body.nome;
    const preco = req.body.preco;
    if(!nome || !preco){
        res.status(400).json({erro: 'Nome e Preço São Obrigatorios'});
        return;
    }
    db.query('UPDATE produtos set nome=?, preco =? where id =?',[nome,preco,id],(err,result)=>{
        if(err){
            res.status(500).json({erro: "Erro ao Atualizar Produtos"});
        }else if(result.affectedRows === 0){
            res.status(404).json({erro:"Produto não econtrado"});
        }else{
            res.json({ok:true,mensagem:'Produto Atualizado'})
        }
    })
});

app.delete('/produtos/:id',(req,res)=>{
    const id = req.params.id;
    db.query('DELETE from produtos where id =?',[id],(err,result)=>{
        if(err){
            res.status(500).json({erro: "Erro ao deletar Produtos"})
        }else if(result.affectedRows === 0){
            res.status(404).json({erro: "Produto não encontrado"})
        }else{
            res.status(204).send();
        }
    })
})

app.get("/produtos/busca",(req,res)=>{
    const termo = req.query.q;
    if(!termo){
        res.status(400).json({erro:"Termo de Buscar e obrigatorio"})
        return;
    }
    db.query("SELECT * from produtos where nome Like ?",[`${termo}`],(err,rows)=>{
        if(err){
            res.status(500).json({erro: "Erro ao buscar Produtos"});
        }else{
            res.json(rows)
        }
    })
})

let logs = [];

app.get('/log',(req,res)=>{
    logs.push(Date.now());
    res.json({totalLogs: logs.length, ultimolog:logs[logs.length-1]});
})



// Porta do Servidor Rodando
app.listen(3000, () => {
    console.log('Servidor rodando na porta 3000');
});
