package com.example.cafeoda.Mainpage;

public class CardViewItem {
     int img;
     String txt;

    public CardViewItem(int img, String txt) {
        this.img = img;
        this.txt = txt;
    }

    public int getImg() {
        return img;
    }

    public String getTxt() {
        return txt;
    }

    @Override
    public String toString() {
        return "CardViewItem{" +
                "img=" + img +
                ", txt='" + txt + '\'' +
                '}';
    }
}