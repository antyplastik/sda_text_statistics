package main_and_user_communication;

import language.Language;
import language.LanguageDetection;
import language.MultiLanguageOfflineList;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import read_from_file.FileReader;
import text_analyzers.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@CommandLine.Command(name = "text_statistics", description = "Language detector\nYou can detect language of input text (pasted text) or file", version = "v1.0")
public class PicoTerm implements Runnable {
    @Parameters(arity = "1..*", paramLabel = "TEXT/FILE", description = "Input text(s) or path to file(s) to process. Default is text input")
    private String inputParametersArgs;

    @Option(names = {"-t", "--text"}, description = "Input is a text (or texts)")
    private boolean inputIsATextFlag;

    @Option(names = {"-f", "--file"}, description = "Input is a file (or files)")
    private boolean inputIsAFileFlag;

    @Option(names = {"-o", "--online"}, description = "Online  language detection (default offline)")
    private boolean onlineDetectionFlag;

    @Option(names = {"-n", "--number-of-words"}, description = "Online  language detection (default offline)")
    private boolean numberOfWordsFlag;

    @Option(names = {"-l", "--letter-frequency"}, description = "Prints frequency of each letter and number")
    private boolean letterFrequencyFlag;

    @Option(names = {"-g", "--longest-words"}, description = "Prints n of longest words from text")
    private boolean longestWordsFlag;

    @Option(names = {"-w", "--most-popular-words"}, description = "Prints n of most popular words from text")
    private boolean mostPopularWordsFlag;

    @Option(names = {"--languages"}, description = "Print available languages for offline language detection")
    private boolean printAvailableLang;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean helpRequestedFlag;


    FileReader fileReader;
    MultiAnalyzer textAnalyzerList = new MultiAnalyzer();
    LetterFrequency letterFrequencyAnalize;
    LanguageDetection languageDetection = new LanguageDetection();
    String input = "";
    String result = "";
    String availableLanguagesStr = "";
    String languageResult = "";

    @Override
    public void run() {

        if (!inputParametersArgs.equals(null)) {
            input = inputParametersArgs;

            if (inputIsAFileFlag) {
                fileReader = new FileReader(input);
                try {
                    input = fileReader.read();
                } catch (IOException e) {
                    System.out.println("[ERR] Wrong path to the file or file name");
                }
            }

            if (inputIsATextFlag) {

            }


            textAnalyzerList.addToAnalyzis(new LetterFrequency());

            if (numberOfWordsFlag) {
                textAnalyzerList.addToAnalyzis(new NumberOfWords());
            }

            if (longestWordsFlag) {
                textAnalyzerList.addToAnalyzis(new LongestWords(1, 10));
            }

            if (mostPopularWordsFlag) {
                textAnalyzerList.addToAnalyzis(new MostPopularWords(10));
            }

            if (printAvailableLang) {

                printAvailableLang = false;
            }

        } else {

        }

        textAnalyzerList.performAnalyzis(input);

        if (onlineDetectionFlag) {
            languageResult = "Online detection result: " + languageDetection.onlineAnalyze(input) + "\n";
        } else {
            LetterFrequency lF;
            FileReader fR = new FileReader();
            MultiLanguageOfflineList mLoL = new MultiLanguageOfflineList();

            try {
                mLoL.setLanguageListFromFile(fR.readFromResources("letter_freq_in_languages.csv"));
            } catch (URISyntaxException e) {
                System.out.println("[ERR] Internet connection err");
            } catch (IOException e) {
                System.out.println("[ERR] Wrong path to the file or file name");
            }
            lF = (LetterFrequency) textAnalyzerList.getAnalyzer("Letter and Numbers Frequency Analyzer");
            languageResult = "Offline detection result: " + languageDetection.offlineAnalyze(lF, mLoL);
        }

        System.out.println("Results:" + "\n" + textAnalyzerList.getResultOfAnalyzis().stream()
                .map(s -> s + '\n')
                .collect(Collectors.joining()) + languageResult);

    }
}
