package text_analyzers.multiAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiAnalyzer implements Analyzer<String> {

    private static ArrayList<Analyzer> analyzers = new ArrayList<>();

    public MultiAnalyzer() {
    }

    MultiAnalyzer(ArrayList<Analyzer> analyzers) {
        this.analyzers = analyzers;
    }

    public void performAnalyzis(String text) {
        for (Analyzer analizer : analyzers) {
            analizer.analyze(text);
        }
    }

    @Override
    public String analyze(String string) {
        return null;
    }


//    public int getNumberOfWordsFromText(String input) {
//        int numberOfWords = 0;
//
//        return numberOfWords;
//    }

//    public HashMap<String, Integer> getTheMostPopularWords(String input, int howMany) {
//
//        return null;
//    }

//    public HashMap<Character, Integer> getLetterFrequency(String input) {
//        return null;
//    }

//    public HashMap<String, Integer> getTheLongestWords(String input, int numberOfRepetitions){
//
//        return null;
//    }

//    public HashMap<Character, Integer> getWhiteSpaces(String input){
//
//        return null;
//    }

//    public String convertHashMapToString(HashMap hashMap){
//
//        return null;
//    }
}
