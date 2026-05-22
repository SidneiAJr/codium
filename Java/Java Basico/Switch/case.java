import java.util.Scanner;

class Program3 {
   public static void main(String[] args) {
   Scanner entrada = new Scanner(System.in);
   
   int mes;
   String str;
   System.out.print("Digite o mes em numero ");
   mes=entrada.nextInt();


   switch(mes){
      case 1:
       str ="Jan";
        break;
        case 2:
        str ="Fev";
        break;
        case 3:
        str ="Marco";
        break;
        case 4:
        str ="Abril";
        break;
        case 5:
        str ="Abril";
        break;
        case 6:
        str ="Jun";
        break;
        case 7:
        str ="Jul";
        break;
        case 8:
        str ="Ago";
        break;
        case 9:
        str ="Set";
        break;
        case 10:
        str ="Out";
        break;
        case 11:
        str ="Nov";
        break;
        case 12:
        str ="Dez";
        break;
        default:
        str = "Mes Invalido";
        break;
   }
     System.out.println("Mes selecionado\n"+str);


    }
}
