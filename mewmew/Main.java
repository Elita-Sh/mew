//package enrollee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        cycle:
        while (true) {
            System.out.println("\n1. Заполнить массив абитуриентов");
            System.out.println("2. Распечатать список абитуриентов");
            System.out.println("3. Список зачисленных по общему баллу");
            System.out.println("4. Список не зачисленных, но с индивидуальными достижениями");
            System.out.println("5. Упорядочить по убыванию суммы баллов за экзамены");
            System.out.println("6. Выход");
            System.out.println("Выберите пункт меню (1..6)");

            int c = sc.nextInt();
            switch (c) {
                case 1:
                    AbiturienService.fillAbiturients();
                    break;
                case 2:
                    AbiturienService.printAbiturients();
                    break;
                case 3:
                    System.out.print("Введите общее количество мест: ");
                    int seats = sc.nextInt();
                    Abiturient.admittedList(seats);
                    break;
                case 4:
                    System.out.print("Введите общее количество мест: ");
                    int seats2 = sc.nextInt();
                    Abiturient.notAdmittedWithAchievements(seats2);
                    break;
                case 5:
                    AbiturienService.sortByExamSumDesc();
                    break;
                default:
                    break cycle;
            }
        }
        System.out.println("Работа программы завершена.");
    }
}