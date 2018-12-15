package main_and_user_communication;

import read_from_file.FileReader;

public class Main {

    private static FileReader languageFile;
    private static String languageFilePath = "/home/kamil/Workspace/JAVA_SDA/sda_text_statistics/letter_freq_in_languages.csv";

    public static void main(String[] args) {
        languageFile = new FileReader(languageFilePath);
        String readedFromFile = languageFile.read();
        System.out.println(languageFile.checkOS());
//        System.out.print(readedFromFile);
    }
}
