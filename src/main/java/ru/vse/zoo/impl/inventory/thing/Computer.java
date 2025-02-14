package ru.vse.zoo.impl.inventory.thing;

import ru.vse.zoo.impl.inventory.base.Thing;

public class Computer extends Thing {
    private int ramMemoryGb;
    private String processor;

    public Computer(int number) {
        super(number);
    }

    public int getRamMemoryGb() {
        return ramMemoryGb;
    }

    public void setRamMemoryGb(int ramMemoryGb) {
        this.ramMemoryGb = ramMemoryGb;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "N:" + number + ", Computer: " + getName() +
                "\n\tinventory number: " + number +
                "\n\tram memory: " + ramMemoryGb + "(Gb)" +
                "\n\tprocessor: " + processor;
    }
}
