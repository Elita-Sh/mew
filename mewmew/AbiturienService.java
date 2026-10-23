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

    //сортировка массива по убыванию
    public static void sortByExamSumDesc(){
        for (int i = 0; i < abiturients.length - 1; i++)
            for (int j = 0; j < abiturients.length - 1 - i; j++)
                if (abiturients_examSum(j) < abiturients_examSum(j + 1)){
                    Abiturient rab = abiturients[j];
                    abiturients[j] = abiturients[j + 1];
                    abiturients[j + 1] = rab;
                }
        System.out.println("\nСписок абитуриентов, упорядоченный по убыванию суммы баллов за экзамены:");

    }
    // вспомогательный
    private static int abiturients_examSum(int index) {
        return abiturients[index].getExamSum();
    }

}
