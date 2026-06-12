       IDENTIFICATION DIVISION.
       PROGRAM-ID. SISTEMA-BANCARIO.
       AUTHOR. SIDNEI JR.
       
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
           DECIMAL-POINT IS COMMA.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       
       01 WS-CONTAS.
           05 WS-CONTA OCCURS 500 TIMES.
              10 WS-CONTA-ID PIC 9(6).
              10 WS-CONTA-TITULAR PIC X(60).
              10 WS-CONTA-TIPO PIC X(1).
              10 WS-CONTA-SALDO PIC 9(11)V99.
              10 WS-CONTA-LIMITE PIC 9(11)V99.
              10 WS-CONTA-BLOQ PIC X(1).
              10 WS-CONTA-DATA-ABERT PIC X(10).
              10 WS-CONTA-ULT-MOV PIC X(10).
              10 WS-CONTA-TENTATIVAS PIC 9(2).
       
       01 WS-MOVIMENTOS.
           05 WS-MOV OCCURS 5000 TIMES.
              10 WS-MOV-ID PIC 9(6).
              10 WS-MOV-CONTA PIC 9(6).
              10 WS-MOV-TIPO PIC X(1).
              10 WS-MOV-VALOR PIC 9(11)V99.
              10 WS-MOV-DATA PIC X(10).
              10 WS-MOV-DESC PIC X(50).
       
       01 WS-AGENCIAS.
           05 WS-AGENCIA OCCURS 50 TIMES.
              10 WS-AGENCIA-ID PIC 9(4).
              10 WS-AGENCIA-NOME PIC X(50).
              10 WS-AGENCIA-CIDADE PIC X(30).
              10 WS-AGENCIA-SALDO-TOTAL PIC 9(15)V99.
       
       01 WS-USUARIO.
           05 WS-USUARIO-LOGIN PIC X(20).
           05 WS-USUARIO-SENHA PIC X(20).
           05 WS-USUARIO-TENTATIVAS PIC 9(2).
           05 WS-USUARIO-BLOQ PIC X(1).
       
       01 WS-VARIAVEIS.
           05 WS-OPCAO PIC 9(2).
           05 WS-OPCAO2 PIC 9(2).
           05 WS-ID PIC 9(6).
           05 WS-ID2 PIC 9(6).
           05 WS-VALOR PIC 9(11)V99.
           05 WS-VALOR2 PIC 9(11)V99.
           05 WS-I PIC 9(5).
           05 WS-J PIC 9(5).
           05 WS-K PIC 9(5).
           05 WS-TOTAL-CONTAS PIC 9(4).
           05 WS-TOTAL-MOV PIC 9(6).
           05 WS-TOTAL-AGENCIAS PIC 9(3).
           05 WS-DATA-ATUAL PIC X(10).
           05 WS-TAXA PIC 9(3)V99.
           05 WS-JUROS PIC 9(11)V99.
           05 WS-BONUS PIC 9(11)V99.
           05 WS-SALDO-TOTAL PIC 9(15)V99.
           05 WS-MEDIA-SALDO PIC 9(15)V99.
           05 WS-CONTADOR PIC 9(5).
       
       PROCEDURE DIVISION.
       INICIO.
           DISPLAY "====================================="
           DISPLAY "   SISTEMA BANCARIO ENTERPRISE"
           DISPLAY "   COBOL EDITION"
           DISPLAY "====================================="
           
           PERFORM INICIALIZAR-DADOS
           
           PERFORM LOGIN
           
           PERFORM UNTIL WS-OPCAO = 99
               PERFORM MENU-PRINCIPAL
               PERFORM PROCESSAR-OPCAO
           END-PERFORM.
       
       INICIALIZAR-DADOS.
           MOVE 0 TO WS-TOTAL-CONTAS
           MOVE 0 TO WS-TOTAL-MOV
           MOVE 3 TO WS-TOTAL-AGENCIAS
           MOVE 0 TO WS-USUARIO-TENTATIVAS
           MOVE "N" TO WS-USUARIO-BLOQ
           
           PERFORM CARREGAR-AGENCIAS.
       
       CARREGAR-AGENCIAS.
           MOVE 1 TO WS-AGENCIA-ID(1)
           MOVE "AGENCIA CENTRO" TO WS-AGENCIA-NOME(1)
           MOVE "SAO PAULO" TO WS-AGENCIA-CIDADE(1)
           MOVE 0 TO WS-AGENCIA-SALDO-TOTAL(1)
           
           MOVE 2 TO WS-AGENCIA-ID(2)
           MOVE "AGENCIA NORTE" TO WS-AGENCIA-NOME(2)
           MOVE "CAMPINAS" TO WS-AGENCIA-CIDADE(2)
           MOVE 0 TO WS-AGENCIA-SALDO-TOTAL(2)
           
           MOVE 3 TO WS-AGENCIA-ID(3)
           MOVE "AGENCIA SUL" TO WS-AGENCIA-NOME(3)
           MOVE "SANTOS" TO WS-AGENCIA-CIDADE(3)
           MOVE 0 TO WS-AGENCIA-SALDO-TOTAL(3).
       
       LOGIN.
           DISPLAY "====================================="
           DISPLAY "              LOGIN"
           DISPLAY "====================================="
           
           IF WS-USUARIO-BLOQ = "S" THEN
               DISPLAY "USUARIO BLOQUEADO!"
               DISPLAY "CONTATE O ADMINISTRADOR"
               STOP RUN
           END-IF
           
           DISPLAY "USUARIO: "
           ACCEPT WS-USUARIO-LOGIN
           DISPLAY "SENHA: "
           ACCEPT WS-USUARIO-SENHA
           
           IF WS-USUARIO-LOGIN = "ADMIN" AND 
              WS-USUARIO-SENHA = "123456" THEN
               DISPLAY "LOGIN EFETUADO COM SUCESSO"
           ELSE
               ADD 1 TO WS-USUARIO-TENTATIVAS
               IF WS-USUARIO-TENTATIVAS >= 3 THEN
                   MOVE "S" TO WS-USUARIO-BLOQ
                   DISPLAY "USUARIO BLOQUEADO!"
                   STOP RUN
               ELSE
                   DISPLAY "LOGIN INVALIDO! TENTATIVA "
                       WS-USUARIO-TENTATIVAS " DE 3"
                   PERFORM LOGIN
               END-IF
           END-IF.
       
       MENU-PRINCIPAL.
           DISPLAY "====================================="
           DISPLAY "            MENU PRINCIPAL"
           DISPLAY "====================================="
           DISPLAY "1 - GERENCIAR CONTAS"
           DISPLAY "2 - MOVIMENTACOES"
           DISPLAY "3 - TRANSFERENCIAS"
           DISPLAY "4 - RELATORIOS"
           DISPLAY "5 - ADMINISTRACAO"
           DISPLAY "6 - SIMULADOR DE INVESTIMENTOS"
           DISPLAY "99 - SAIR"
           DISPLAY "====================================="
           ACCEPT WS-OPCAO.
       
       PROCESSAR-OPCAO.
           EVALUATE WS-OPCAO
               WHEN 1 PERFORM GERENCIAR-CONTAS
               WHEN 2 PERFORM MOVIMENTACOES
               WHEN 3 PERFORM TRANSFERENCIAS
               WHEN 4 PERFORM RELATORIOS
               WHEN 5 PERFORM ADMINISTRACAO
               WHEN 6 PERFORM SIMULAR-INVESTIMENTO
               WHEN 99 DISPLAY "SAINDO..."
               WHEN OTHER DISPLAY "OPCAO INVALIDA"
           END-EVALUATE.
       
       GERENCIAR-CONTAS.
           DISPLAY "====================================="
           DISPLAY "        GERENCIAR CONTAS"
           DISPLAY "====================================="
           DISPLAY "1 - ABRIR CONTA"
           DISPLAY "2 - CONSULTAR CONTA"
           DISPLAY "3 - ALTERAR CONTA"
           DISPLAY "4 - BLOQUEAR CONTA"
           DISPLAY "5 - LISTAR CONTAS"
           DISPLAY "0 - VOLTAR"
           ACCEPT WS-OPCAO2
           
           EVALUATE WS-OPCAO2
               WHEN 1 PERFORM ABRIR-CONTA
               WHEN 2 PERFORM CONSULTAR-CONTA
               WHEN 3 PERFORM ALTERAR-CONTA
               WHEN 4 PERFORM BLOQUEAR-CONTA
               WHEN 5 PERFORM LISTAR-CONTAS
               WHEN OTHER CONTINUE
           END-EVALUATE.
       
       ABRIR-CONTA.
           ADD 1 TO WS-TOTAL-CONTAS
           MOVE WS-TOTAL-CONTAS TO WS-CONTA-ID(WS-TOTAL-CONTAS)
           
           DISPLAY "NOME DO TITULAR: "
           ACCEPT WS-CONTA-TITULAR(WS-TOTAL-CONTAS)
           
           DISPLAY "TIPO DE CONTA (C=CORRENTE / P=POUPANCA): "
           ACCEPT WS-CONTA-TIPO(WS-TOTAL-CONTAS)
           
           MOVE 0 TO WS-CONTA-SALDO(WS-TOTAL-CONTAS)
           MOVE "N" TO WS-CONTA-BLOQ(WS-TOTAL-CONTAS)
           
           IF WS-CONTA-TIPO(WS-TOTAL-CONTAS) = "C" THEN
               MOVE 1000 TO WS-CONTA-LIMITE(WS-TOTAL-CONTAS)
           ELSE
               MOVE 0 TO WS-CONTA-LIMITE(WS-TOTAL-CONTAS)
           END-IF
           
           MOVE 0 TO WS-CONTA-TENTATIVAS(WS-TOTAL-CONTAS)
           
           DISPLAY "CONTA ABERTA COM SUCESSO!"
           DISPLAY "NUMERO DA CONTA: " WS-CONTA-ID(WS-TOTAL-CONTAS).
       
       CONSULTAR-CONTA.
           DISPLAY "NUMERO DA CONTA: "
           ACCEPT WS-ID
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               IF WS-CONTA-ID(WS-I) = WS-ID THEN
                   DISPLAY "====================================="
                   DISPLAY "DADOS DA CONTA"
                   DISPLAY "====================================="
                   DISPLAY "NUMERO: " WS-CONTA-ID(WS-I)
                   DISPLAY "TITULAR: " WS-CONTA-TITULAR(WS-I)
                   DISPLAY "TIPO: " WS-CONTA-TIPO(WS-I)
                   DISPLAY "SALDO: R$ " WS-CONTA-SALDO(WS-I)
                   DISPLAY "LIMITE: R$ " WS-CONTA-LIMITE(WS-I)
                   IF WS-CONTA-BLOQ(WS-I) = "S" THEN
                       DISPLAY "STATUS: BLOQUEADA"
                   ELSE
                       DISPLAY "STATUS: ATIVA"
                   END-IF
                   DISPLAY "ULTIMO MOVIMENTO: " 
                       WS-CONTA-ULT-MOV(WS-I)
               END-IF
           END-PERFORM.
       
       ALTERAR-CONTA.
           DISPLAY "NUMERO DA CONTA: "
           ACCEPT WS-ID
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               IF WS-CONTA-ID(WS-I) = WS-ID THEN
                   DISPLAY "NOVO NOME DO TITULAR: "
                   ACCEPT WS-CONTA-TITULAR(WS-I)
                   DISPLAY "CONTA ALTERADA COM SUCESSO!"
               END-IF
           END-PERFORM.
       
       BLOQUEAR-CONTA.
           DISPLAY "NUMERO DA CONTA: "
           ACCEPT WS-ID
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               IF WS-CONTA-ID(WS-I) = WS-ID THEN
                   MOVE "S" TO WS-CONTA-BLOQ(WS-I)
                   DISPLAY "CONTA BLOQUEADA COM SUCESSO!"
               END-IF
           END-PERFORM.
       
       LISTAR-CONTAS.
           DISPLAY "====================================="
           DISPLAY "        LISTA DE CONTAS"
           DISPLAY "====================================="
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               DISPLAY WS-CONTA-ID(WS-I) " - " 
                       WS-CONTA-TITULAR(WS-I) " - R$ "
                       WS-CONTA-SALDO(WS-I)
           END-PERFORM.
       
       MOVIMENTACOES.
           DISPLAY "====================================="
           DISPLAY "        MOVIMENTACOES"
           DISPLAY "====================================="
           DISPLAY "1 - DEPOSITO"
           DISPLAY "2 - SAQUE"
           DISPLAY "3 - EXTRATO"
           DISPLAY "0 - VOLTAR"
           ACCEPT WS-OPCAO2
           
           EVALUATE WS-OPCAO2
               WHEN 1 PERFORM DEPOSITO
               WHEN 2 PERFORM SAQUE
               WHEN 3 PERFORM EXTRATO
               WHEN OTHER CONTINUE
           END-EVALUATE.
       
       DEPOSITO.
           DISPLAY "NUMERO DA CONTA: "
           ACCEPT WS-ID
           DISPLAY "VALOR DO DEPOSITO: "
           ACCEPT WS-VALOR
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               IF WS-CONTA-ID(WS-I) = WS-ID THEN
                   ADD WS-VALOR TO WS-CONTA-SALDO(WS-I)
                   ADD 1 TO WS-TOTAL-MOV
                   MOVE WS-TOTAL-MOV TO WS-MOV-ID(WS-TOTAL-MOV)
                   MOVE WS-ID TO WS-MOV-CONTA(WS-TOTAL-MOV)
                   MOVE "D" TO WS-MOV-TIPO(WS-TOTAL-MOV)
                   MOVE WS-VALOR TO WS-MOV-VALOR(WS-TOTAL-MOV)
                   MOVE "DEPOSITO" TO WS-MOV-DESC(WS-TOTAL-MOV)
                   DISPLAY "DEPOSITO REALIZADO COM SUCESSO!"
               END-IF
           END-PERFORM.
       
       SAQUE.
           DISPLAY "NUMERO DA CONTA: "
           ACCEPT WS-ID
           DISPLAY "VALOR DO SAQUE: "
           ACCEPT WS-VALOR
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               IF WS-CONTA-ID(WS-I) = WS-ID THEN
                   IF WS-CONTA-BLOQ(WS-I) = "S" THEN
                       DISPLAY "CONTA BLOQUEADA!"
                       EXIT PERFORM
                   END-IF
                   
                   COMPUTE WS-VALOR2 = WS-CONTA-SALDO(WS-I) 
                       + WS-CONTA-LIMITE(WS-I)
                   
                   IF WS-VALOR <= WS-VALOR2 THEN
                       SUBTRACT WS-VALOR FROM WS-CONTA-SALDO(WS-I)
                       ADD 1 TO WS-TOTAL-MOV
                       MOVE WS-TOTAL-MOV TO WS-MOV-ID(WS-TOTAL-MOV)
                       MOVE WS-ID TO WS-MOV-CONTA(WS-TOTAL-MOV)
                       MOVE "S" TO WS-MOV-TIPO(WS-TOTAL-MOV)
                       MOVE WS-VALOR TO WS-MOV-VALOR(WS-TOTAL-MOV)
                       MOVE "SAQUE" TO WS-MOV-DESC(WS-TOTAL-MOV)
                       DISPLAY "SAQUE REALIZADO COM SUCESSO!"
                   ELSE
                       DISPLAY "SALDO INSUFICIENTE!"
                   END-IF
               END-IF
           END-PERFORM.
       
       EXTRATO.
           DISPLAY "NUMERO DA CONTA: "
           ACCEPT WS-ID
           
           DISPLAY "====================================="
           DISPLAY "            EXTRATO"
           DISPLAY "====================================="
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-MOV
               IF WS-MOV-CONTA(WS-I) = WS-ID THEN
                   DISPLAY WS-MOV-DATA(WS-I) " - "
                           WS-MOV-DESC(WS-I) " - R$ "
                           WS-MOV-VALOR(WS-I)
               END-IF
           END-PERFORM.
       
       TRANSFERENCIAS.
           DISPLAY "====================================="
           DISPLAY "        TRANSFERENCIAS"
           DISPLAY "====================================="
           DISPLAY "CONTA ORIGEM: "
           ACCEPT WS-ID
           DISPLAY "CONTA DESTINO: "
           ACCEPT WS-ID2
           DISPLAY "VALOR: "
           ACCEPT WS-VALOR
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               IF WS-CONTA-ID(WS-I) = WS-ID THEN
                   COMPUTE WS-VALOR2 = WS-CONTA-SALDO(WS-I) 
                       + WS-CONTA-LIMITE(WS-I)
                   
                   IF WS-VALOR <= WS-VALOR2 THEN
                       SUBTRACT WS-VALOR FROM WS-CONTA-SALDO(WS-I)
                       
                       PERFORM VARYING WS-J FROM 1 BY 1 
                               UNTIL WS-J > WS-TOTAL-CONTAS
                           IF WS-CONTA-ID(WS-J) = WS-ID2 THEN
                               ADD WS-VALOR TO WS-CONTA-SALDO(WS-J)
                           END-IF
                       END-PERFORM
                       
                       ADD 1 TO WS-TOTAL-MOV
                       MOVE WS-TOTAL-MOV TO WS-MOV-ID(WS-TOTAL-MOV)
                       MOVE WS-ID TO WS-MOV-CONTA(WS-TOTAL-MOV)
                       MOVE "T" TO WS-MOV-TIPO(WS-TOTAL-MOV)
                       MOVE WS-VALOR TO WS-MOV-VALOR(WS-TOTAL-MOV)
                       MOVE "TRANSFERENCIA PARA " 
                           TO WS-MOV-DESC(WS-TOTAL-MOV)
                       MOVE WS-ID2 TO WS-MOV-DESC(WS-TOTAL-MOV)
                       
                       DISPLAY "TRANSFERENCIA REALIZADA!"
                   ELSE
                       DISPLAY "SALDO INSUFICIENTE!"
                   END-IF
               END-IF
           END-PERFORM.
       
       RELATORIOS.
           DISPLAY "====================================="
           DISPLAY "           RELATORIOS"
           DISPLAY "====================================="
           DISPLAY "1 - SALDO TOTAL DO BANCO"
           DISPLAY "2 - MEDIA DE SALDO"
           DISPLAY "3 - CONTAS POR TIPO"
           DISPLAY "4 - CONTAS BLOQUEADAS"
           DISPLAY "5 - RELATORIO POR AGENCIA"
           DISPLAY "0 - VOLTAR"
           ACCEPT WS-OPCAO2
           
           EVALUATE WS-OPCAO2
               WHEN 1 PERFORM SALDO-TOTAL
               WHEN 2 PERFORM MEDIA-SALDO
               WHEN 3 PERFORM CONTAS-POR-TIPO
               WHEN 4 PERFORM CONTAS-BLOQUEADAS
               WHEN 5 PERFORM RELATORIO-AGENCIA
               WHEN OTHER CONTINUE
           END-EVALUATE.
       
       SALDO-TOTAL.
           MOVE 0 TO WS-SALDO-TOTAL
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               ADD WS-CONTA-SALDO(WS-I) TO WS-SALDO-TOTAL
           END-PERFORM
           
           DISPLAY "SALDO TOTAL DO BANCO: R$ " WS-SALDO-TOTAL.
       
       MEDIA-SALDO.
           MOVE 0 TO WS-SALDO-TOTAL
           MOVE 0 TO WS-CONTADOR
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               ADD WS-CONTA-SALDO(WS-I) TO WS-SALDO-TOTAL
               ADD 1 TO WS-CONTADOR
           END-PERFORM
           
           DIVIDE WS-SALDO-TOTAL BY WS-CONTADOR 
               GIVING WS-MEDIA-SALDO
           
           DISPLAY "MEDIA DE SALDO: R$ " WS-MEDIA-SALDO.
       
       CONTAS-POR-TIPO.
           MOVE 0 TO WS-I
           MOVE 0 TO WS-J
           
           PERFORM VARYING WS-K FROM 1 BY 1 
                   UNTIL WS-K > WS-TOTAL-CONTAS
               IF WS-CONTA-TIPO(WS-K) = "C" THEN
                   ADD 1 TO WS-I
               ELSE
                   ADD 1 TO WS-J
               END-IF
           END-PERFORM
           
           DISPLAY "CONTAS CORRENTE: " WS-I
           DISPLAY "CONTAS POUPANCA: " WS-J.
       
       CONTAS-BLOQUEADAS.
           MOVE 0 TO WS-CONTADOR
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               IF WS-CONTA-BLOQ(WS-I) = "S" THEN
                   ADD 1 TO WS-CONTADOR
                   DISPLAY WS-CONTA-ID(WS-I) " - " 
                           WS-CONTA-TITULAR(WS-I)
               END-IF
           END-PERFORM
           
           DISPLAY "TOTAL DE CONTAS BLOQUEADAS: " WS-CONTADOR.
       
       RELATORIO-AGENCIA.
           DISPLAY "1 - AGENCIA CENTRO"
           DISPLAY "2 - AGENCIA NORTE"
           DISPLAY "3 - AGENCIA SUL"
           ACCEPT WS-OPCAO2
           
           DISPLAY "SALDO TOTAL DA AGENCIA: " 
               WS-AGENCIA-SALDO-TOTAL(WS-OPCAO2).
       
       ADMINISTRACAO.
           DISPLAY "====================================="
           DISPLAY "         ADMINISTRACAO"
           DISPLAY "====================================="
           DISPLAY "1 - CALCULAR JUROS"
           DISPLAY "2 - APLICAR TAXA"
           DISPLAY "3 - BONUS PARA CONTAS"
           DISPLAY "0 - VOLTAR"
           ACCEPT WS-OPCAO2
           
           EVALUATE WS-OPCAO2
               WHEN 1 PERFORM CALCULAR-JUROS
               WHEN 2 PERFORM APLICAR-TAXA
               WHEN 3 PERFORM BONUS-CONTAS
               WHEN OTHER CONTINUE
           END-EVALUATE.
       
       CALCULAR-JUROS.
           DISPLAY "TAXA DE JUROS (%): "
           ACCEPT WS-TAXA
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               COMPUTE WS-JUROS = WS-CONTA-SALDO(WS-I) 
                   * WS-TAXA / 100
               ADD WS-JUROS TO WS-CONTA-SALDO(WS-I)
           END-PERFORM
           
           DISPLAY "JUROS APLICADOS COM SUCESSO!".
       
       APLICAR-TAXA.
           DISPLAY "VALOR DA TAXA: "
           ACCEPT WS-VALOR
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               SUBTRACT WS-VALOR FROM WS-CONTA-SALDO(WS-I)
           END-PERFORM
           
           DISPLAY "TAXA APLICADA COM SUCESSO!".
       
       BONUS-CONTAS.
           DISPLAY "VALOR DO BONUS: "
           ACCEPT WS-VALOR
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-TOTAL-CONTAS
               ADD WS-VALOR TO WS-CONTA-SALDO(WS-I)
           END-PERFORM
           
           DISPLAY "BONUS APLICADO COM SUCESSO!".
       
       SIMULAR-INVESTIMENTO.
           DISPLAY "====================================="
           DISPLAY "     SIMULADOR DE INVESTIMENTOS"
           DISPLAY "====================================="
           DISPLAY "VALOR INICIAL: "
           ACCEPT WS-VALOR
           DISPLAY "TAXA DE JUROS (%): "
           ACCEPT WS-TAXA
           DISPLAY "MESES: "
           ACCEPT WS-VARIAVEIS
           
           MOVE WS-VALOR TO WS-VALOR2
           
           PERFORM VARYING WS-I FROM 1 BY 1 
                   UNTIL WS-I > WS-VARIAVEIS
               COMPUTE WS-JUROS = WS-VALOR2 * WS-TAXA / 100
               ADD WS-JUROS TO WS-VALOR2
           END-PERFORM
           
           DISPLAY "VALOR FINAL: R$ " WS-VALOR2
           DISPLAY "RENDIMENTO: R$ " 
               (WS-VALOR2 - WS-VALOR).

       END PROGRAM SISTEMA-BANCARIO.
