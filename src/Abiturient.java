
import com.sun.source.util.SourcePositions;

import java.util.Scanner;

public class Abiturient {
    //поля класса
    private String surname;
    private int exam1;
    private int exam2;
    private int exam3;
    private int achievements;
    //статическое содержимое класса
    private static Abiturient[] abiturients;
    public static Scanner sc = new Scanner(System.in, "UTF-8");
    static{

        abiturients = new Abiturient[]{
                new Abiturient("Кудухова",85,90,78,5),
                new Abiturient("Шавлохова",80,78,90,4),
                new Abiturient("Кокаева",85,78,90,4)

        };
    }
    //конструктор с параметрами
    public Abiturient(String surname, int exam1, int exam2, int exam3, int achievements){
        this.surname = surname;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
        this.achievements = achievements;
    }
    //конструктор без значений (запр. дан.)
    public Abiturient(){
        Scanner sc = new Scanner(System.in);
        boolean ok;
        do {
            System.out.print("фамилия:");
            ok = this.setSurname(readString());
        } while (!ok);

        do {
            System.out.print("\tэкзамен1 (баллы):");
            ok = this.setExam1(readInt(sc));
        } while (!ok);

        do {
            System.out.print("\tэкзамен2 (баллы):");
            ok = this.setExam2(readInt(sc));
        } while (!ok);

        do {
            System.out.print("\tэкзамен3 (баллы):");
            ok = this.setExam3(readInt(sc));
        } while (!ok);

        do {
            System.out.print("\tиндивидуальные достижения (баллы):");
            ok = this.setAchievements(readInt(sc));
        } while (!ok);
    }
    private static String readString() {

        while (true) {

            String value = sc.nextLine().trim();

            if (value.isEmpty()) {
                System.out.print("Заполните строку! Повторите ввод: ");
            }
            else if (!value.matches("[a-zA-Zа-яА-Я]+")) {
                System.out.print("Цифры и символы запрещены! Повторите ввод: ");
            }
            else {
                return value;
            }
        }
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
    public String toString(){
        return String.format(
                "фамилия: %s ==> экзамен1: %d экзамен2: %d экзамен3: %d" +
                        "инд.достижения = %d сумма экзаменов = %d общий балл = %d",
                surname, exam1, exam2, exam3,achievements, getExamSum(), getTotalScore());
    }
    //геттеры и сеттеры с проверкой значений
    public String getSurname(){
        return this.surname;
    }
    public boolean setSurname(String surname){
        if (surname == null || surname.trim().isEmpty()) {
            System.out.println("недопустимое значение!");
            return false;
        }
        this.surname = surname;
        return true;
    }

    public int getExam1(){
        return exam1;
    }

    public boolean setExam1(int exam1) {
        if (exam1 < 0 || exam1 > 100) {
            System.out.println("недопустимое значение!");
            return false;
        } else {
            this.exam1 = exam1;
            return true;
        }
    }

    public int getExam2() {
        return exam2;
    }
    public boolean setExam2(int exam2) {
        if (exam2 < 0 || exam2 > 100) {
            System.out.println("недопустимое значение!");
            return false;
        } else {
            this.exam2 = exam2;
            return true;
        }
    }

    public int getExam3() {
        return exam3;
    }
    public boolean setExam3(int exam3) {
        if (exam3 < 0 || exam3 > 100) {
            System.out.println("недопустимое значение!");
            return false;
        } else {
            this.exam3 = exam3;
            return true;
        }
    }

    public int getAchievements() {
        return achievements;
    }
    public boolean setAchievements(int achievements) {
        if (achievements < 0) {
            System.out.println("недопустимое значение!");
            return false;
        } else {
            this.achievements = achievements;
            return true;
        }
    }
    //сумма баллов
    public int getExamSum(){
        return exam1 + exam2 + exam3;
    }
    //общий балл
    public int getTotalScore(){
        return getExamSum() + achievements;
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


    //распкчатка массива
    public static void printAbiturients(){
        System.out.println("\nсписок абитуриентов:");
        for (Abiturient a: abiturients){
            System.out.println(a);
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
        printAbiturients();
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

    public static int getSize() {
        return abiturients.length;
    }
}






























































//import com.sun.source.util.SourcePositions;
//
//import java.util.Scanner;
//
//public class Abiturient {
//    Scanner sc = new Scanner(System.in);
//    //поля класса
//    private String surname;
//    private int exam1;
//    private int exam2;
//    private int exam3;
//    private int achievements;
//
//
//    //статическое содержимое класса
//    private static Abiturient[] abiturients;
//    static{
//
//        abiturients = new Abiturient[]{
//                new Abiturient("Кудухова",85,90,78,5),
//                new Abiturient("Шавлохова",80,78,90,4),
//                new Abiturient("Кокаева",85,78,90,4)
//        };
//    }
//    //конструктор с параметрами
//    public Abiturient(String surname, int exam1, int exam2, int exam3, int achievеments){
//       this.surname = surname;
//       this.exam1 = exam1;
//       this.exam2 = exam2;
//       this.exam3 = exam3;
//       this.achievements = achievеments;
//    }
//    //конструктор без значений
//    public Abiturient(){
//        Scanner sc = new Scanner(System.in);
//        System.out.print("фамилия:");
//        this.setSurname(sc.nextLine());
//        System.out.print("\tэкзамен1 (баллы):");
//        this.setExam1(sc.nextInt());
//        System.out.print("\tэкзамен2 (баллы):");
//        this.setExam2(sc.nextInt());
//        System.out.print("\tэкзамен3 (баллы):");
//        this.setExam3(sc.nextInt());
//        System.out.print("\tиндивидуальные достижения (баллы):");
//        this.setAchievements(sc.nextInt());
//        sc.nextLine();
//    }
//
//
//    public String toString(){
//        return String.format(
//                "фамилия: %s ==> экзамен1: %d экзамен2: %d экзамен3: %d" +
//                        "инд.достижения = %d сумма экзаменов = %d общий балл = %d",
//                surname, exam1, exam2, exam3,achievements, getExamSum(), getTotalScore());
//    }
//    //геттеры и сеттеры с проверкой значений
//    public String getSurname(){
//        return this.surname;
//    }
//    public void setSurname(String surname){
//        if (surname == null || surname.trim().isEmpty())
//            System.out.println("недопустимое значение!");
//        else
//            this.surname = surname;
//    }
//
//    public int getExam1(){
//        return exam1;
//    }
//
//    public void setExam1(int exam1) {
//        if (exam1 < 0 || exam1 > 100)
//            System.out.println("недопустимое значение!");
//        else
//            this.exam1 = exam1;
//    }
//
//    public int getExam2() {
//        return exam2;
//    }
//    public void setExam2(int exam2) {
//        if (exam2 < 0 || exam2 > 100)
//            System.out.println("недопустимое значение!");
//        else
//            this.exam2 = exam2;
//    }
//
//    public int getExam3() {
//        return exam3;
//    }
//    public void setExam3(int exam3) {
//        if (exam3 < 0 || exam3 > 100)
//            System.out.println("недопустимое значение!");
//        else
//            this.exam3 = exam3;
//    }
//
//    public int getAchievements() {
//        return achievements;
//    }
//    public void setAchievements(int achievеments) {
//        if (achievеments < 0)
//            System.out.println("недопустимое значение!");
//        else
//            this.achievements = achievеments;
//    }
//    //сумма баллов
//    public int getExamSum(){
//        return exam1 + exam2 + exam3;
//    }
//    //общий балл
//    public int getTotalScore(){
//        return getExamSum() + achievements;
//    }
//    //заполнение массива с клавиатуры
//    public static void fillAbiturients(){
//        System.out.println("Введите количество новых абитуриентов => ");
//        int n = new Scanner(System.in).nextInt();
//
//        int oldLength = abiturients.length;
//        Abiturient[] extended = new Abiturient[oldLength + n];
//        System.arraycopy(abiturients, 0, extended, 0, oldLength);
//
//        System.out.println("Введите информацию об абитуриентах:");
//        for (int i = 0; i < n; i++) {
//            System.out.println("Абитуриент " + (i + 1) + " =>");
//            extended[oldLength + i] = new Abiturient();
//        }
//
//        abiturients = extended;
//    }
//    //распкчатка массива
//    public static void printAbiturients(){
//        System.out.println("\nсписок абитуриентов:");
//        for (Abiturient a: abiturients){
//            System.out.println(a);
//        }
//    }
//    //сортировка массива по убыванию
//    public static void sortByExamSumDesc(){
//        for (int i = 0; i < abiturients.length - 1; i++)
//            for (int j = 0; j < abiturients.length - 1 - i; j++)
//                if (abiturients_examSum(j) < abiturients_examSum(j + 1)){
//                    Abiturient rab = abiturients[j];
//                    abiturients[j] = abiturients[j + 1];
//                    abiturients[j + 1] = rab;
//        }
//        System.out.println("\nСписок абитуриентов, упорядоченный по убыванию суммы баллов за экзамены:");
//        printAbiturients();
//    }
//
//    // вспомогательный метод (чтобы не дублировать вызов getExamSum())
//    private static int abiturients_examSum(int index) {
//        return abiturients[index].getExamSum();
//    }
//
//    // список абитуриентов, зачисленных на 90% мест (по общему баллу)
//    public static void admittedList(int totalSeats) {
//        int admittedCount = (int) Math.round(totalSeats * 0.9);
//        // сортируем копию массива по убыванию общего балла (экзамены + достижения)
//        Abiturient[] sorted = abiturients.clone();
//        for (int i = 0; i < sorted.length - 1; i++)
//            for (int j = 0; j < sorted.length - 1 - i; j++)
//                if (sorted[j].getTotalScore() < sorted[j + 1].getTotalScore()) {
//                    Abiturient rab = sorted[j];
//                    sorted[j] = sorted[j + 1];
//                    sorted[j + 1] = rab;
//                }
//
//        int limit = Math.min(admittedCount, sorted.length);
//        System.out.println("\nВсего мест: " + totalSeats + ", на 90% мест зачислено: " + limit);
//        System.out.println("Список зачисленных абитуриентов:");
//        for (int i = 0; i < limit; i++) {
//            System.out.println(sorted[i]);
//        }
//    }
//
//    // абитуриенты, не попавшие в список зачисленных, но имеющие
//    // индивидуальные достижения
//    public static void notAdmittedWithAchievements(int totalSeats) {
//        int admittedCount = Math.min((int) Math.round(totalSeats * 0.9), abiturients.length);
//
//        Abiturient[] sorted = abiturients.clone();
//        for (int i = 0; i < sorted.length - 1; i++)
//            for (int j = 0; j < sorted.length - 1 - i; j++)
//                if (sorted[j].getTotalScore() < sorted[j + 1].getTotalScore()) {
//                    Abiturient rab = sorted[j];
//                    sorted[j] = sorted[j + 1];
//                    sorted[j + 1] = rab;
//                }
//
//        System.out.println("\nАбитуриенты, не прошедшие по конкурсу, но имеющие " +
//                "индивидуальные достижения:");
//        boolean found = false;
//        for (int i = admittedCount; i < sorted.length; i++) {
//            if (sorted[i].getAchievements() > 0) {
//                System.out.println(sorted[i]);
//                found = true;
//            }
//        }
//        if (!found) {
//            System.out.println("Таких абитуриентов нет.");
//        }
//    }
//
//    public static int getSize() {
//        return abiturients.length;
//    }
//}









