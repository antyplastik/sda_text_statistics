import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import text_analyzers.*;
import text_analyzers.MultiAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        taskList.add(new LongestWords(10));
        taskList.add(new MostPopularWords(1));

        multiAnalyzer = new MultiAnalyzer(taskList);
        multiAnalyzer.performAnalyzis(testString);
    }

    @Test
    public void testCheckTheNumberOfWordsInTheText() {
        NumberOfWords numberOfWords = (NumberOfWords) taskList.get(0);
        int number = numberOfWords.getNumberOfWords();
        assertThat(number, is(84));
    }

    @Ignore
    @Test
    public void testCheckTheFrequencyDistributionOfLettersInTheText() {
        LetterFrequency letterFrequency = (LetterFrequency) taskList.get(1);


    }

    @Test
    public void testGet10LongestWordsFromText() {
        HashMap<String, Integer> expected = new HashMap<>();

        LongestWords longestWords = (LongestWords) taskList.get(2);
        HashMap<String, Integer> resultList = longestWords.analyze(testString);
        int max = resultList.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        int min = resultList.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();

        assertThat(max, is(10));
        assertThat(min,is(8));
    }

    @Ignore
    @Test
    public void testGetThe10MostPopularWordsThatAppearedInTheTextOnlyOnce() {
        taskList.get(3);
    }
}
