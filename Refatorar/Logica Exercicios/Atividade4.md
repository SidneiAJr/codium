# 🧠 Lógica Pura – Nível 4: Complexidade e otimização

**Regra:** Comparar estratégias, não implementar.

---

## Exercício 1: O alfaiate desorganizado

Você tem 5 camisas e 5 calças misturadas num baú.  
Quer encontrar **um par específico** (camisa vermelha + calça azul).

**Estratégia A:** Pegar uma camisa, testar com todas calças até achar. Repetir para cada camisa.  
**Estratégia B:** Separar primeiro todas camisas e todas calças, depois procurar o par.

**Perguntas:**

1. No pior caso, quantas tentativas a Estratégia A faz?
2. No pior caso, quantas tentativas a Estratégia B faz?
3. Se houvesse 1000 camisas e 1000 calças, qual estratégia é melhor? Por quê?
4. Dê um exemplo onde a Estratégia A seria mais rápida que a B.

---

## Exercício 2: O carteiro preguiçoso

Um carteiro deve entregar cartas em 5 casas em linha reta. Posições: 1, 4, 6, 9, 12 (km do ponto inicial).  
Pode começar de qualquer casa e terminar em qualquer casa.

**Perguntas:**

1. Qual a rota mais curta que visita todas as casas exatamente uma vez?
2. Qual a distância percorrida?
3. Se ele pudesse **repetir casas**, a rota melhoraria? Em quanto?
4. Se fossem 20 casas aleatórias, você ainda tentaria resolver na mão? Por quê?

---

## Exercício 3: A busca binária na vida real

Você tem um dicionário com 1.000 páginas. Procura a palavra "Zebra".

**Perguntas:**

1. Se abrir no meio, depois na metade do lado certo, etc., quantas aberturas no máximo?
2. E se fossem 1.000.000 de páginas?
3. Por que esse método é melhor que abrir página por página?
4. Em que situação **não** dá pra usar busca binária? (pista: os dados precisam estar...)
