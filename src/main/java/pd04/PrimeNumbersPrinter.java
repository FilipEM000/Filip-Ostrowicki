package pd04;

public class PrimeNumbersPrinter {
    public static void main(String[] args) {
        for (int i = 2; i<=50; i++){
            int numberOfFactors = 0;
            for (int j = 1; j <= i; j++) {
                if(i%j == 0){
                    numberOfFactors+=1;
                }
            }
            if(numberOfFactors == 2){
                System.out.println(i);
            }

        }
    }
}
