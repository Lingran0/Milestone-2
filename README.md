# Milestone-2

## Author
LINGRAN ZHANG  

CPSC2231L 01 Programming Workshop Lab  

2024Fall  

---

## Overview

This project is a robust **Java-based text analysis toolkit** designed to simplify processing and analyzing textual data. It provides a user-friendly, menu-driven interface to clean data, extract meaningful insights, and perform advanced text analysis, including sentiment evaluation.

Whether you're processing large text files for research or preparing datasets for machine learning, this toolkit streamlines essential tasks like removing stop words, calculating statistics, analyzing vocabulary richness, and understanding sentiment.

---

## Why This Project Is Useful

This project serves as a versatile and powerful tool for anyone working with textual data. Key benefits include:

- **Text Cleaning**: Helps clean text by removing stop words, making it ready for advanced analysis.
- **Detailed Statistics**: Quickly calculates important metrics like word count, sentence count, and vocabulary richness.
- **Actionable Insights**: Identifies commonly used words and their frequencies, providing deeper insight into text content.
- **Sentiment Analysis**: Understands the emotional tone of text (positive, negative, neutral).
- **Efficiency**: Automates repetitive and time-consuming text preprocessing tasks.
- **Educational Value**: Offers a great way to practice object-oriented programming and text processing techniques in Java.

---

## UML Diagram
![UML Diagram](https://github.com/Lingran0/Milestone-2/blob/f4957201a2f01cca7e73b4c86a872c648373d43c/UML%20Diagram.png)

---

## Features

This project provides the following functionalities:

1. **Remove Stop Words**: Filters out common stop words to focus on meaningful text.
2. **Calculate Basic Statistics**:
   - Total word count.
   - Total sentence count.
3. **Sort Words by Frequency**: Lists words sorted by their frequency of occurrence.
4. **Calculate Vocabulary Richness**: Measures the uniqueness of the text by counting unique words.
5. **Find Most Repeated Words**: Identifies the top three most frequently occurring words.
6. **Determine Sentiment**:
   - Calculates a sentiment score based on positive and negative word counts.
   - Outputs a sentiment classification (positive, negative, or neutral).

---

## Project Structure

The project is organized into the following components:

1. **`main.main`**:  
   The entry point of the program.  
   - Provides a menu-driven interface for user interaction.
   - Handles inputs, error messages, and controls the flow of the application.

2. **`textProcessor.textProcessor`**:  
   Responsible for text-related preprocessing tasks.  
   - Reads text files from the system.
   - Removes stop words using a predefined stop word file.

3. **`statisticCalculator.statisticCalculator`**:  
   Performs all statistical computations, word frequency ranking, and sentiment analysis.  
   - Calculates word and sentence counts.
   - Generates word frequency maps.
   - Evaluates vocabulary richness and sentiment scores.

---

## Usage Instructions
1. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse, BlueJ).
2. Run the `main` class located in the `src/main` folder.
3.  Place your text files in the appropriate directory.
4. Modify the file paths in the code as needed for your environment.
5. Run the program and follow the on-screen instructions to process your files.
