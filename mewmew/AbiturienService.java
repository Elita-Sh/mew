import java.util.Scanner;

public class AbiturienService {
    private static Abiturient[] abiturients;
    //распечатка массива
    public static void printAbiturients(){
        System.out.println("\nсписок абитуриентов:");
        for (Abiturient a: abiturients){
            System.out.println(a);
        }
    }
    //заполнение массива с клавиатуры
    public static void fillAbiturients(){
        System.out.println("Введите количество новых абитуриентов => ");
        int n = new Scanner(System.in).nextInt();

        int oldLength = abiturients.length;
        Abiturient[] extended = new Abiturient[oldLength + n];
        System.arraycopy(abiturients, 0, extended, 0, oldLength);

        System.out.println("Введите информацию об абитуриентах:");
        for (int i = 0; i < n; i++) {
            System.out.println("Абитуриент " + (i + 1) + " =>");
            extended[oldLength + i] = new Abiturient();
        }
        abiturients = extended;
    }
    private static int readInt(Scanner sc) {
        String line = sc.nextLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            System.out.println("нужно ввести целое число!");
            return -1;
        }
    }

}
