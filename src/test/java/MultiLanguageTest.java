import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import read_from_file.FileReader;

public class MultiLanguageTest {

    private FileReader languageFile;
    private String languageFilePath = "/home/kamil/Workspace/JAVA_SDA/sda_text_statistics/letter_freq_in_languages.csv";
    private String StringFromFile;

    @Before
    public void setUp(){
        languageFile = new FileReader(languageFilePath);
        this.StringFromFile = languageFile.read();
    }

    @Test
    public void checkIfStringReadFromFileIsNotEmpty(){
        assertThat(StringFromFile, is(notNullValue(null)));
    }

}
