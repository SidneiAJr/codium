import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Datas {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        System.out.println(hoje);

        LocalDate aniversarioAlice = LocalDate.of(2005, Month.SEPTEMBER, 15);
        System.out.println(aniversarioAlice);

        int idade = aniversarioAlice.getYear() - hoje.getYear();
        System.out.println(idade);

        Period periodo = Period.between(hoje, aniversarioAlice);
        System.out.println(periodo);

    }
}
