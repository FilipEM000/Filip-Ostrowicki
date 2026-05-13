package pd06;

import java.util.Arrays;
import java.util.Scanner;

public class MathLibrary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> {
                System.out.println("Podaj liczbę: ");
                System.out.println(factorialRecursion(scanner.nextInt()));
            }
            case 2 -> {
                System.out.println("Podaj liczbę: ");
                System.out.println(factorialIteration(scanner.nextInt()));
            }
            case 3 -> {
                System.out.println("Podaj liczbę: ");
                if (isPrime(scanner.nextInt())) {
                    System.out.println("Liczba pierwsza");
                } else {
                    System.out.println("To nie jest liczba pierwsza");
                }
            }
            case 4 -> {
                System.out.println("Podaj górny zakres:");
                int[] result = sieveOfEratosthenes(scanner.nextInt());
                for (int number : result) {
                    if (number != 0) {
                        System.out.printf(number + " ");
                    }
                }
            }
            case 5 -> {
                System.out.println("Podaj pierwszą liczbę: ");
                int a = scanner.nextInt();
                System.out.println("Podaj drugą liczbę:");
                int b = scanner.nextInt();
                System.out.println(gcd(a, b));
            }
            case 0 -> System.out.println("Pa pa!");
            default -> System.out.println("Podałeś złą liczbę");
        }
    }

    private static void printMenu() {
        System.out.println("[1] - silnia (iteracyjnie)\n[2] - silnia (rekurencyjnie)\n[3] - sprawdzenie czy liczba jest liczbą pierwszą\n[4] - sito Eratostenesa\n[5] - Największy wspólny dzielnik\n[0] - wyjdź z programu");
    }

    /**
     * this method calculates the factor of the provided number by Recursion
     *
     * @param n is the integer number that will be factored
     * @return factor of the parameter
     */
    static long factorialRecursion(int n) {
        return n > 1 ? n * factorialRecursion(n - 1) : n;
    }


    /**
     * this method calculates the factor of the provided number by Iteration
     *
     * @param n is the integer number that will be factored
     * @return factor of the parameter
     */
    static long factorialIteration(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }


    /**
     * this method checks if the provided number is a prime number
     *
     * @param n is the integer number that will be checked if it is prime
     * @return true or false depending on provided number is prime
     */
    static boolean isPrime(int n) {
        int numberOfFactors = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                numberOfFactors += 1;
            }
        }
        return numberOfFactors == 2;
    }


    /**
     * this method creates the sieveOfEratosthenes
     *
     * @param limit is the integer number that will tell us the upper limit of the sieve
     * @return int array of the prime numbers within limit
     */

    static int[] sieveOfEratosthenes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        int[] result = new int[limit];
        result[0] = 1;
        result[1] = 2;
        int numberOfPrimes = 2;
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i)
                    isPrime[j] = false;
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                result[numberOfPrimes] = i;
                numberOfPrimes++;
            }
        }
        return result;
    }

    /**
     * this method calculates Greatest Common Divisor for 2 numbers
     *
     * @param a is the first integer number
     * @param b is the second integer number
     * @return int number that represents Greatest Common Divisor for 2 provided numbers
     */

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
