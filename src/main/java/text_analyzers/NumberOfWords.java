package text_analyzers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberOfWords implements Analyzer<Integer> {

    private int numberOfWords;
    private HashMap<String, Integer> words;

    public NumberOfWords() {
        this.words = new HashMap<>();
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    @Override
    public Integer analyze(String string) {
//        String[] tmp = string.split("\\s");
        Stream<String> stream = Arrays.stream(string.split("\\s"));
        stream.forEach(string1 -> addToHashMap(string1));
        numberOfWords = words.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        return numberOfWords;
    }

    public void addToHashMap(String string) {
        if (words.containsKey(string))
            words.put(string, words.get(string) + 1);
        else
            words.put(string, 1);
    }

    public int getfromHashMap(String string) {
        return words.get(string);
    }

    @Override
    public String toString() {
        String result = "The text has " + numberOfWords + " word(s)";

        return result;
    }
}
