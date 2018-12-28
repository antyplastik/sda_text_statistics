package main_and_user_communication;

import language.MultiLanguageOfflineList;
import picocli.CommandLine;
import read_from_file.FileReader;

import java.util.stream.Collectors;

public class Main {

//    public static FileReader langRefs;
//    public static MultiLanguageOfflineList availableLanguages;

    public static void main(String[] args) {

//        langRefs = new FileReader();
//        availableLanguages = new MultiLanguageOfflineList();
//        availableLanguages.setLanguageListFromFile(langRefs.readFromResources("letter_freq_in_languages.csv"));
//
//        System.out.format("Available languages for offline detection:\n%s", availableLanguages.getAvailableLanguages().stream()
//                .map(e -> e.getLanguageLabel() + "\n")
//                .collect(Collectors.joining()));

        CommandLine.run(new PicoTerm(),args);
    }
}
