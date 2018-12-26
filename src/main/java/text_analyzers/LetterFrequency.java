package text_analyzers;

import one.util.streamex.DoubleStreamEx;
import org.apache.commons.math3.util.Precision;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LetterFrequency implements Analyzer<HashMap<String, Double>> {

    private HashMap<String, Double> lettersFreq;
    double letterFrequencyAverage;
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
                .map(Character::toLowerCase)
                .forEach(c -> addToHashMap((char) c));

        lettersFreq = getSignsFreqPercent(lettersFreq, 4);
        letterFrequencyAverage = calcAverage(lettersFreq);

        return lettersFreq;
    }

    private void addToHashMap(Character letter) {
        if (lettersFreq.containsKey((letter + "")))
            lettersFreq.put((letter + ""), lettersFreq.get((letter + "")) + 1);
        else
            lettersFreq.put((letter + ""), (double) 1);
    }

    private HashMap<String, Double> getSignsFreqPercent(HashMap<String, Double> hashMap, int precision) {
        HashMap<String, Double> resultMap = new HashMap<>();

        for (Map.Entry<String, Double> entry : hashMap.entrySet())
            resultMap.put(entry.getKey(), (Precision.round(((entry.getValue() * 100) / sumOfSigns), precision)));

        return resultMap;
    }

    private Double calcAverage(HashMap <String, Double> letterFrequency) {
        double tmp = 0;

        for (Map.Entry<String, Double> entry : letterFrequency.entrySet()){
            if (!entry.getKey().equals("")){
                tmp = tmp + entry.getValue();
            }
        }
            return tmp / letterFrequency.size();
    }

    public HashMap<String, Double> getLettersFreq() {
        return lettersFreq;
    }

    public double getLetterFrequencyAverage() {
        return letterFrequencyAverage;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
