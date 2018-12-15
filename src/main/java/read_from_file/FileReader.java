package read_from_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileReader implements Readable<String> {

    private String pathToFile;
//
//    private static FileReader ourInstance = new FileReader();
//
//    public static FileReader getInstance() {
//        return ourInstance;
//    }

    public FileReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String checkOS() {
        String[] osName = System.getProperty("os.name").split(" ");
        return osName[0];
    }

    @Override
    public String read() {
        String readedFile = "";
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(pathToFile));
            readedFile = new String(fileBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readedFile;
    }

}
