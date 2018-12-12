package text_analyzers;

import text_analyzers.multiAnalyzer.Analyzer;

import java.util.HashMap;

public class MostPopularWords implements Analyzer<HashMap<String, Integer>> {

    private int howMany;

    public MostPopularWords(int howMany) {
        this.howMany = howMany;
    }

    @Override
    public HashMap<String, Integer> analyze(String string) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
