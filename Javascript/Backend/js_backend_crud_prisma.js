const express = require('express');
const {PrismaClient} = require('@prisma/client')
const prisma = new PrismaClient();
const app = express();

app.use(express.json());

app.get('/a', async(req, res) => {
    const usuarios = await prisma.usuario.findMany();
    res.json(usuarios)
});

app.post('/cad', async(req,res)=>{
    const {nome,senha} = req.body
    const gravar = await prisma.usuario.create({
        data:{nome,senha}
    })
    res.json(gravar)
})

const porta = 3000;
app.listen(porta, () => {
    console.log(`Servidor rodando na porta ${porta}`);
});


