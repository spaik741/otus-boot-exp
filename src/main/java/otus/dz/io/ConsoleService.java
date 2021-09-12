package otus.dz.io;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class ConsoleService implements IOService {

    public String readString(InputStream in) {
        Scanner sc = new Scanner(in);
        return sc.nextLine();
    }

    public void printString(String s) {
        System.out.println(s);
    }
}
