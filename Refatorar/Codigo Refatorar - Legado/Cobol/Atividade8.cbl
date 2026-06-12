       IDENTIFICATION DIVISION.
       PROGRAM-ID. FOLHA-PGTO.
       AUTHOR. SIDNEI JR.
       
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
           DECIMAL-POINT IS COMMA.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 WS-FUNCIONARIOS.
           05 WS-FUNC OCCURS 100 TIMES.
              10 WS-ID PIC 9(5).
              10 WS-NOME PIC X(50).
              10 WS-SAL-BRUTO PIC 9(7)V99.
              10 WS-DEP PIC 9(2).
              10 WS-INSS PIC 9(7)V99.
              10 WS-IRRF PIC 9(7)V99.
              10 WS-SAL-LIQ PIC 9(7)V99.
       
       01 WS-I PIC 9(3).
       01 WS-J PIC 9(3).
       01 WS-TOTAL-FOLHA PIC 9(9)V99.
       01 WS-MEDIA-SAL PIC 9(9)V99.
       01 WS-MAIOR-SAL PIC 9(7)V99.
       01 WS-MENOR-SAL PIC 9(7)V99.
       01 WS-CONT PIC 9(3).
       
       PROCEDURE DIVISION.
       INICIO.
           DISPLAY "===== SISTEMA DE FOLHA ====="
           DISPLAY "1 - CALCULAR FOLHA"
           DISPLAY "2 - RELATORIO"
           DISPLAY "3 - SAIR"
           ACCEPT WS-I
           
           IF WS-I = 1 THEN
               PERFORM CALCULA-FOLHA
           END-IF
           
           IF WS-I = 2 THEN
               PERFORM RELATORIO
           END-IF
           
           IF WS-I = 3 THEN
               STOP RUN
           END-IF
           
           GO TO INICIO.
       
       CALCULA-FOLHA.
           MOVE 1 TO WS-CONT
           PERFORM UNTIL WS-CONT > 100
               DISPLAY "ID: " WS-CONT
               DISPLAY "NOME: " 
               ACCEPT WS-NOME(WS-CONT)
               DISPLAY "SALARIO BRUTO: "
               ACCEPT WS-SAL-BRUTO(WS-CONT)
               DISPLAY "DEPENDENTES: "
               ACCEPT WS-DEP(WS-CONT)
               
               COMPUTE WS-INSS(WS-CONT) = 
                   WS-SAL-BRUTO(WS-CONT) * 0.11
               
               IF WS-DEP(WS-CONT) > 0 THEN
                   COMPUTE WS-IRRF(WS-CONT) = 
                       WS-SAL-BRUTO(WS-CONT) * 0.075
               ELSE
                   COMPUTE WS-IRRF(WS-CONT) = 
                       WS-SAL-BRUTO(WS-CONT) * 0.15
               END-IF
               
               COMPUTE WS-SAL-LIQ(WS-CONT) = 
                   WS-SAL-BRUTO(WS-CONT) - 
                   WS-INSS(WS-CONT) - 
                   WS-IRRF(WS-CONT)
               
               ADD 1 TO WS-CONT
           END-PERFORM.
       
       RELATORIO.
           MOVE 1 TO WS-I
           MOVE 0 TO WS-TOTAL-FOLHA
           MOVE WS-SAL-LIQ(1) TO WS-MAIOR-SAL
           MOVE WS-SAL-LIQ(1) TO WS-MENOR-SAL
           
           PERFORM UNTIL WS-I > 100
               IF WS-NOME(WS-I) NOT = SPACES THEN
                   DISPLAY "ID: " WS-ID(WS-I)
                   DISPLAY "NOME: " WS-NOME(WS-I)
                   DISPLAY "SAL LIQ: " WS-SAL-LIQ(WS-I)
                   DISPLAY "---"
                   
                   ADD WS-SAL-LIQ(WS-I) TO WS-TOTAL-FOLHA
                   ADD 1 TO WS-J
                   
                   IF WS-SAL-LIQ(WS-I) > WS-MAIOR-SAL THEN
                       MOVE WS-SAL-LIQ(WS-I) TO WS-MAIOR-SAL
                   END-IF
                   
                   IF WS-SAL-LIQ(WS-I) < WS-MENOR-SAL THEN
                       MOVE WS-SAL-LIQ(WS-I) TO WS-MENOR-SAL
                   END-IF
               END-IF
               ADD 1 TO WS-I
           END-PERFORM.
           
           COMPUTE WS-MEDIA-SAL = 
               WS-TOTAL-FOLHA / WS-J
           
           DISPLAY "===== RESULTADOS ====="
           DISPLAY "TOTAL FOLHA: " WS-TOTAL-FOLHA
           DISPLAY "MEDIA SAL: " WS-MEDIA-SAL
           DISPLAY "MAIOR SAL: " WS-MAIOR-SAL
           DISPLAY "MENOR SAL: " WS-MENOR-SAL.
