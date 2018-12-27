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

    private String letterFreqFilePath = "/home/kamil/Workspace/JAVA_SDA/sda_text_statistics/src/main/resources/letter_freq_in_languages.csv";
    private String apiDictFilePath = "/home/kamil/Workspace/JAVA_SDA/sda_text_statistics/src/main/resources/languages_LanguageDetectionAPI.csv";

    private FileReader languageFile;
    private FileReader apiDictFile;
    private LanguageDetection languageDetection;
    private MultiLanguage multiLanguage = new MultiLanguage();
    private LetterFrequency letterFrequency;

    @Before
    public void setUp() {
        languageFile = new FileReader(letterFreqFilePath);
        apiDictFile = new FileReader(apiDictFilePath);
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
            },
            new Object[]{"Wydarzenia przełomu XX i XXI wieku uświadomiły konieczność podjęcia dyskusji o kształtującej się nowej przestrzeni bezpieczeństwa " +
                    "oraz systemach bezpieczeństwa redukujących ryzyko zagrożeń dla bezpieczeństwa życia i mienia obywateli oraz obiektów tworzących infrastrukturę państwa. " +
                    "Takim szczególnym systemom, od których skuteczności zależy bezpieczeństwo ludzi, organizacji i instytucji, systemów technicznych i innych obiektów o znaczącej wartości " +
                    "poświęcona jest ta książka. Autorzy – reprezentujący różne środowiska i różne specjalności – po przedstawieniu podstaw inżynierii systemów bezpieczeństwa " +
                    "oraz istoty modelowania takich systemów omówili: ryzyko w inżynierii systemów bezpieczeństwa, bezpieczeństwo cybernetyczne, inżynierię systemów bezpieczeństwa informacyjnego, " +
                    "zarządzanie bezpieczeństwem informacji w urzędach administracji publicznej, inżynierię systemów bezpieczeństwa pracy, inżynierię systemów bezpieczeństwa w ruchu drogowym, " +
                    "zarządzanie bezpieczeństwem zdrowotnym, bezpieczeństwo systemów logistycznych, krajowy i międzynarodowy system zarządzania kryzysowego.", "Polish"},
                new Object[]{"Kun Muumipeikko, Pikkumyy\n" +
                        "ja Muumipappa esiintyy.\n" +
                        "Silloin laulu kutsukoon\n" +
                        "tuijottelutuokioon.", "Finnish"},
                new Object[]{
                        "Ó, guð vors lands! Ó, lands vors guð!\n" +
                                "Vér lofum þitt heilaga, heilaga nafn!\n" +
                                "Úr sólkerfum himnanna hnýta þér krans\n" +
                                "þínir herskarar, tímanna safn.", "Icelandic"
                },
                new Object[]{
                        "Kde domov můj, kde domov můj,\n" +
                                "voda hučí po lučinách,\n" +
                                "bory šumí po skalinách,\n" +
                                "v sadě skví se jara květ,\n" +
                                "zemský ráj to na pohled!\n" +
                                "A to je ta krásná země,\n" +
                                "země česká domov můj,\n" +
                                "země česká domov můj!", "Czech"
                }
        };
    }

    @Parameters(method = "testStrings")
    @Test
    public void testLanguageRecognitionBasedOnFileWithLettersFrequencyInLanguages(String text, String expectedLanguage) {
        HashMap <String, Double> letterFreqMap = letterFrequency.analyze(text);
        List <Language> multilanguageList = multiLanguage.getAvailableLanguages();
        String result = languageDetection.offlineAnalyze(letterFrequency,multiLanguage);
        assertThat(result, is(expectedLanguage));
    }

    @Parameters(method = "testStrings")
    @Test
    public void testCompareResultsWithDetectLanguageSite(String text, String expectedLanguage) {
        languageDetection.addToAPIdict(apiDictFile.readFromResources("languages_LanguageDetectionAPI.csv"));

        String result = languageDetection.useAPIdict(languageDetection.onlineAnalyze(text));
        assertThat(result,is(expectedLanguage));
    }
}
