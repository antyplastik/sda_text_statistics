import language.Language;
import language.MultiLanguage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import read_from_file.FileReader;

import java.util.List;

public class MultiLanguageTest {

    private FileReader languageFile;
    private String linuxFilePath = "/home/kamil/Workspace/JAVA_SDA/sda_text_statistics/letter_freq_in_languages.csv";
    private String windowsFilePath = "\\home\\kamil\\Workspace\\JAVA_SDA\\sda_text_statistics\\letter_freq_in_languages.csv";
    private String StringFromFile;

    @Before
    public void setUp(){
        languageFile = new FileReader(linuxFilePath);
        StringFromFile = languageFile.read();
    }

    @Test
    public void checkTheInterpretationOfTheFilePathDependingOnTheSystem(){ // test only for unix
        languageFile = new FileReader(windowsFilePath);
        assertThat(languageFile.getPathToFile(), is(linuxFilePath));
    }

    @Test
    public void checkIfStringReadFromFileIsNotEmpty(){
        assertThat(StringFromFile, is(notNullValue(null)));
    }

    @Test
    public void generatingMapsContainingTheFrequencyOfLettersInAGivenLanguage(){
        MultiLanguage multiLanguageList = new MultiLanguage();
        multiLanguageList.setLanguageListFromFile(languageFile.read());

        List <Language> languages = multiLanguageList.getAvailableLanguages();

        Language testLang = new Language("Ciapacki");

        for (Language lang : languages)
            if(lang.getLanguageLabel().equals("Polish"))
                testLang = lang;

        assertThat(testLang.getLetterStat(),is(notNullValue()));
    }

}
