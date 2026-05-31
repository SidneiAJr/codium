const express = require('express');
const { DataSource } = require('typeorm');
const app = express();

app.use(express.json());

const AppDataSource = new DataSource({
    type: "mysql",
    host: "localhost",
    port: 3306,
    username: "root",
    password: "root",
    database: "java_crud",
    synchronize: true,  // ← igual spring.jpa.hibernate.ddl-auto=update
    logging: true,      // ← mostra SQL no console
    entities: [require('./entities/Usuario')]  // ← onde ficam as entidades
})

AppDataSource.initialize().then(()=>{
    const usuarioRepo = AppDataSource.getRepository('Usuario');
    app.get('/a', async(req, res) => {
    const usuarios = await usuarioRepo.find();
    res.json(usuarios)
});
app.post('/cad', async(req,res)=>{
    const {nome,senha}=req.body;
    const usuario = {nome,senha};
    const gravado = await usuarioRepo.save(usuario);
    res.json({mensagem: "Gravado com Sucesso"})
})
app.listen(3000);
})







