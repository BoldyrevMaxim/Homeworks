import Java.until.ArrayList;
import Java.until.Collections;
import Java.until.Scanner;

import java.util.ArrayList;

public class Main {
    static Scanner inp = new Scanner(System.in);
    public static void main(String[] args) {
        int length = inp.nextInt();
        ArrayList<Integer> numbers = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            numbers.add(inp.nextInt());
        }
        String[] mas = min_max(numbers).split("s");

        for (String i : mas) {
            cnt += 1;
            if (cnt == 1) System.out.print(i + "- min,");
            else System.out.println(i + "- max");
        }
    }
    public static String min_max(ArrayList<Integer> numbers){
        int min_number = Collections.min(numbers);
        int max_number = Collections.max(numbers);
        return min_number + "s" + max_number;
    }
}
