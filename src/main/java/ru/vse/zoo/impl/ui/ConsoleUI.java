package ru.vse.zoo.impl.ui;

import ru.vse.zoo.UI;
import ru.vse.zoo.exception.IllegalFormatException;
import ru.vse.zoo.util.Ensure;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements UI {
    private final PrintStream writer;
    private final Scanner reader;

    public ConsoleUI() {
        writer = System.out;
        reader = new Scanner(System.in);
    }

    @Override
    public void print(String txt) {
        writer.print(txt);
    }

    @Override
    public void printLine(String txt) {
        writer.println(txt);
    }

    @Override
    public boolean readYesOrNo(String prompt) {
        print(prompt + "? (y/n): ");
        var res = reader.nextLine();
        if ("y".equals(res)) {
            return true;
        } else if ("n".equals(res)) {
            return false;
        } else {
            throw new IllegalFormatException("Unexpected answer '" + res + "'. Expected 'y' or 'n'...");
        }
    }

    @Override
    public int readInt(String prompt) {
        var line = readString(prompt);
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalFormatException("Expected integer but was '" + line + "'");
        }
    }

    @Override
    public String readString(String prompt) {
        print(prompt + ": ");
        return reader.nextLine();
    }

    @Override
    public int selectOption(List<String> options) {
        Ensure.notEmpty(options, "options");
        printLine("Select option:");
        for (int i = 0; i < options.size(); i++) {
            printLine((i + 1) + " " + options.get(i));
        }
        return readInt("[1 ... " + options.size() + "]");
    }
}
