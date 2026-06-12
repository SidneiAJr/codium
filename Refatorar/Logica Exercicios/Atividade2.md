# 🧠 Lógica Pura – Nível 2: Estruturas de Dados (sem código)

**Regra:** Desenhe, liste, compare. Nada de programar.

---

## Exercício 1: A pilha de pratos sujos

Você lava pratos e empilha. O último lavado fica no topo. Na hora de guardar, você pega do topo.

**Situação:**  
Você lava: Prato1, Prato2, Prato3, Prato4  
Depois guarda 2 pratos  
Depois lava mais: Prato5, Prato6  
Depois guarda 3 pratos  

**Perguntas:**

1. Qual a ordem de guardamento (quem sai da pilha primeiro)?
2. Qual prato continua na pilha no final?
3. Se você quisesse guardar sempre o prato mais velho primeiro, qual estrutura usaria?
4. Desenhe a pilha após cada operação.

---

## Exercício 2: A fila do supermercado

Tem 3 caixas. Cada cliente escolhe a **fila menor**.

Chegadas (minutos após abertura, tempo de atendimento em minutos):

| Cliente | Chegada | Atendimento |
|---------|---------|-------------|
| A       | 0       | 5           |
| B       | 2       | 3           |
| C       | 3       | 4           |
| D       | 6       | 2           |
| E       | 7       | 6           |

**Perguntas:**

1. A que horas cada cliente termina de ser atendido?
2. Qual caixa atendeu mais clientes?
3. Qual cliente esperou mais tempo na fila? Quanto?
4. Se houvesse **um único caixa**, qual seria o tempo total de espera?

---

## Exercício 3: O labirinto de decisões

Você está num labirinto com 4 salas. Cada sala tem 2 portas (esquerda ou direita).  
Apenas 1 caminho leva à saída. Regras:

- Sala1: esquerda → Sala2, direita → Sala4
- Sala2: esquerda → Sala3, direita → Sala1
- Sala3: esquerda → SAÍDA, direita → Sala4
- Sala4: esquerda → Sala1, direita → Sala2

**Perguntas:**

1. Começando na Sala1, qual sequência de portas leva à SAÍDA?
2. Existe algum ciclo? Qual?
3. Quantos passos no mínimo pra sair?
4. O que acontece se você alternar sempre esquerda, direita, esquerda, direita...?
