package text_analyzers;

import java.util.HashMap;

public class MostPopularWords implements Analyzer<HashMap<String, Integer>> {

    private int howManyAppeared;

    public MostPopularWords(int howManyAppeared) {
        this.howManyAppeared = howManyAppeared;
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
