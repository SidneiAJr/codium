let produtos = [
    {nome: "Mouse 1",preco: 50},
    {nome: "Mouse 2",preco: 500},
    {nome: "Mouse 3",preco: 5000},
    {nome: "Mouse 4",preco: 15},
    {nome: "Mouse 4",preco: 5},
]

produtos.sort((a,b)=>a.nome.localeCompare(b.nome));
let total = produtos.reduce((acumulador,produto)=>acumulador+produto.preco,0);

console.log("Lista Ordenada: ", produtos);
console.log("Soma Total dos Produtos: ", total);
