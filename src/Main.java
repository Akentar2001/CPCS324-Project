import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner readingInput = new Scanner(System.in);
        System.out.print("How many lines you want to read from the text file? ");
        int numberOfLines = readingInput.nextInt();
        System.out.print("How many patterns to be generated? ");
        int numberOfPatterns = readingInput.nextInt();
        System.out.print("What is the length of each pattern? ");
        int lengthOfEachPattern = readingInput.nextInt();
        System.out.println("hello gitHub");
    }
}