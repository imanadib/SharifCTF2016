package src;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        MessageExtractor extractor = new Extractor();
        String msg = extractor.extract("AsianCheetah1.png");
        System.out.print("[!]Flag: " + msg);

    }
}
