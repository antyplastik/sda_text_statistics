package text_analyzers;

import java.util.*;
import java.util.stream.Collectors;

public class LongestWords implements Analyzer<Map<String, Integer>> {

    private final int howManyWords;
    private int howManyAppeared;
    private Map<String, Integer> wordLengthMap;
    private Map<String, Integer>

    public LongestWords(int howManyAppeared, int howManyWords) {
        this.howManyWords = howManyWords;
        this.howManyAppeared = howManyAppeared;
        wordLengthMap = new HashMap<>();
    }

    @Override
    public Map<String, Integer> analyze(String string) {

        String filteredString = string.chars()
                .map(x -> !(Character.isLetter(x) || (Character.isWhitespace(x) && x != '\n' && x != ' ')) ? x = ' ' : x)
                .mapToObj(x -> (char) x + "")
                .collect(Collectors.joining());

        List<String> stringList = Arrays.stream(filteredString.split(" "))
                .map(String::toLowerCase)
                .map(x -> addToWordLengthMap(x))
                .collect(Collectors.toList());

        wordLengthMap = wordLengthMap.entrySet().stream()
                .filter(map -> map.getValue().equals(howManyAppeared))
                .limit(howManyWords)
                .collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));

        return wordLengthMap;
    }

    private String addToWordLengthMap(String word) {
        int strLen = word.length();
        if (!wordLengthMap.containsKey(word))
            wordLengthMap.put(word, strLen);
        return word;
    }

    private String addTo

    public Map<String, Integer> getWordLengthMap() {
        return wordLengthMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
