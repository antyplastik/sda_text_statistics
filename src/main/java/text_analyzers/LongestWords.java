package text_analyzers;

import java.util.*;
import java.util.stream.Collectors;

public class LongestWords implements Analyzer<Map<String, Integer>> {

    private final int howManyWords;
    private int howManyAppeared;
    private Map<String, Integer> wordLengthMap;
    private Map<String, Integer> wordsRepetitionMap;
    private Map<String, Integer> resultMap;

    public LongestWords(int howManyAppeared, int howManyWords) {
        this.howManyWords = howManyWords;
        this.howManyAppeared = howManyAppeared;
        this.wordLengthMap = new HashMap<>();
        this.wordsRepetitionMap = new HashMap<>();
        this.resultMap = new HashMap<>();
    }

    @Override
    public Map<String, Integer> analyze(String string) {

        String filteredString = stringFilter(string);

        Arrays.stream(filteredString.split(" "))
                .map(String::toLowerCase)
                .map(x -> addToWordLengthMap(x))
                .map(x -> addToWordsRepetitionMap(x))
                .collect(Collectors.joining());

        sortMaps();

        resultMap = wordLengthMap.entrySet().stream()
                .filter(key -> wordsRepetitionMap.get(key.getKey()).equals(howManyAppeared))
                .limit(howManyWords)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return resultMap;
    }


    private String stringFilter(String string) {
        return string.chars()
                .map(x -> !(Character.isLetter(x) || (Character.isWhitespace(x) && x != '\n' && x != ' ')) ? x = ' ' : x)
                .mapToObj(x -> (char) x + "")
                .collect(Collectors.joining());
    }

    private void sortMaps() {
        wordLengthMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//        this.wordsRepetitionMap = wordsRepetitionMap.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1,LinkedHashMap::new));
    }

    private String addToWordLengthMap(String word) {
        int strLen = word.length();
        if (!wordLengthMap.containsKey(word))
            wordLengthMap.put(word, strLen);
        return word;
    }

    private String addToWordsRepetitionMap(String word) {
        if (!wordsRepetitionMap.containsKey(word))
            wordsRepetitionMap.put(word, 1);
        else
            wordsRepetitionMap.put(word, (wordsRepetitionMap.get(word) + 1));
        return word;
    }

    public Map<String, Integer> getResultMap() {
        return resultMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
