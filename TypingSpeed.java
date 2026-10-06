import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TypingSpeed {

    // A collection of test sentences to prompt the user
    private static final String[] PROMPTS = {
        "The quick brown fox jumps over the lazy dog.",
        "Java is a verbose, object-oriented programming language.",
        "A missing semicolon will cause a build failure in enterprise applications.",
        "Building muscle memory for syntax directly translates to faster feature development.",
        "Consistency and precision matter far more than raw keystroke speed."
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 1. Pick a random prompt string
        String targetText = PROMPTS[random.nextInt(PROMPTS.length)];

        System.out.println("=== CLI Java Typing Speed Test ===");
        System.out.println("Get ready to type the following text exacty as it appears:\n");
        System.out.println("\"" + targetText + "\"");
        System.out.println("\nStarting countdown...");

        // 2. Visual countdown before the timer starts
        try {
            for (int i = 3; i > 0; i--) {
                System.out.print(i + "... ");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("GO!\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 3. Capture the exact start time and user input
        long startTime = System.currentTimeMillis();
        String userInput = scanner.nextLine();
        long endTime = System.currentTimeMillis();

        // 4. Calculate total elapsed time in minutes
        double elapsedMinutes = (endTime - startTime) / 60000.0;

        // 5. Evaluate Accuracy and Net WPM metrics
        int correctCharacters = calculateCorrectCharacters(targetText, userInput);
        double accuracy = ((double) correctCharacters / targetText.length()) * 100;
        
        // Industry standard WPM formula: (Total Correct Characters / 5) / Time in Minutes
        double grossWpm = (userInput.length() / 5.0) / elapsedMinutes;
        double netWpm = (correctCharacters / 5.0) / elapsedMinutes;

        // 6. Print Results
        System.out.println("\n========= RESULTS =========");
        System.out.printf("Time Taken   : %.2f seconds\n", (endTime - startTime) / 1000.0);
        System.out.printf("Accuracy     : %.2f%%\n", accuracy);
        System.out.printf("Gross Speed  : %.0f WPM\n", grossWpm);
        System.out.printf("Net Speed    : %.0f WPM\n", Math.max(0, netWpm));
        System.out.println("===========================");

        scanner.close();
    }

    /**
     * Compares the user input against the target text character-by-character
     * to count matched indices for accuracy calculations.
     */
    private static int calculateCorrectCharacters(String target, String input) {
        int matches = 0;
        int minLength = Math.min(target.length(), input.length());
        
        for (int i = 0; i < minLength; i++) {
            if (target.charAt(i) == input.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }
}