package com.adv2022.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Monkey {
    private final String name;
    private List<Long> items = new ArrayList<>();
    private int divisor;
    private String ifTrueMonkey;
    private String ifFalseMonkey;
    private int operand;
    private String operator;
    private long throwNum;

    public Monkey(String name) {
        this.name = name;
        this.throwNum = 0L;
    }

    public long getThrowNum() {
        return throwNum;
    }

    public String getName() {
        return name;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public String getIfTrueMonkey() {
        return ifTrueMonkey;
    }

    public void setIfTrueMonkey(String ifTrueMonkey) {
        this.ifTrueMonkey = ifTrueMonkey;
    }

    public String getIfFalseMonkey() {
        return ifFalseMonkey;
    }

    public void setIfFalseMonkey(String ifFalseMonkey) {
        this.ifFalseMonkey = ifFalseMonkey;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public long getNextItem() {
        ListIterator<Long> it = this.items.listIterator();
        if (it.hasNext()) {
            long ret = it.next();
            it.remove();
            return ret;
        }
        return 0;
    }

    public void incrThrowNumber() {
        this.throwNum++;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }

    public void addNewItem(long item) {
        this.items.add(item);
    }
}