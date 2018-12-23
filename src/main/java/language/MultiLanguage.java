package language;

import java.util.*;
import java.util.stream.Collectors;

public class MultiLanguage {

    private static List<Language> availabeLanguages;
    private final static String csvLineSeparator = ",";

    public MultiLanguage() {
        availabeLanguages = new ArrayList<>();
    }

    public void setLanguageListFromFile(String fileString) {
//        fileString = fileString.replaceAll("%", "");

        List<String> line = Arrays.stream(fileString.replace("%", "")
                .split("\n"))
                .collect(Collectors.toList());

        List<String> titleLine = Arrays.stream(line.get(0).split(csvLineSeparator)).collect(Collectors.toList());
        titleLine.remove(0);
        line.set(0, titleLine.stream().collect(Collectors.joining()));
        line.remove(0);

        availabeLanguages = titleLine.stream()
                .map(s -> new Language(s))
                .collect(Collectors.toList());

        for (String lineStr : line) {
            List<String> lineStrList = new ArrayList<String>();
            lineStrList = Arrays.stream(lineStr.split(csvLineSeparator)).collect(Collectors.toList());
            int i = 1;
            for (String languageStr : titleLine) {
                Language language = getLanguage(languageStr);
                language.addToLetterStatMap(lineStrList.get(0), Double.parseDouble(lineStrList.get(i)));
                i++;
            }
        }
        String stop = "";
    }

    private void addSignStatisticsToLanguageObject(){
        for(Language language : availabeLanguages)
            ;

    }

    public Language getLanguage(String lang) {
        for (Language language : availabeLanguages)
            if (language.getLanguageLabel().equals(lang))
                return language;

        return null;
    }
}
