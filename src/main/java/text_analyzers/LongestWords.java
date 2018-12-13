package text_analyzers;

import java.util.HashMap;

public class LongestWords implements Analyzer<HashMap<String, Integer>> {

    private int howMany;

    public LongestWords(int howMany) {
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
