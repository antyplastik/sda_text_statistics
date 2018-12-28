package main_and_user_communication;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import read_from_file.FileReader;
import text_analyzers.LetterFrequency;
import text_analyzers.MultiAnalyzer;

import java.io.IOException;

@CommandLine.Command(name = "text_statistics", description = "Language detector\nYou can detect language of input text (pasted text) or file", version = "v1.0")
public class PicoTerm implements Runnable {
    @Parameters(arity = "1..*", paramLabel = "TEXT/FILE", description = "Input text(s) or path to file(s) to process. Default is text input")
    private String[] inputParametersArgs;

    @Option(names = {"f", "--file"}, description = "Input is a file (or files)")
    private boolean inputIsAFileFlag = false;

    @Option(names = {"-o", "--online"}, description = "Online  language detection (default offline)")
    private boolean onlineDetectionFlag = false;

    @Option(names = {"-n", "--number-of-words"}, arity = "1..*", description = "Online  language detection (default offline)")
    private boolean numberOfWordsFlag = false;

    @Option(names = {"-l", "--letter-frequency"}, description = "Prints frequency of each letter and number")
    private boolean letterFrequencyFlag = false;

    @Option(names = {"-g", "--longest-words"}, arity = "1..*", description = "Prints n of longest words from text")
    private boolean longestWordsFlag = false;

    @Option(names = {"-w", "--most-popular-words"}, arity = "1..*", description = "Prints n of most popular words from text")
    private boolean mostPopularWordsFlag = false;

    @Option(names = {"--languages"}, description = "Print available languages for offline language detection")
    private boolean printAvailableLang = false;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean helpRequestedFlag = false;


    @Override
    public void run() {
        FileReader fileReader;
        MultiAnalyzer textAnalyzerList;
        LetterFrequency letterFrequencyAnalize;
        String input = "";
        String result = "";

        if (inputParametersArgs.length != 0) {
            input = inputParametersArgs[0];

            if (inputIsAFileFlag) {
                fileReader = new FileReader(input);
                try {
                    input = fileReader.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (numberOfWordsFlag) {

            }

            if(longestWordsFlag){

            }

            if(mostPopularWordsFlag){

            }

            if (onlineDetectionFlag) {

            }

            if (printAvailableLang) {

                printAvailableLang = false;
            }

        } else {

        }

    }
}
