package text_analyzers;

import java.io.StringWriter;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LongestWords implements Analyzer<HashMap<String, Integer>> {

    private final int howMany;
    private HashMap<String, Integer> resultMap;

    public LongestWords(int howMany) {
        this.howMany = howMany;
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
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(howMany)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));

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
