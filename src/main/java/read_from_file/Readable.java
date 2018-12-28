package read_from_file;

import java.io.IOException;

public interface Readable<T> {
    T read() throws IOException;
}
