public class FileReader implements Readable <String> {
    private static FileReader ourInstance = new FileReader();

    public static FileReader getInstance() {
        return ourInstance;
    }

    private FileReader() {
    }

    @Override
    public String read() {
        return null;
    }
}
