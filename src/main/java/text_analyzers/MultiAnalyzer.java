package text_analyzers;

import java.util.ArrayList;
import java.util.List;

public class MultiAnalyzer{

    private static ArrayList<Analyzer> analyzers = new ArrayList<>();

    public MultiAnalyzer(ArrayList<Analyzer> analyzers) {
        this.analyzers = analyzers;
    }

    public void performAnalyzis(String text) {
        for (Analyzer analizer : analyzers) {
            analizer.analyze(text);
        }
    }
}
