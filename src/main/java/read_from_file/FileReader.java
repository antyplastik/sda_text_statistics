package read_from_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileReader implements Readable<String> {

    private String pathToFile;
    private String detectedOS;
//
//    private static FileReader ourInstance = new FileReader();
//
//    public static FileReader getInstance() {
//        return ourInstance;
//    }

    public FileReader(String pathToFile) {
        if (checkOS() == "windows")
            this.pathToFile = pathToFile.replaceAll("/", "\\");
        else
            this.pathToFile = pathToFile.replaceAll("\\\\", "/");
    }

    private String checkOS() {
        String[] osName = System.getProperty("os.name").split(" ");
        return detectedOS = osName[0].toLowerCase();
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

    public String getPathToFile() {
        return pathToFile;
    }

    public String getDetectedOS() {
        return detectedOS;
    }
}
