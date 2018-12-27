package main_and_user_communication;

import picocli.CommandLine;

public class Main {

    public static void main(String[] args) {
        CommandLine.run(new PicoTerm(),args);
    }
}
