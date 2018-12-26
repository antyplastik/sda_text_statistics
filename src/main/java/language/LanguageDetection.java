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


    private String offlineAnalyze(LetterFrequency letterFrequency, MultiLanguage availableLanguages) {
        HashMap<String, Double> stdDeviationsOfLanguages = new HashMap<>();
        HashMap<String, Double> letterFreqMap = letterFrequency.getLettersFreq();
        double letterFreqAverage = letterFrequency.getLetterFrequencyAverage();
        double languageStdDev = 0;


        for (Language language : availableLanguages.getAvailableLanguages()) {
            HashMap<String, Double> languageMap = language.getLetterStat();

            for (Map.Entry<String, Double> entry : letterFreqMap.entrySet()) {
                String tmpStr = entry.getKey();
                if (languageMap.containsKey(tmpStr)) {
                    languageStdDev = languageStdDev + languageMap.get(tmpStr);
                }
            }

            stdDeviationsOfLanguages.put(language.getLanguageLabel(),languageStdDev);
        }

        return null;
    }

    private String onlineAnalyze(String string) {


        return null;
    }
}
