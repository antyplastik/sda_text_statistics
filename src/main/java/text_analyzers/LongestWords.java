package text_analyzers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LongestWords implements Analyzer<HashMap<String, Integer>> {

    private int howMany;
    private HashMap<String, Integer> resultMap;

    public LongestWords(int howMany) {
        this.howMany = howMany;
        resultMap = new HashMap<>();
    }

    @Override
    public HashMap<String, Integer> analyze(String string) {

        HashMap<String, Integer> resultMap = new HashMap<>();

        Stream stringStream = Arrays.stream(string.split(" "))
                .map(String::toLowerCase)
                .map(x->addToHashMap(x));

        ArrayList<Integer> numbers = new ArrayList<>();
                resultMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(numbers.add());




        return resultMap;
    }

    private String addToHashMap(String word) {
        int strLen = word.length();
        if (!resultMap.containsKey(word))
            resultMap.put(word, strLen);
        return word;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
