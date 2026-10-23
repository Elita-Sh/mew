import java.util.Scanner;


public class AbiturienService {
    //статическое содержимое класса
    static Abiturient[] abiturients;
    public static Scanner sc = new Scanner(System.in, "UTF-8");
    static{

        abiturients = new Abiturient[]{
                new Abiturient("Кудухова",85,90,78,5),
                new Abiturient("Шавлохова",80,78,90,4),
                new Abiturient("Кокаева",85,78,90,4),
        };
    }

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
    // абитуриенты, не попавшие в список зачисленных, но имеющие
    // индивидуальные достижения
    public static void notAdmittedWithAchievements(int totalSeats) {
        int admittedCount = Math.min((int) Math.round(totalSeats * 0.9), abiturients.length);

        Abiturient[] sorted = abiturients.clone();
        for (int i = 0; i < sorted.length - 1; i++)
            for (int j = 0; j < sorted.length - 1 - i; j++)
                if (sorted[j].getTotalScore() < sorted[j + 1].getTotalScore()) {
                    Abiturient rab = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = rab;
                }

        System.out.println("\nАбитуриенты, не прошедшие по конкурсу, но имеющие " +
                "индивидуальные достижения:");
        boolean found = false;
        for (int i = admittedCount; i < sorted.length; i++) {
            if (sorted[i].getAchievements() > 0) {
                System.out.println(sorted[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Таких абитуриентов нет.");
        }
    }
    public static Abiturient findByName(String name) {
        for (Abiturient a : abiturients) {
            if (a.getSurname().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }
    public static boolean removeByName(String name) {
        for (int i = 0; i < abiturients.length; i++) {
            if (abiturients[i].getSurname().equalsIgnoreCase(name)) {
                Abiturient[] result = new Abiturient[abiturients.length - 1];
                for (int j = 0, k = 0; j < abiturients.length; j++) {
                    if (j != i) result[k++] = abiturients[j];
                }
                abiturients = result;
                return true;
            }
        }
        return false;
    }
    public static int count() {
        return abiturients.length;
    }

}
