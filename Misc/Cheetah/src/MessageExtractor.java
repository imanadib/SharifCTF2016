package src;

import java.io.IOException;

public interface MessageExtractor {
    String extract(String path) throws IOException;
}

