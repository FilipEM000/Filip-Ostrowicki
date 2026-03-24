package pd05;

import java.util.Scanner;

public class MatrixOperations {
    public static void main(String[] args) {

        int[][] matrix = createNewMatrix();

        printMatrix(matrix);
        printTranspositionOfMatrix(matrix);
        printMatrixTurnedRight(matrix);
        printSumsOfRows(matrix);
        printSumsOfCulumns(matrix);
        if (isMatrixSymetric(matrix)) {
            System.out.println("\nMacierz jest symetryczna");
        } else {
            System.out.println("\nMacierz nie jest symetryczna");
        }
    }

    private static boolean isMatrixSymetric(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printSumsOfCulumns(int[][] matrix) {
        System.out.println("------- Suma kolumn: ");
        for (int i = 0; i < 3; i++) {
            int sumOfColumn = 0;
            for (int j = 0; j < 3; j++) {
                sumOfColumn = sumOfColumn + matrix[j][i];
            }
            System.out.println("Suma " + (i + 1) + " kolumny = " + sumOfColumn);
        }
    }

    private static void printSumsOfRows(int[][] matrix) {
        System.out.println("------- Suma wierszy: ");
        for (int i = 0; i < 3; i++) {
            int sumOfRow = 0;
            for (int j = 0; j < 3; j++) {
                sumOfRow = sumOfRow + matrix[i][j];
            }
            System.out.println("Suma " + (i + 1) + " wiersza = " + sumOfRow);
        }
    }

    private static void printMatrixTurnedRight(int[][] matrix) {
        System.out.println("------- Macierz obrócona 90 stopni w prawo: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 2; j >= 0; j--) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }

    private static void printTranspositionOfMatrix(int[][] matrix) {
        System.out.println("------- Transpozycja macierzy: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("------- Macierz: ");
        for (int[] rows : matrix) {
            System.out.println(rows[0] + " " + rows[1] + " " + rows[2]);
        }
    }

    private static int[][] createNewMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj po kolei 9 liczb, które będą reprezentować macierz 3x3");
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        return matrix;
    }
}
