package com.example.loginandcalculator;

import java.util.Random;

public class RandomEqu {

    private int r1;
    private int r2;
    private Random r = new Random();

    private int getR1() {
        return r1;
    }

    private void setR1(int r1) {
        this.r1 = r1;
    }

    private int getR2() {
        return r2;
    }

    private void setR2(int r2) {
        this.r2 = r2;
    }

    public int randomR1() {
        setR1(r.nextInt(100) + 1);
        return getR1();
    }

    public int randomR2() {
        setR2(r.nextInt(100) + 1);
        return getR2();
    }

    public char randomSign() {
        int sign = r.nextInt(4) + 1;
        char s = 0;
        switch (sign){
            case 1:
                s = '+';
                break;
            case 2:
                s = '-';
                break;
            case 3:
                s = '*';
                break;
            case 4:
                s = '/';
                break;
        }
        return s;
    }

    public double printRe(char sign,double a,double b){
        double tmp = 0;
        switch(sign){
            case '+':
                tmp = a + b;
                break;
            case '-':
                tmp = a - b;
                break;
            case '*':
                tmp = a * b;
                break;
            case '/':
                tmp = a / b;
                break;
        }
        return tmp;
    }

}
