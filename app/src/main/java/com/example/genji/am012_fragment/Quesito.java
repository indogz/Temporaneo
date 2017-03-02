package com.example.genji.am012_fragment;

/**
 * Created by matteo on 27/02/17.
 */

public class Quesito {

    protected String q;
    protected String a1;
    protected String a2;
    protected String a3;
    protected String a4;
    int  correctA;

    public Quesito() {
        q = a1 = a2 = a3 = a4 = null;

    }

    public Quesito(String q, String a1, String a2, String a3, String a4, int correctA) {
        this.q = q;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correctA = correctA;
    }

    public String getDomanda() {
        return q;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }

    public int getCorrectA() { return correctA;}

}
