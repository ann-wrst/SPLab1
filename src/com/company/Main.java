package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<String> ReadFile(File file) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        int maxLength = 30;
        String pattern = "[A-Za-zА-яа-я]";
        StringBuilder word = new StringBuilder();
        try (FileReader reader = new FileReader(file)) {
            int c;
            while (-1 != (c = reader.read())) {
                String character = String.valueOf(((char) c));
                if (character.matches(pattern))
                    word.append(character);
                else if (word.length() > 0) {
                    words.add(word.substring(0, Math.min(maxLength, word.length())));
                    word = new StringBuilder();
                }
            }
            if (word.length() > 0)
                words.add(word.substring(0, Math.min(maxLength, word.length())));
        }
        return words;
    }

    public static ArrayList<String> removeDuplicates(ArrayList<String> words) {
        ArrayList<String> finalWords = new ArrayList<>();
        for (String word : words) {
            if (!finalWords.contains(word)) {
                finalWords.add(word);
            }
        }
        return finalWords;
    }

    public static ArrayList<String> Filter(ArrayList<String> words) {
        String vowelsRegex = "[aeiouAEIOUаеиіоуяюєАЕИІОУЯЮЄ]*";
        ArrayList<String> FilteredWords = new ArrayList<>();
        for (String word : words)
            if (word.matches(vowelsRegex))
                FilteredWords.add(word);

        return FilteredWords;
    }

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\annwr\\IdeaProjects\\fileslab1\\src\\com\\company\\words.txt";
        File file = new File(path);
        ArrayList<String> result;
        try {
            result = ReadFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        result = removeDuplicates(result);
        result = Filter(result);
        System.out.println(result);
    }
}
