package text_analyzers;

public interface Analyzer<T> {

    T analyze(String string);

    String getLabel();

}
