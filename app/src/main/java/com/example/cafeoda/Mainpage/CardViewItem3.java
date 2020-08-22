package com.example.cafeoda.Mainpage;

public class CardViewItem3 {
    int img;
    String txt;
    String txt2;
    String txt3;

    public CardViewItem3(int img, String txt, String txt2, String txt3) {
        this.img = img;
        this.txt = txt;
        this.txt2 = txt2;
        this.txt3 = txt3;
    }

    public int getImg() {
        return img;
    }

    public String getTxt() {
        return txt;
    }

    public String getTxt2() {
        return txt2;
    }

    public String getTxt3() {
        return txt3;
    }


    @Override
    public String toString() {
        return "CardViewItem3{" +
                "img=" + img +
                ", txt='" + txt + '\'' +
                ", txt2='" + txt2 + '\'' +
                ", txt3='" + txt3 + '\'' +
                '}';
    }
}
