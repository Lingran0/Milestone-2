package main;

import textProcessor.textProcessor;
import statisticCalculator.statisticCalculator;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        textProcessor textProcessor;
        statisticCalculator statisticCalculator = new statisticCalculator();

        try {
            textProcessor = new textProcessor("src/stopWords.txt");

            while (true) {
                System.out.println("\nSelect an operation: ");
                System.out.println("1. Remove stop words");
                System.out.println("2. Calculate basic statistics");
                System.out.println("3. Sort by word frequency");
                System.out.println("4. Calculate vocabulary richness");
                System.out.println("5. Find the most repeated words");
                System.out.println("6. Determine sentiment (positive or negative)");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        removeStopWords(scanner, textProcessor);
                        break;
                    case 2:
                        calculateBasicStats(scanner, textProcessor, statisticCalculator);
                        break;
                    case 3:
                        rankWordByFrequency(scanner, textProcessor, statisticCalculator);
                        break;
                    case 4:
                        calculateVocabularyRichness(scanner, textProcessor, statisticCalculator);
                        break;
                    case 5:
                        findMostRepeatedWords(scanner, textProcessor, statisticCalculator);
                        break;
                    case 6:
                        determineSentiment(scanner, textProcessor, statisticCalculator);
                        break;
                    case 0:
                        System.out.println("End of program\n");
                        return;
                    default:
                        System.out.println("Invalid selection, please try again");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("The stop word file cannot be found: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Method 1: Remove the stop word
    private static void removeStopWords(Scanner scanner, textProcessor textProcessor) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        String filePath = scanner.nextLine();
        
        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);
        
        System.out.println("\nContent after deletion of stop words: ");
        System.out.println(filteredContent);
    }

    //  Method 2: Calculate basic statistics
    private static void calculateBasicStats(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        String filePath = scanner.nextLine();
        
        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);
        
        int wordCount = statisticCalculator.calculateWords(filteredContent);
        int sentenceCount = statisticCalculator.calculateStatements(filteredContent);
        
        System.out.println("\nBasic statistics: ");
        System.out.println("Total word count: " + wordCount);
        System.out.println("Total sentences: " + sentenceCount);
    }

    // Method 3: Sort by word frequency
    private static void rankWordByFrequency(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        String filePath = scanner.nextLine();
        
        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);
        
        Map<String, Integer> wordFrequency = statisticCalculator.calculateWordFrequency(filteredContent);
        
        System.out.println("\nWords sorted by word frequency: ");
        wordFrequency.forEach((word, frequency) -> 
            System.out.println("word: " + word + " - frequency: " + frequency)
        );
    }

    // (New) Method 4: Calculating Vocabulary Richness (Number of unique words)
    private static void calculateVocabularyRichness(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        String filePath = scanner.nextLine();
        
        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);
        
        int uniqueWordCount = statisticCalculator.calculateVocabularyRichness(filteredContent);
        
        System.out.println("Vocabulary richness: " + uniqueWordCount);
    }

    //  (New) Method 5: Find the Most Repeated Words (Top three)
    private static void findMostRepeatedWords(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        String filePath = scanner.nextLine();
        
        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);
        
        Map<String, Integer> mostRepeatedWords = statisticCalculator.mostRepeatedWords(filteredContent);
        
        System.out.println("The most repeated words: ");
        mostRepeatedWords.forEach((word, frequency) ->
        System.out.println("word: " + word + " - frequency: " + frequency)
        );
    }
    
    // (New) Method 6: Determine Sentiment
    private static void determineSentiment(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        String filePath = scanner.nextLine();
        
        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);
        
        double sentimentScore = statisticCalculator.calculateSentimentScore(filteredContent);
        
        System.out.println("Sentiment score: " + sentimentScore);

        // Determine and print sentiment based on score
        String sentiment = statisticCalculator.determineSentiment(sentimentScore);
        System.out.println("Sentiment: " + sentiment); 
    }
}
