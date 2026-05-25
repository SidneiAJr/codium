const express = require('express');
const mysql = require('mysql2');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const rateLimit = require('express-rate-limit');
const cors = require('cors');
const dotenv = require('dotenv');

dotenv.config();
const app = express();

const connection = mysql.createConnection({
    host: process.env.DB_HOST || '',
    user: process.env.DB_USER || '',
    password: process.env.DB_PASSWORD || '',
    database: process.env.DB_NAME || ''
});



app.get('/produtoss', (req, res) => {
    const sql = `SELECT * FROM [sua tabela aqui]`;
    connection.query(sql, (err, results) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        res.json(results);
    });
});
app.post('/produtoss', (req, res) => {
    const { nome, email } = req.body;
    const sql = `INSERT INTO [sua tabela aqui] (nome, email) VALUES ('${nome}', '${email}')`;
    connection.query(sql, (err, result) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        res.status(201).json({ id: result.insertId, nome, email });
    });
});
app.put('/produtoss/:id', (req, res) => {
    const { id } = req.params;
    const { nome, email } = req.body;
    const sql = `UPDATE [sua tabela aqui] SET nome = '${nome}', email = '${email}' WHERE id = ${id}`;
    connection.query(sql, (err, result) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        if (result.affectedRows === 0) {
            return res.status(404).json({ erro: 'Registro não encontrado' });
        }
        res.json({ id: Number(id), nome, email });
    });
});
app.delete('/produtoss/:id', (req, res) => {
    const { id } = req.params;
    const sql = `DELETE FROM [sua tabela aqui] WHERE id = ${id}`;
    connection.query(sql, (err, result) => {
        if (err) {
            return res.status(500).json({ erro: err.message });
        }
        if (result.affectedRows === 0) {
            return res.status(404).json({ erro: 'Registro não encontrado' });
        }
        res.status(204).send();
    });
});
