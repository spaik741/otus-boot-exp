package otus.dz.treatment;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.Scanner;


public class ConsoleService {

    public static String readString(InputStream in) {
        Scanner sc = new Scanner(in);
        return sc.nextLine();
    }

    public static void printString(String s) {
        System.out.println(s);
    }
}
