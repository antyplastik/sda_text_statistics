package language;

import java.util.*;
import java.util.stream.Collectors;

public class MultiLanguage {

    private static List<Language> availableLanguages;
    private final static String csvLineSeparator = ",";

    public MultiLanguage() {
        availableLanguages = new ArrayList<>();
    }

    public void setLanguageListFromFile(String fileString) {
        List<String> line = Arrays.stream(fileString.split("\n"))
                .collect(Collectors.toList());

        List<String> titleLine = Arrays.stream(line.get(0).split(csvLineSeparator)).collect(Collectors.toList());
        titleLine.remove(0);
        line.set(0, titleLine.stream().collect(Collectors.joining()));
        line.remove(0);

        availableLanguages = titleLine.stream()
                .map(s -> new Language(s))
                .collect(Collectors.toList());

        for (String lineStr : line) {
            List<String> lineStrList = Arrays
                    .stream(lineStr.replaceAll("[$&+:;=?@#|'<>^*()%~!]", "").split(csvLineSeparator))
                    .collect(Collectors.toList());
            int i = 1;
            for (String languageStr : titleLine) {
//                getLanguage(languageStr).addToLetterStatMap(lineStrList.get(0), Double.parseDouble(lineStrList.get(i)));
                addSignStatsToLangObj(languageStr, lineStrList.get(0), Double.parseDouble(lineStrList.get(i)));
                i++;
            }
        }
    }

    private void addSignStatsToLangObj(String label, String key, Double value) {
//        String label = lang.getLanguageLabel();
        int index = 0;
        for (Language language : availableLanguages) {
            if (language.getLanguageLabel().equals(label)) {
                language.addToLetterStatMap(key, value);
                this.availableLanguages.set(index, language);
                break;
            } else
                index++;
        }
    }

    public Language getLanguage(String lang) {
        for (Language language : availableLanguages)
            if (language.getLanguageLabel().equals(lang))
                return language;
        return null;
    }

    public List<Language> getAvailableLanguages() {
        return availableLanguages;
    }
}
