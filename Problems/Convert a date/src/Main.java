import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] tokens = text.split("-");
        System.out.println(tokens[1] + "/" + tokens[2] + "/" + tokens[0]);
    }
}