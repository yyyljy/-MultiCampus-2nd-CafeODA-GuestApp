package com.example.cafeoda.Mainpage;

public class CardViewItem2 {
    int img;
    String txt;
    String txt2;

    public CardViewItem2(int img, String txt, String txt2) {
        this.img = img;
        this.txt = txt;
        this.txt2 = txt2;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    @Override
    public String toString() {
        return "CardViewItem2{" +
                "img=" + img +
                ", txt='" + txt + '\'' +
                ", txt2='" + txt2 + '\'' +
                '}';
    }
}
