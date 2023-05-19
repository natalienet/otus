package ru.nn.service;

import ru.nn.api.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOStreamsService implements IOService {

    private final PrintStream out;
    private final Scanner in;

    public IOStreamsService(PrintStream out, InputStream in) {
        this.out = out;
        this.in = new Scanner(in);
    }

    @Override
    public void outputString(String s) {
        out.println(s);
    }

    @Override
    public String readString() {
        return in.nextLine();
    }
}
