package com.example.cafeoda.WishList;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoppingItem implements Parcelable {
    String menuname;
    String optionice;
    String size;
    int quantity;
    int price;

    public ShoppingItem() {
    }

    public ShoppingItem(String menuname, String optionice, String size, int quantity,int price) {
        this.menuname = menuname;
        this.optionice = optionice;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    protected ShoppingItem(Parcel in) {
        menuname = in.readString();
        optionice = in.readString();
        size = in.readString();
        quantity = in.readInt();
        price = in.readInt();
    }

    public static final Creator<ShoppingItem> CREATOR = new Creator<ShoppingItem>() {
        @Override
        public ShoppingItem createFromParcel(Parcel in) {
            return new ShoppingItem(in);
        }

        @Override
        public ShoppingItem[] newArray(int size) {
            return new ShoppingItem[size];
        }
    };

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getOptionice() {
        return optionice;
    }

    public void setOptionice(String optionice) {
        this.optionice = optionice;
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
        return "ShoppingItem{" +
                "menuname='" + menuname + '\'' +
                ", optionice=" + optionice +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menuname);
        dest.writeString(optionice);
        dest.writeString(size);
        dest.writeInt(quantity);
        dest.writeInt(price);
    }
}
