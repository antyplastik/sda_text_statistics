package language;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private String languageLabel;
    private HashMap<String, Double> letterStat;

    public Language(String languageLabel) {
        this.languageLabel = languageLabel;
        this.letterStat = new HashMap<>();
    }

    public void addToLetterStatMap(String key, Double value) {
        if (key == null || !key.equals(""))
            letterStat.put(key, value);
    }

    public String getLanguageLabel() {
        return languageLabel;
    }

    public void optimalize() {
        HashMap<String, Double> optimalizedMap = new HashMap<>();
        for (Map.Entry<String, Double> entry : letterStat.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if (value > 0)
                optimalizedMap.put(key, value);
        }
        letterStat = optimalizedMap;
    }

    public HashMap<String, Double> getLetterStat() {
        return letterStat;
    }
}
