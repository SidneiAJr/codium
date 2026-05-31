// Importa o Express - framework web para criar rotas e servidor HTTP
const express = require('express');

// Importa o mysql2 com suporte a Promise (async/await)
// O '/promise' permite usar 'await' ao invés de callbacks
const mysql2 = require('mysql2/promise');

// Importa o CORS - permite que seu frontend (React, HTML, etc) acesse essa API
// Sem CORS, o navegador bloqueia requisições de origens diferentes
const cors = require('cors');

// Cria a aplicação Express
const app = express();

// Habilita o parse de JSON no corpo da requisição (req.body)
// Sem isso, req.body seria undefined
app.use(express.json());

// Habilita CORS para todas as rotas
// Permite que qualquer domínio acesse sua API (útil para desenvolvimento)
app.use(cors());

// Cria um POOL de conexões com o banco de dados
// Pool mantém várias conexões abertas e reutiliza, ao invés de abrir/fechar a cada requisição
const pool = mysql2.createPool({
    host: 'localhost',        // Onde o MySQL está rodando
    port: 3306,               // Porta padrão do MySQL
    user: 'root',             // Seu usuário do MySQL
    password: '',         // Sua senha do MySQL
    database: '',        // Nome do banco de dados que você quer usar
    waitForConnections: true, // Se todas conexões estiverem ocupadas, aguarda uma liberar
    connectionLimit: 10,      // Número máximo de conexões simultâneas
    queueLimit: 0             // 0 = fila infinita de espera (não rejeita requisição)
});

/**
 * Rota GET /Listar
 * Retorna uma lista paginada de usuários da tabela 't'
 * 
 * Parâmetros opcionais via query string:
 * - page: número da página (padrão = 1)
 * - limit: itens por página (padrão = 50)
 * 
 * Exemplos de uso:
 * - /Listar               → página 1, 50 itens
 * - /Listar?page=2        → página 2, 50 itens
 * - /Listar?page=1&limit=10 → página 1, 10 itens
 */
app.get('/Listar', async (req, res) => {
    // req.query = parâmetros da URL após o '?'
    // Ex: /Listar?page=2&limit=10 → req.query = { page: '2', limit: '10' }
    
    // parseInt converte string para número
    // || 1 significa: se não veio ou deu NaN, usa 1 como padrão
    const page = parseInt(req.query.page) || 1;
    const limit = parseInt(req.query.limit) || 50;
    
    // Cálculo do OFFSET (quantos registros pular)
    // Página 1: offset = (1-1)*50 = 0   → começa do primeiro
    // Página 2: offset = (2-1)*50 = 50  → começa do 51º
    // Página 3: offset = (3-1)*50 = 100 → começa do 101º
    const offset = (page - 1) * limit;
    
    try {
        // --- PRIMEIRA QUERY: busca os dados paginados ---
        // LIMIT ? = quantos registros pegar (ex: 50)
        // OFFSET ? = quantos registros pular (ex: 0, 50, 100...)
        // As interrogações (?) são placeholders para evitar SQL injection
        // O segundo argumento [limit, offset] substitui as ? na ordem
        const [rows] = await pool.query(
            'SELECT * FROM t LIMIT ? OFFSET ?',
            [limit, offset]
        );
        
        // --- SEGUNDA QUERY: conta o total de registros na tabela ---
        // Necessário para calcular quantas páginas existem
        // Ex: se total = 150 e limit = 50 → totalPaginas = 3
        const [totalRows] = await pool.query('SELECT COUNT(*) as total FROM t');
        const total = totalRows[0].total;  // Pega o valor do contador
        
        // --- RESPOSTA DA API ---
        // Retorna um JSON com:
        // - dados: array dos registros da página atual
        // - pagina: número da página atual
        // - limite: itens por página
        // - total: total de registros no banco
        // - totalPaginas: quantas páginas existem (Math.ceil arredonda pra cima)
        res.json({
            dados: rows,                      // Os registros em si
            pagina: page,                     // Página atual
            limite: limit,                    // Itens por página
            total: total,                     // Total de registros
            totalPaginas: Math.ceil(total / limit)  // Ex: 150/50 = 3 páginas
        });
        
    } catch (error) {
        // Se qualquer erro acontecer nas queries acima, cai aqui
        
        // Loga o erro no console do servidor (para você debuggar)
        console.error('Erro na query:', error);
        
        // Retorna erro 500 (Internal Server Error) para o cliente
        // O cliente sabe que algo deu errado, mas não vê os detalhes internos
        res.status(500).json({
            erro: 'Erro ao listar usuários',
            detalhe: error.message  // Mensagem amigável do erro
        });
    }
});

// Inicia o servidor na porta 3000
// O servidor fica ouvindo requisições HTTP nessa porta
app.listen(3000, () => {
    // Esse callback roda quando o servidor inicia com sucesso
    console.log('Servidor rodando na porta 3000');
});
