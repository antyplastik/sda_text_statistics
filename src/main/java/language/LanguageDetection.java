package language;

import read_from_file.FileReader;
import text_analyzers.Analyzer;
import text_analyzers.LetterFrequency;

import java.util.HashMap;
import java.util.Map;

public class LanguageDetection {

    private String detecLanguageUrl = "detectlanguage.com";

    public LanguageDetection() {
    }


    public String offlineAnalyze(LetterFrequency letterFrequency, MultiLanguage availableLanguages) {
//        HashMap<String, Double> stdDeviationsOfLanguages = new HashMap<>();
        HashMap<String, Double> meanSquaredErrForLanguages = new HashMap<>();
        HashMap<String, Double> freqMapFromText = letterFrequency.getLettersFreq();
//        double letterFreqAverage = letterFrequency.getLetterFrequencyAverage();
        double tmpMeanSquaredErr = 0;
        double meanSquaredErrMIN = 0;
        int tmpIndex = 0;
        String result = "";


        for (Language language : availableLanguages.getAvailableLanguages()) {
            HashMap<String, Double> referenceLanguageMap = language.getLetterStat();
            HashMap<String, Double> tempMeanSquareMap = new HashMap<>();
            for (Map.Entry<String, Double> entry : freqMapFromText.entrySet()) {
                String tmpStr = entry.getKey();

                tmpIndex = 0;
                if (referenceLanguageMap.containsKey(tmpStr)) {
                    double lettValFromRef = referenceLanguageMap.get(tmpStr);
                    double lettValFromText = freqMapFromText.get(tmpStr);
//                    tmpMeanSquaredErr = tmpMeanSquaredErr + Math.pow((lettValFromRef - lettValFromText), 2);
                    tempMeanSquareMap.put(entry.getKey(), Math.pow((lettValFromRef - lettValFromText), 2));
//                    tmpIndex++;
                }
            }
            tmpMeanSquaredErr = tempMeanSquareMap.entrySet().stream()
                    .mapToDouble(entry-> entry.getValue())
                    .sum();
            tmpMeanSquaredErr = tmpMeanSquaredErr / tempMeanSquareMap.size();
            meanSquaredErrForLanguages.put(language.getLanguageLabel(), tmpMeanSquaredErr);
            tempMeanSquareMap.clear();
        }

        tmpIndex = 0;
        for (Map.Entry entry : meanSquaredErrForLanguages.entrySet()) {
            double tmp = (double) entry.getValue();
            if (tmp < meanSquaredErrMIN || tmpIndex == 0) {
                meanSquaredErrMIN = tmp;
                result = entry.getKey().toString();
            }
            tmpIndex++;
        }

        return result;
    }

    public String onlineAnalyze(String string) {


        return null;
    }
}
