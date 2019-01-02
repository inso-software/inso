package com.inso.example.Hybrid;

/**
 * Comment:
 * Author: ftc300
 * Date: 2019/1/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

public class PassData {
    public int a;
    public String b;

    public PassData(int arg_a, String arg_b) {
        a = arg_a;
        b = arg_b;
    }

    @Override
    public String toString() {
        return "PassData{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}
