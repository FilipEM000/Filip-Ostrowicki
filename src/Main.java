import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);

        System.out.println("Podaj swoją wagę w kilogramach");
        double weightInKg = skaner.nextByte();

        System.out.println("Podaj swój wzrost w centymetrach");
        double heightInM = skaner.nextDouble() / 100;
        if (heightInM <= 0.3 || heightInM >= 2.5) {
            System.out.println("uwaga wzrost może być błędny");
        }

        double bmi = weightInKg / (heightInM * heightInM);
        System.out.printf("Twoje MBI to: %.2f \n", bmi);

        if (bmi < 18.5) {
            System.out.println("Niedowaga");
        } else if (bmi < 24.9) {
            System.out.println("Norma");
        } else if (bmi < 29.9) {
            System.out.println("Nadwaga");
        } else {
            System.out.println("Otyłość");
        }

    }
}