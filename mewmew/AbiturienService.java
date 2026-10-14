public class AbiturienService {
    private static Abiturient[] abiturients;
    //распкчатка массива
    public static void printAbiturients(){
        System.out.println("\nсписок абитуриентов:");
        for (Abiturient a: abiturients){
            System.out.println(a);
        }
    }
}
