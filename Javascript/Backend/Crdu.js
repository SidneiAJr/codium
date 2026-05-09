const express = require('express');
const mysql2 = require('mysql2');
const cors = require('cors');
const bcrypt = require('bcrypt')
const { body, validationResult } = require('express-validator');

const app = express();
app.use(express.json());
app.use(cors());

const connection = mysql2.createConnection({
    host:'localhost',
    port: 3306,
    user: 'root',
    password: 'root',
    database: 'teste'
});

connection.connect();

// Lista todos os Usuarios
app.get('/Listar',(req,res)=>{
  const listagem = `SELECT * FROM t`
  connection.query(listagem,(erro,resultado)=>{
    if(erro){
        return res.status(500).send("Erro | Não Possivel Listar");
    }
    return res.json(resultado);
  })
})

// Cadastra o Usuario com nome e idade | Banco Simples
app.post('/cadastro',[
  body('nome').notEmpty().withMessage("Nome é Obrigatorio").isLength({min:3}).withMessage("Nome deve ter no minimo 3 Caracteres"),
      body('idade').notEmpty().withMessage('Idade é obrigatória').isInt({ min: 0, max: 120 }).withMessage('Idade deve ser um número entre 0 e 120'),
    body('senha').notEmpty().withMessage('Senha é obrigatória').isLength({ min: 6 }).withMessage('Senha deve ter no mínimo 6 caracteres')
],async(req,res)=>{
  const errors = validationResult(req);
  if(!errors.isEmpty()){
    return res.status(400).json({erro:errors.array()[0].msg});
  }
  const {nome,idade,senha}= req.body
  try{
    const senhaHash = await bcrypt.hash(senha,12);
    const inserrir = `INSERT INTO t (nome,idade,senha) values (?,?,?)`
  connection.query(inserrir,[nome,idade,senhaHash],(erro,resultado)=>{
    if(erro){
      console.error(erro)
      return res.status(500).send("Erro | Não foi possivel Cadastrar");
    }
    return res.status(201).json({
      mensagem: "Usuario cadastrado com sucesso!",
      id:resultado.insertid
    })
  })
  }catch(erro){
    console.error(erro);
    return res.status(500).json({erro:"Erro ao gerar hash da senha"})
  }
  
})

app.delete('/delete/:id',(req,res)=>{
    const {id} = req.params;
    if(!id){
      return res.status(400).json({erro:"ID e Obrigatorio"});
    }
    const deletar = `delete from t where id =?`
     connection.query(deletar,[id],(erro,resultado)=>{
    if(erro){
      console.error(erro);
      return res.status(500).json("Erro | Não foi possivel Deletar");
    }
    if(resultado.affectedRows===0){
      return res.status(404).json({erro: "Usuario Não Encontrado"});
    }
    return res.status(200).json({
      mensagem: "Usuario Deletado com Sucesso",
      id:id
    })
  })
})

app.put('/update/:id',(req,res)=>{
    const {id} = req.params;
    const {nome,idade,senha}=req.body;
    if(!id){
      return res.status(400).json({erro:"ID e Obrigatorio"});
    }
    if(!nome&&!idade){
      return res.status(400).json({erro:"Nome ou idade são Obrigatorios"})
    }
    const atualizar = `update t set nome =?,idade=?, senha=? where id=?`
     connection.query(atualizar,[nome,idade,senha,id],(erro,resultado)=>{
    if(erro){
      console.error(erro);
      return res.status(500).json("Erro | Ao Atualizar o usuario");
    }
    if(resultado.affectedRows===0){
      return res.status(404).json({erro: "Usuario Não Encontrado"});
    }
    return res.status(200).json({
      mensagem: "Usuario Atualizado com sucesso com Sucesso",
      id:id
    })
  })
})






const port = 3000;
app.listen(port, () => {
    console.log(`Servidor Rodando em http://localhost:${port}`);
});
