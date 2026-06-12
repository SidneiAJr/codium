const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true })); 

const connection = mysql.createConnection({
    host: 'localhost',
    user:'',
    password: '',
    database: ''
});


app.get('/medicos', (req, res) => {
    const sql = `SELECT * FROM medico`;
    connection.query(sql, (err, results) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        res.json(results);
    });
});

app.post('/medicos', (req, res) => {
    const { nome_medico,crme_medico,idade_medico,cidade_medico,telefone } = req.body;
    const sql = `INSERT INTO medico(nome_medico,crme_medico,idade_medico,cidade_medico,telefone) VALUES (?,?,?,?,?)`;
    const valores = [nome_medico,crme_medico,idade_medico,cidade_medico,telefone]
    connection.query(sql,valores ,(err, result) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        res.status(201).json({id:result.insertId,nome_medico,crme_medico,idade_medico,cidade_medico,telefone});
    });
});

app.put('/medicos/:id', (req, res) => {
    const {id} = req.params;
    const { nome_medico,crme_medico,idade_medico,cidade_medico,telefone } = req.body;
    const sql = `UPDATE medico SET nome_medico=?,crme_medico =?,idade_medico =?,cidade_medico=?,telefone=? WHERE id_medicos=?`;
    const valores = [nome_medico,crme_medico,idade_medico,cidade_medico,telefone,id]
    connection.query(sql,valores, (err, result) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        if (result.affectedRows === 0) {
            return res.status(404).json({ erro: 'Registro não encontrado' });
        }
        res.json({mensagem: 'Atualizado com Sucesso'});
    });
});
app.delete('/medicos/:id', (req, res) => {
    const { id } = req.params;
    const sql = `DELETE FROM medico WHERE id_medicos = ?`;
    connection.query(sql,[id], (err, result) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        if (result.affectedRows === 0) {
            return res.status(404).json({ erro: 'Registro não encontrado' });
        }
        res.json({mensagem: 'Apagado com Sucesso'});
    });
});

const porta = 3000;
app.listen(porta,()=>{
    console.log(`Servidor Rodando na Porta ${porta}`)
})
