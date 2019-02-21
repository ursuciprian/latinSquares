import java.util.Scanner;

public class LatinSquare {

    static void printSimetricLatinSquare(int n){

        int k = n+1;
        for (int i = 1; i<=n; i++) {
            int tmp = k;
            while (tmp <= n) {

                System.out.print(tmp + " ");
                tmp++;
            }
            for (int j = 1; j < k; j++) {

                System.out.print(j + " ");
            }
            k--;
            System.out.println();

        }
}
}
