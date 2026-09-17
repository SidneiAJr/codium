01 nota PIC 9(3) VALUE ZEROS.

MOVE 75 TO nota

IF nota >= 90
    DISPLAY "A"
ELSE IF nota >= 70
    DISPLAY "B"
ELSE IF nota >= 50
    DISPLAY "C"
ELSE
    DISPLAY "Reprovado"
END-IF.