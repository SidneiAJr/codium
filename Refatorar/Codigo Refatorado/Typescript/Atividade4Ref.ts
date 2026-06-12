/*
===================================
Refatorando Atividade 5
===================================
*/

import express, { Request, Response } from 'express';
import mysql2 from 'mysql2';

const app = express();
app.use(express.json())


// Simulando Interface
interface Produto{
    id?: number;
    nome:string;
    preco:number;
}

// Banco Simulado Aqui Array.
let produtos: Produto[]=[];

//Rota Raiz
app.get('/', (req:Request, res:Response) => {
   res.json(produtos);
});

//Rota de Produtos - Adiciona Novo Produto
app.post('/produtos',(req:Request,res:Response)=>{
    const novoProduto: Produto = req.body;
    if(!novoProduto.nome || !novoProduto.preco){
        res.status(400).json({erro: 'Nome e Preço são Obrigatorios'});
        return;
    }
    produtos.push(novoProduto);
    res.status(201).json(novoProduto)
});


//Rota de Produtos por ID | Atualizar
app.put('/produtos/:id',(req:Request,res:Response)=>{
    const id = parseInt(req.params.id);
    const dadosAtualizados: Produto = req.body;
    if(id<0|| id>=produtos.length){
        res.status(404).json({erro:'Produto não encontrado'});
        return;
    }
    produtos[id]=dadosAtualizados;
    res.json(produtos[id]);
});

// Rota de Deletar
app.delete('/produtos',(req:Request,res:Response)=>{
    const id = parseInt(req.params.id);
    if(id<0|| id>=produtos.length){
        res.status(404).json({erro:'Produto não econtrado'});
        return;
    }
    produtos.splice(id,1);
    res.status(204).send();
});

// Porta do Servidor Rodando
app.listen(3000, () => {
    console.log('Servidor rodando na porta 3000');
});
