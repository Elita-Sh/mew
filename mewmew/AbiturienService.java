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
    // список абитуриентов, зачисленных на 90% мест (по общему баллу)
    public static void admittedList(int totalSeats) {
        int admittedCount = (int) Math.round(totalSeats * 0.9);
        // сортируем копию массива по убыванию общего балла (экзамены + достижения)
        Abiturient[] sorted = abiturients.clone();
        for (int i = 0; i < sorted.length - 1; i++)
            for (int j = 0; j < sorted.length - 1 - i; j++)
                if (sorted[j].getTotalScore() < sorted[j + 1].getTotalScore()) {
                    Abiturient rab = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = rab;
                }

        int limit = Math.min(admittedCount, sorted.length);
//        System.out.println("\nВсего мест: " + totalSeats  + limit);
        System.out.println("Список зачисленных абитуриентов на 90% мест:");
        for (int i = 0; i < limit; i++) {
            System.out.println(sorted[i]);
        }
    }

}
