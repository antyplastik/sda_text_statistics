package language;

import text_analyzers.Analyzer;
import text_analyzers.LetterFrequency;

public class LanguageDetection implements Analyzer<String> {

    private String detecLanguageUrl = "detectlanguage.com";

    public String offlineAnalyze(LetterFrequency letterFrequency, MultiLanguage multiLanguage) {

        letterFrequency.getLettersFreq();

        return null;
    }

    public String onlineAnalyze() {

        return null;
    }

    @Override
    public String analyze(String string) {
        return null;
    }
}
