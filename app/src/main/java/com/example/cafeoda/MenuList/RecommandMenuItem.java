package com.example.cafeoda.MenuList;

public class RecommandMenuItem {

    int menunum;
    int cafeid;
    int proid;
    String proname;
    int price;
    String country;
    int imgsource;

    public RecommandMenuItem(){

    }
    public RecommandMenuItem(int menunum, int cafeid, int proid, String proname, int price, String country, int imgsource) {
        this.menunum = menunum;
        this.cafeid = cafeid;
        this.proid = proid;
        this.proname = proname;
        this.price = price;
        this.country = country;
        this.imgsource = imgsource;
    }

    public int getMenunum() {
        return menunum;
    }

    public void setMenunum(int menunum) {
        this.menunum = menunum;
    }

    public int getCafeid() {
        return cafeid;
    }

    public void setCafeid(int cafeid) {
        this.cafeid = cafeid;
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImgsource() {
        return imgsource;
    }

    public void setImgsource(int imgsource) {
        this.imgsource = imgsource;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menunum=" + menunum +
                ", cafeid=" + cafeid +
                ", proid=" + proid +
                ", proname='" + proname + '\'' +
                ", price=" + price +
                ", country='" + country + '\'' +
                ", imgsource='" + imgsource + '\'' +
                '}';
    }
}
