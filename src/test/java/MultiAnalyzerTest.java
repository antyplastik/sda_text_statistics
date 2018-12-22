import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import text_analyzers.*;
import text_analyzers.MultiAnalyzer;

import java.util.ArrayList;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class MultiAnalyzerTest {

    private String testString = "Is checking for key existence in HashMap always necessary?\n" +
            "\n" +
            "I have a HashMap with say a 1000 entries and I am looking at improving the efficiency. " +
            "If the HashMap is being accessed very frequently, then checking for the key existence at every access will lead to a large overhead. " +
            "Instead if the key is not present and hence an exception occurs, I can catch the exception. (when I know that this will happen rarely). " +
            "This will reduce accesses to the HashMap by half.";

    private ArrayList taskList;
    private MultiAnalyzer multiAnalyzer;

    @Before
    public void TestSetup() {
        taskList = new ArrayList();

        taskList.add(new NumberOfWords());
        taskList.add(new LetterFrequency());
        taskList.add(new LongestWords(1,10));
        taskList.add(new MostPopularWords(10));

        multiAnalyzer = new MultiAnalyzer(taskList);
        multiAnalyzer.performAnalyzis(testString);
    }

    @Test
    public void testCheckTheNumberOfWordsInTheText() {
        NumberOfWords numberOfWords = (NumberOfWords) taskList.get(0);
        int number = numberOfWords.getNumberOfWords();
        assertThat(number, is(greaterThan(0))); // 84
    }

    @Test
    public void testCheckTheFrequencyDistributionOfLettersInTheText() {
        LetterFrequency letterFrequency = (LetterFrequency) taskList.get(1); // poprzednio drugi raz wykonywalem analyze i to powodowalo przeklamanie zsumowanych procentow o ponad 21%!

        double expectedValue = letterFrequency.getLettersFreq()
                .values()
                .stream()
                .mapToDouble(d -> d).sum();

        assertThat(expectedValue, is(closeTo(100, 0.01)));

    }

    @Test
    public void testGet10LongestWordsFromText() {
        LongestWords longestWords = (LongestWords) taskList.get(2);

        int max = longestWords.getWordLengthMap()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getValue();
        int min = longestWords.getWordLengthMap()
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getValue();

        assertThat(max, is(greaterThan(0))); // 10
        assertThat(min, is(greaterThan(0))); // 8
    }

    @Test
    public void testGetThe10MostPopularWordsThatAppearedInTheTextOnlyOnce() {
        MostPopularWords mostPopularWords = (MostPopularWords) taskList.get(3);

        int firstValue = mostPopularWords.getResultMap()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getValue();
        int lastValue = mostPopularWords.getResultMap()
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getValue();

        assertThat(firstValue, is(greaterThan(0))); //
        assertThat(lastValue, is(greaterThan(0))); //
    }
}
