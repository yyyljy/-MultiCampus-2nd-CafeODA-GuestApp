package com.example.cafeoda.WishList;

import android.os.Parcel;
import android.os.Parcelable;

public class Jangbaguny implements Parcelable {
    String menuname;
    String iceable;
    String size;
    int quantity;
    int price;


    protected Jangbaguny(Parcel in) {
        menuname = in.readString();
        iceable = in.readString();
        size = in.readString();
        quantity = in.readInt();
        price = in.readInt();
    }

    public static final Creator<Jangbaguny> CREATOR = new Creator<Jangbaguny>() {
        @Override
        public Jangbaguny createFromParcel(Parcel in) {
            return new Jangbaguny(in);
        }

        @Override
        public Jangbaguny[] newArray(int size) {
            return new Jangbaguny[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menuname);
        dest.writeString(iceable);
        dest.writeString(size);
        dest.writeInt(quantity);
        dest.writeInt(price);
    }

    public Jangbaguny(String menuname, String iceable, String size, int quantity,int price) {
        this.menuname = menuname;
        this.iceable = iceable;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public Jangbaguny() {
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getIceable() {
        return iceable;
    }

    public void setIceable(String iceable) {
        this.iceable = iceable;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Jangbaguny{" +
                "menuname='" + menuname + '\'' +
                ", iceable=" + iceable +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
