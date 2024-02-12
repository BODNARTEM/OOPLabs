import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static double a, b, x;
    public static void main(String[] args) {
        print();
        double Y = calculateY(a, b, x);
        double D = calculateD(a, b, x);

        System.out.println("Y = " + Y);
        System.out.println("D = " + D);

        printDate();
    }

    public static double calculateY(double a, double b, double x) {
        return b * Math.pow(Math.tan(x), 2) - (a / Math.pow(Math.sin(x / a), 2));
    }

    public static double calculateD(double a, double b, double x) {
        return a * Math.exp(- Math.sqrt(a)) * Math.cos(b * x / a);
    }


    public static void printDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM, dd, yyyy, EEEE");
        String formattedDateTime = now.format(formatter);
        System.out.println("Поточна дата: " + formattedDateTime);
    }

    public static void print(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть значення 'a':");
        a = scanner.nextDouble();
        System.out.print("Введіть значення 'b':");
        b = scanner.nextDouble();
        System.out.print("Введіть значення 'x':");
        x = scanner.nextDouble();
    }
}
