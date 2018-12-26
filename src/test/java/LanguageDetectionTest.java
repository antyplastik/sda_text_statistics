import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import language.Language;
import language.LanguageDetection;
import language.MultiLanguage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.runner.RunWith;
import read_from_file.FileReader;
import text_analyzers.LetterFrequency;

import java.util.HashMap;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class LanguageDetectionTest {

    private String linuxFilePath = "/home/kamil/Workspace/JAVA_SDA/sda_text_statistics/letter_freq_in_languages.csv";

    private FileReader languageFile;
    private LanguageDetection languageDetection;
    private MultiLanguage multiLanguage = new MultiLanguage();
    private LetterFrequency letterFrequency;

    @Before
    public void setUp() {
        languageFile = new FileReader(linuxFilePath);
        multiLanguage.setLanguageListFromFile(languageFile.read());
        letterFrequency = new LetterFrequency();

        languageDetection = new LanguageDetection();
    }

    public Object[] testStrings() {
        return new Object[]{
            new Object[]{"Is checking for key existence in HashMap always necessary?\n" +
                    "\n" +
                    "I have a HashMap with say a 1000 entries and I am looking at improving the efficiency. " +
                    "If the HashMap is being accessed very frequently, then checking for the key existence at every access will lead to a large overhead. " +
                    "Instead if the key is not present and hence an exception occurs, I can catch the exception. (when I know that this will happen rarely). " +
                    "This will reduce accesses to the HashMap by half.", "English"
            }
        };
    }

    @Parameters(method = "testStrings")
    @Test
    public void testLanguageRecognitionBasedOnFileWithLettersFrequencyInLanguages(String text, String expectedLanguage) {
        HashMap <String, Double> letterFreqMap = letterFrequency.analyze(text);
        List <Language> multilanguageList = multiLanguage.getAvailableLanguages();
        String result = languageDetection.analyze(text);
        assertThat(result, is(expectedLanguage));
    }

    @Test
    public void testCompareResultsWithDetectLanguageSite() {

    }
}
