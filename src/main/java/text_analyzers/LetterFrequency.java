package text_analyzers;

import one.util.streamex.DoubleStreamEx;
import org.apache.commons.math3.util.Precision;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LetterFrequency implements Analyzer<HashMap<Character, Double>> {

    private HashMap<Character, Double> lettersFreq;
    private int sumOfSigns;

    public LetterFrequency() {
        lettersFreq = new HashMap<>();
    }

    @Override
    public HashMap<Character, Double> analyze(String string) {

        this.sumOfSigns = string.length();
        IntStream stringStream = string.chars();

        stringStream
                .map(x -> !(Character.isLetter(x) || (Character.isWhitespace(x) && x != '\n' && x != ' ')) ? x = ' ' : x)
                .map(Character::toUpperCase)
                .forEach(c -> addToHashMap((char) c));

        lettersFreq = getSignsFreqPercent(lettersFreq, 4);

        return lettersFreq;
    }

    private void addToHashMap(Character character) {
        if (lettersFreq.containsKey(character))
            lettersFreq.put(character, lettersFreq.get(character) + 1);
        else
            lettersFreq.put(character, (double) 1);
    }

    public HashMap<Character, Double> getLettersFreq() {
        return lettersFreq;
    }

    private HashMap<Character, Double> getSignsFreqPercent(HashMap<Character, Double> hashMap, int precision) {
        HashMap<Character, Double> resultMap = new HashMap<>();

        for (Map.Entry<Character, Double> entry : hashMap.entrySet())
            resultMap.put(entry.getKey(), (Precision.round(((entry.getValue() * 100) / sumOfSigns), precision)));

        return resultMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
