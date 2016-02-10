package src;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Extractor extends ExtractorBase {

    public Extractor() {
        super(0, 0);
    }

    @Override
    public String extract(String path) throws IOException {
        this.image = ImageIO.read(new File(path));
        int length = getMessageLength();
        byte[] messageBytes = new byte[length];
        for (int i = 0; i < length; i++) {
            messageBytes[i] = decodeNextByte();
        }
        String message = new String(messageBytes, StandardCharsets.US_ASCII);
        return message;
    }

}
