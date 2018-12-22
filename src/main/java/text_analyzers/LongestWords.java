package text_analyzers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongestWords implements Analyzer<Map<String, Integer>> {

    private final int howManyWords;
    private int howManyAppeared;
    private Map<String, Integer> wordLengthMap;
    private Map<String, Integer> WordsRepetitionMap;

    public LongestWords(int howManyAppeared, int howManyWords) {
        this.howManyWords = howManyWords;
        this.howManyAppeared = howManyAppeared;
        wordLengthMap = new HashMap<>();
        WordsRepetitionMap = new HashMap<>();
    }

    @Override
    public Map<String, Integer> analyze(String string) {

        String filteredString = stringFilter(string);

        List<String> stringList = Arrays.stream(filteredString.split(" "))
                .map(String::toLowerCase)
                .map(x -> addToWordLengthMap(x))
                .map(x -> addToWordsRepetitionMap(x))
                .collect(Collectors.toList());

        sortMaps();

        Map<String, Integer> mergedStreams = Stream.concat(wordLengthMap.entrySet().stream(), WordsRepetitionMap.entrySet().stream())
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue(),
                        (wordLengthMap, wordsRepetitionMap) -> wordLengthMap + wordsRepetitionMap
                ));

        wordLengthMap = wordLengthMap.entrySet().stream()
                .filter(map -> map.getValue().equals(howManyAppeared))
                .limit(howManyWords)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        return wordLengthMap;
    }


    private String stringFilter(String string) {
        return string.chars()
                .map(x -> !(Character.isLetter(x) || (Character.isWhitespace(x) && x != '\n' && x != ' ')) ? x = ' ' : x)
                .mapToObj(x -> (char) x + "")
                .collect(Collectors.joining());
    }

    private void sortMaps() {
        wordLengthMap = wordLengthMap.entrySet().stream()
                .map(Map.Entry.comparingByValue().reversed())
                .collect();


    }

    private String addToWordLengthMap(String word) {
        int strLen = word.length();
        if (!wordLengthMap.containsKey(word))
            wordLengthMap.put(word, strLen);
        return word;
    }

    private String addToWordsRepetitionMap(String word) {
        if (!WordsRepetitionMap.containsKey(word))
            WordsRepetitionMap.put(word, 1);
        else
            WordsRepetitionMap.put(word, (WordsRepetitionMap.get(word) + 1));
        return word;
    }

    public Map<String, Integer> getWordLengthMap() {
        return wordLengthMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
