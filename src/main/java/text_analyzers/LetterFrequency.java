package text_analyzers;

import one.util.streamex.DoubleStreamEx;
import org.apache.commons.math3.util.Precision;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LetterFrequency implements Analyzer<HashMap<String, Double>> {

    private HashMap<String, Double> lettersFreq;
    private int sumOfSigns;

    public LetterFrequency() {
        lettersFreq = new HashMap<>();
    }

    @Override
    public HashMap<String, Double> analyze(String string) {

        this.sumOfSigns = string.length();
        IntStream stringStream = string.chars();

        stringStream
                .map(x -> !(Character.isLetter(x) || (Character.isWhitespace(x) && x != '\n' && x != ' ')) ? x = ' ' : x)
                .map(Character::toUpperCase)
                .forEach(c -> addToHashMap(String.valueOf(c)));

        lettersFreq = getSignsFreqPercent(lettersFreq, 4);

        return lettersFreq;
    }

    private void addToHashMap(String character) {
        if (lettersFreq.containsKey(character))
            lettersFreq.put(character, lettersFreq.get(character) + 1);
        else
            lettersFreq.put(character, (double) 1);
    }

    public HashMap<String, Double> getLettersFreq() {
        return lettersFreq;
    }

    private HashMap<String, Double> getSignsFreqPercent(HashMap<String, Double> hashMap, int precision) {
        HashMap<String, Double> resultMap = new HashMap<>();

        for (Map.Entry<String, Double> entry : hashMap.entrySet())
            resultMap.put(entry.getKey(), (Precision.round(((entry.getValue() * 100) / sumOfSigns), precision)));

        return resultMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
