package otus.dz.io;

import java.io.InputStream;

public interface IOService {

    String readString(InputStream in);

    void printString(String s);
}
