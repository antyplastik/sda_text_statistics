package text_analyzers;

import java.util.*;
import java.util.stream.Collectors;

public class MostPopularWords implements Analyzer<HashMap<String, Integer>> {

    private int howManyWords;
    private HashMap<String, Integer> resultMap;

    public MostPopularWords(int howManyWords) {
        this.howManyWords = howManyWords;
        this.resultMap = new HashMap<>();
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

        resultMap.remove("");

        resultMap = resultMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(howManyWords)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));

        return resultMap;
    }

    private String addToHashMap(String word) {
        if (resultMap.containsKey(word))
            resultMap.put(word, resultMap.get(word) + 1);
        else
            resultMap.put(word, 1);
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
