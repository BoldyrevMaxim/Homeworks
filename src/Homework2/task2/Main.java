import java.util.Scanner;

public class Main {
    static Scanner inp = new Scanner(System.in);
    public static void main(String[] args) {
        int n = inp.nextInt();
        int[] mas = new int[n];

        for (int i = 0; i < n; i++){
            mas[i] = inp.nextInt();
        }

        System.out.println(checkBalance(mas, n));
    }

    public static Boolean checkBalance(int[] mas, int n) {
        int rightSum = 0;
        int leftSum = 0;

        for (int i = 0; i < n - 1; i++) {
            leftSum += mas[i];
            for (int j = i + 1; j < n; j++) {
                rightSum += mas[j];
            }
            if (leftSum == rightSum) return true;
            rightSum = 0;
        }
        return false;
    }
}
