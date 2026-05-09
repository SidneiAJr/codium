import express,{Request,Response} from "express";
import mysql2 from "mysql2";
import bcrypt from "bcrypt";
import cors from "cors";
import { body } from "express-validator";

const app = express();
app.use(express.json());
app.use(cors());

const connection = mysql2.createConnection({
    host:'localhost',
    port: Number(3306) ,
    user: 'root',
    password: 'root',
    database: 'teste'
});

connection.connect();

// Lista todos os Usuarios
app.get('/Listar',(req:Request,res:Response)=>{
  const listagem = `SELECT * FROM t`
  connection.query(listagem,(erro,resultado)=>{
    if(erro){
        return res.status(500).send("Erro | Não Possivel Listar");
    }
    return res.json(resultado);
  })
})

const port = 3000;
app.listen(port, () => {
    console.log(`Servidor Rodando em http://localhost:${port}`);
});
