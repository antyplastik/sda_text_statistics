package main_and_user_communication;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@CommandLine.Command(name = "Language Detector", description = "You can detect language of input text (pasted text) or file", version = "v1.0")
public class PicoTerm implements Runnable {

    @Parameters(arity = "1..*", paramLabel = "TEXT/FILE", description = "Input text(s) or path to file(s) to process.")
    private String[] inputParameters;

    @Option(names = {"-o", "--online"}, description = "Online  language detection (default offline)")
    private boolean onlineDetection = false;

    @Override
    public void run() {
        System.out.println("hello");
    }
}
