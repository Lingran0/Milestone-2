package statisticCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class statisticCalculator {
    private Set<String> positiveWords;
    private Set<String> negativeWords;
    private Map<String, Double> sentimentScores;

    // Constructor
    public statisticCalculator() {
        positiveWords = new HashSet<>();
        negativeWords = new HashSet<>();
        sentimentScores = new HashMap<>();
        loadSentimentLexicon("src/positive-words.txt", "src/negative-words.txt", "src/statisticCalculator.ctxt"); // Paths to your lexicon files
    }

    // Method to load the positive, negative words, and sentiment scores
    private void loadSentimentLexicon(String positiveFilePath, String negativeFilePath, String sentimentFilePath) {
        loadWords(positiveFilePath, positiveWords);
        loadWords(negativeFilePath, negativeWords);
        loadSentimentScores(sentimentFilePath);
    }

    // Helper method to load words from a file into a set
    private void loadWords(String filePath, Set<String> wordSet) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordSet.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error reading lexicon file: " + e.getMessage());
        }
    }

    // Helper method to load sentiment scores from the .ctxt file
    private void loadSentimentScores(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    sentimentScores.put(parts[0].toLowerCase(), Double.parseDouble(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading sentiment scores file: " + e.getMessage());
        }
    }

    // Calculate the number of words in the content
    public int calculateWords(String content) {
        String[] words = content.split("\\s+");
        return words.length;
    }

    // Calculate the number of sentences in the content
    public int calculateStatements(String content) {
        String[] sentences = content.split("[.!?]");
        return sentences.length;
    }

    // Calculate word frequency
    public Map<String, Integer> calculateWordFrequency(String content) {
        String[] words = content.toLowerCase().split("\\s+");
        
        Map<String, Integer> wordFrequency = new HashMap<>(); 

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "");

            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        return wordFrequency;
    }

    // Calculate vocabulary richness (unique words)
    public int calculateVocabularyRichness(String content) {
        Set<String> uniqueWords = new HashSet<>();
        String[] words = content.toLowerCase().split("\\s+");
        
        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "");
            if (!word.isEmpty()) {
                uniqueWords.add(word);
            }
        }

        return uniqueWords.size();
    }

    // Method to find the most repeated words
    public Map<String, Integer> mostRepeatedWords(String content) {
        Map<String, Integer> wordFrequency = calculateWordFrequency(content);

        return wordFrequency.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    // (New) Method to calculate sentiment score
    public double calculateSentimentScore(String content) {
        String[] words = content.toLowerCase().split("\\s+");
        double sentimentScore = 0.0;

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "");
            
            if (!word.isEmpty()) {
                // Add sentiment score from ctxt lexicon
                if (sentimentScores.containsKey(word)) {
                    sentimentScore += sentimentScores.get(word);
                }
                // Add positive or negative word scores
                else if (positiveWords.contains(word)) {
                    sentimentScore += 1;
                } else if (negativeWords.contains(word)) {
                    sentimentScore -= 1;
                }
            }
        }

        return sentimentScore;
    }

    // (New) Method to determine sentiment based on score
    public String determineSentiment(double sentimentScore) {
        if (sentimentScore > 0) {
            return "Positive";  
        } else if (sentimentScore < 0) {
            return "Negative"; 
        } else {
            return "Neutral";  
        }
    }
}

 