package com.example.cafeoda.ShoppingList;

public class MyShoppingItem {

    int cafeimg;
    String coffeename;
    String icehot;
    int amount;

    public MyShoppingItem(int cafeimg, String coffeename, String icehot, int amount) {
        this.cafeimg = cafeimg;
        this.coffeename = coffeename;
        this.icehot = icehot;
        this.amount = amount;
    }

    public int getcafeimg() {
        return cafeimg;
    }

    public String getCoffeename() {
        return coffeename;
    }

    public String getIcehot() {
        return icehot;
    }

    public int getAmount() {
        return amount;
    }
}

