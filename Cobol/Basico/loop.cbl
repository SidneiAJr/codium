01 contador PIC 9(3) VALUE ZEROS.

PERFORM UNTIL contador = 5
    ADD 1 TO contador
    DISPLAY "Contador: " contador
END-PERFORM.