package text_analyzers;

import one.util.streamex.EntryStream;

import java.util.*;
import java.util.stream.Collectors;

public class LongestWords implements Analyzer<HashMap<String, Integer>> {

    private final int howManyWords;
    private int howManyAppeared;
    private HashMap<String, Integer> resultMap;

    public LongestWords(int howManyAppeared, int howManyWords) {
        this.howManyWords = howManyWords;
        this.howManyAppeared = howManyAppeared;
        resultMap = new HashMap<>();
    }

    @Override
    public HashMap<String, Integer> analyze(String string) {

        String filteredString = string.chars()
                .map(x -> !(Character.isLetter(x) || (Character.isWhitespace(x) && x != '\n' && x != ' ')) ? x = ' ' : x)
                .mapToObj(x -> (char) x + "")
                .collect(Collectors.joining());

        List<String> stringStream = Arrays.stream(filteredString.split(" "))
                .map(String::toLowerCase)
                .map(x -> addToHashMap(x))
                .collect(Collectors.toList());

        resultMap = resultMap.entrySet().stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) // this must be .filter
                .map(entry -> new AbstractMap.SimpleEntry(entry.getKey(), Integer.valueOf(entry.getValue())))
                .filter(entry -> entry.getValue() == (Integer) howManyAppeared)
                .limit(howManyWords)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);

                resultMap = EntryStream.of(resultMap)
                        .mapValues(Integer::valueOf)
                        .filterValues(value->value == howManyAppeared)
                        .limit(howManyWords)
                        .toMap();

        return resultMap;
    }

    private String addToHashMap(String word) {
        int strLen = word.length();
        if (!resultMap.containsKey(word))
            resultMap.put(word, strLen);
        return word;
    }

    public HashMap<String, Integer> getResultMap() {
        return resultMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
