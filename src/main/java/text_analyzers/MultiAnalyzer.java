package text_analyzers;

import java.util.ArrayList;
import java.util.List;

public class MultiAnalyzer{

    private static ArrayList<Analyzer> analyzers = new ArrayList<>();

    public MultiAnalyzer() {
    }

    public MultiAnalyzer(ArrayList<Analyzer> analyzers) {
        this.analyzers = analyzers;
    }

    public void addToAnalyzis(Analyzer analyzer){
        analyzers.add(analyzer);
    }

    public void performAnalyzis(String text) {
        for (Analyzer analizer : analyzers) {
            analizer.analyze(text);
        }
    }

    public Analyzer getAnalyzer(String label){
        for (Analyzer analyzer : analyzers){
            if(analyzer.getLabel().equals(label))
                return analyzer;
        }

        return null;
    }

    public List<String> getAnalyzerLabel(){
        ArrayList <String> result = new ArrayList<>();
        for (Analyzer analyzer : analyzers)
            result.add(analyzer.getLabel());

        return result;
    }

    public List<String> getResultOfAnalyzis(){
        ArrayList <String> result = new ArrayList<>();
        for (Analyzer analyzer : analyzers)
            result.add(analyzer.toString());

        return result;
    }
}
