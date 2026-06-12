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


function ValidarProduto(){
    if(!produto||typeof produto !== 'object'){
        return {valido: false,mensagem: 'Dados Invalidos'};
    }
    if(!produto.nome || produto.nome.trim()===''){
        return {valido:false , mensagem: 'Nome é Obrigatorio'};
    }
    if(!produto.preco || isNaN(produto.preco)|| produto.preco<=0){
        return {valido: false , mensagem: 'Preço deve ser um Numero maior que zero'}
    }
    return{valido:true}
}


app.get('/produtos', (req, res) => {
    conexao.query('SELECT * FROM produtos', (err, rows) => {
        if (err) {
           console.error(err);
           res.status(500).json({erro:"Erro Ao Buscar Informação"});
        }
        res.json(rows);
    });
});

app.get('/produtos/buscar',(req,res)=>{
    const termo = req.query.q || '';
    conexao.query(
        'select * from produtos where nome like ?',[`${termo}`],
        (err,rows)=>{
            if(err){
                console.erro(err);
                return res.status(500).json({erro: 'erro na busca'});
            }
            res.json(rows)
        }
    );
});

app.get('/produtos',(req,res)=>{
    const validacao = ValidarProduto(req.body);
    if(!validacao.valido){
        return res.status(400).json({erro: validacao.mensagem})
    }
    const {nome,preco}=req.body;
    conexao.query('INSERT INTO produtos (nome,preco) values (?,?)'),[nome,preco],
    (err,result)=>{
        if(err){
            console.erro(err);
            return res.status(500).json({erro: 'Erro ao criar produto'});
        }
        res.status(201).json({
            ok:true,
            id:result.insertid,
            mensagem: 'Produto Criado com sucesso';
        });
    };
});

app.put('/produtos/:id',(req,res)=>{
    const id = parseInt(req.params.id);
    if(isNaN(id)){
        return res.status(400).json({erro: 'ID invalido'})
    }
    const validacao = ValidarProduto(req.body);
    if(!validacao.valido){
        return res.status(400).json({erro: validacao.mensagem});
    }

    const {nome,preco} = req.body;

    conexao.query('UPDATE produtos set nome = ?, preco = ? where id = ?'),[nome,preco,id],(err,result=>{
        if(err){
            console.error(err);
            return res.status(500).json({erro: 'Errro ao Atualizar produtos'})
        }
        if(result.affectedRows===0){
            return res.status(404).json({erro:'Produto não encontrado'})
        }
        res.json({ok:true,mensagem:'Produto atualizado'})
    });
});

// DELETAR produto
app.delete('/produtos/:id', (req, res) => {
    const id = parseInt(req.params.id);
    if (isNaN(id)) {
        return res.status(400).json({ erro: 'ID inválido' });
    }

    connection.query('DELETE FROM produtos WHERE id = ?', [id], (err, result) => {
        if (err) {
            console.error(err);
            return res.status(500).json({ erro: 'Erro ao deletar produto' });
        }
        if (result.affectedRows === 0) {
            return res.status(404).json({ erro: 'Produto não encontrado' });
        }
        res.json({ ok: true, mensagem: 'Produto deletado' });
    });
});

// Status da API
app.get('/status', (req, res) => {
    res.json({
        ok: true,
        data: new Date(),
        versao: '1.0.0'
    });
});

// Rota de ping (uma única rota, não 100)
app.get('/ping/:id', (req, res) => {
    const id = req.params.id;
    res.send(`pong${id}`);
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`🚀 Servidor rodando em http://localhost:${PORT}`);
});
