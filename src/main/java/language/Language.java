package language;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private String languageLabel;
    private Map<String, Double> letterStat;

    public Language(String languageLabel) {
        this.languageLabel = languageLabel;
        this.letterStat = new HashMap<String, Double>();
    }

    public void addToLetterStatMap(String key, Double value){
        if(!key.equals(null) || !key.equals(""))
            letterStat.put(key,value);
    }

    public String getLanguageLabel() {
        return languageLabel;
    }

    public void optimalize(){

    }

    public Map<String, Double> getLetterStat() {
        return letterStat;
    }
}
