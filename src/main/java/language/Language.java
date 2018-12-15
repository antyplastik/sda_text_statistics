package language;

import java.util.HashMap;

public class Language {

    private String languageLabel;
    private HashMap<Character, Double> letterStat;

    public Language(String languageLabel, HashMap<Character, Double> letterStat) {
        this.languageLabel = languageLabel;
        this.letterStat = letterStat;
    }

    public String getLanguageLabel() {
        return languageLabel;
    }

    public HashMap<Character, Double> getLetterStat() {
        return letterStat;
    }
}
