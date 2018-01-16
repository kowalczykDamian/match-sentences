package com.english;

public class Element {
    private String standard;
    private String phrasal;

    public Element(String standard, String phrasal) {
        this.standard = standard;
        this.phrasal = phrasal;
    }

    @Override
    public String toString() {
        return "<" + standard + " => " + phrasal + ">";
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getPhrasal() {
        return phrasal;
    }

    public void setPhrasal(String phrasal) {
        this.phrasal = phrasal;
    }
}
