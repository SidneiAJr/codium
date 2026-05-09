// Versão Old scholl
let array = [1,2,3,4,6,9];
let soma = 0;

for(let i=0; i<array.length; i++){
    if(array[i]%2===0){
        soma = soma+array[i]
    }
}

console.log(soma);

// Versão Mais Nova | filter
let array2 = [2,7,9,10,11,66];
let pares = array2.filter(n=>n%2===0);
console.log(pares);

// Versão com reduce | reduce
let array3 = [11,16,18,19];
let res = array3.reduce((acc,n)=>acc+n,0);
console.log(res)
