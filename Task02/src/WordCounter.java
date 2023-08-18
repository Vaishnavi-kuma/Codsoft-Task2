import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordCounter {
    private static final Set<String> stopWords = new HashSet<>(Arrays.asList(
            "the", "and", "a", "an", "in", "on", "at", "is", "it", "for", "to"));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText;

        System.out.println("Please select one of the following options:");
        System.out.println("1. Enter text manually");
        System.out.println("2. Provide a file");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            System.out.println("Enter your text here:");
            inputText = scanner.nextLine();
        } else if (option == 2) {
            System.out.println("Enter the file path here:");
            String filePath = scanner.nextLine();
            try {
                inputText = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading the respective file. Please make sure that the file exists and it is accessible.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid option. Please choose 1 or 2.");
            scanner.close();
            return;
        }

        int wordCount = countWords(inputText);
        System.out.println("Total number of words are: " + wordCount);

        scanner.close();
    }

    private static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    private static int countWords(String text) {
        text = text.trim().toLowerCase();
        String[] words = text.split("[\\p{Punct}\\s]+");
        int wordCount = 0;

        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word)) {
                wordCount++;
            }
        }

        return wordCount;
    }
}

