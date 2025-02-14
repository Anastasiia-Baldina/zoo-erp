package ru.vse.zoo;

import java.util.List;

public interface UI {
    void print(String txt);

    void printLine(String txt);

    boolean readYesOrNo(String prompt);

    int readInt(String prompt);

    String readString(String prompt);

    int selectOption(List<String> options);
}
