package text_analyzers.multiAnalyzer;

public interface Analyzer<T> {

    T analyze(String string);

}
