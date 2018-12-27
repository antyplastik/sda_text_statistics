package read_from_file;

public interface Readable<T> {
    T read();
    T readFromResources(T filePath);
}
