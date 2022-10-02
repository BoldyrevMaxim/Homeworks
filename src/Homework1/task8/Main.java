public class Main {
    public static void isLeap(int year) {
        if (year % 400 == 0) {
            System.out.println("It's leap year");
        } else if (year % 100 == 0) {
            System.out.println("It's not leap year");
        } else if (year % 4 == 0) {
            System.out.println("It's leap year");
    } else

    {
        System.out.println("It's not leap year");
    }

   }
    public static void main(String[] args) {
        isLeap(2023);
    }
}
