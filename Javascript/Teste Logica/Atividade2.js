// Objeto de Pessoas
let pessoa = [
    {nome:"pedro",idade: 11},
    {nome: "Juliana", idade: 20},
    {nome: "Jorge",idade: 15}
];

//Usando Filtrer
let maiores = pessoa.filter(p=>p.idade>=18);
console.log("Maiores de idade:",maiores)

// Usando map
let resultado = pessoa.map(pessoa=>{
    return `${pessoa.nome} é ${pessoa.idade >=18 ? "maior": "menor"} de idade`;
})

console.log(resultado)
