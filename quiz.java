import java.util.Random;
import java.util.Scanner;

public class MathQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        int totalQuestions = 5;

        for (int i = 0; i < totalQuestions; i++) {
            int num1 = random.nextInt(10) + 1;
            int num2 = random.nextInt(10) + 1;
            int operation = random.nextInt(3);
            int correctAnswer = 0;
            String question = "";

            if (operation == 0) {
                correctAnswer = num1 + num2;
                question = num1 + " + " + num2;
            } else if (operation == 1) {
                correctAnswer = num1 - num2;
                question = num1 + " - " + num2;
            } else {
                correctAnswer = num1 * num2;
                question = num1 + " * " + num2;
            }

            System.out.print("Solve: " + question + " = ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == correctAnswer) {
                score++;
            }
        }

        System.out.println("Quiz Complete!");
        System.out.println("Your score: " + score + " out of " + totalQuestions);
        scanner.close();
    }
}
