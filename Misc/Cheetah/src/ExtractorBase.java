package src;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class ExtractorBase implements MessageExtractor {
    protected int x;
    protected int y;
    protected BufferedImage image;

    public ExtractorBase(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = null;
    }

    protected byte decodeNextByte() {
        byte decodedByte = 0x00;
        for (int i = 0; i < 8; i++) {
            int pixel = image.getRGB(x, y);
            if ((pixel & 0x00000001) == 1) {
                decodedByte = (byte) (decodedByte | (byte) (0x1 << 7 - i));
            }
            if (x == image.getWidth() - 1) {
                x = 0;
                y += 1;
            } else {
                x += 1;
            }
        }
        return decodedByte;
    }

    protected int getMessageLength() {
        ArrayList<Byte> decodedBytes = new ArrayList<Byte>();
        while (true) {
            byte decodedByte = decodeNextByte();
            if (decodedByte == 0x3a) {
                break;
            }
            decodedBytes.add(decodedByte);
        }
        String lengthString = new String(toArray(decodedBytes), StandardCharsets.US_ASCII);
        int length = Integer.parseInt(lengthString);
        return length;
    }

    private byte[] toArray(ArrayList<Byte> list) {
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

}