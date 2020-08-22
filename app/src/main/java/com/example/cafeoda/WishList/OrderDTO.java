package com.example.cafeoda.WishList;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private int ordid;
    private String guestphone;
    private String ordnum;
    private String orderdate;
    private int cafeid;
    private String cafename;
    private int menunum;
    private String prdname;
    private String country;
    private int quantity;
    private String icehot;
    private String cupsize;
    private int oneprice;
    private int totalprice;
    private String status;
    private String statusmsg;
    private String completetime;

    public OrderDTO() {
    }

    public OrderDTO(int ordid, String guestphone, String ordnum, String orderdate, int cafeid, String cafename, int menunum, String prdname, String country, int quantity, String icehot, String cupsize, int oneprice, int totalprice, String status, String statusmsg, String completetime) {
        this.ordid = ordid;
        this.guestphone = guestphone;
        this.ordnum = ordnum;
        this.orderdate = orderdate;
        this.cafeid = cafeid;
        this.cafename = cafename;
        this.menunum = menunum;
        this.prdname = prdname;
        this.country = country;
        this.quantity = quantity;
        this.icehot = icehot;
        this.cupsize = cupsize;
        this.oneprice = oneprice;
        this.totalprice = totalprice;
        this.status = status;
        this.statusmsg = statusmsg;
        this.completetime = completetime;
    }

    public int getOrdid() {
        return ordid;
    }

    public void setOrdid(int ordid) {
        this.ordid = ordid;
    }

    public String getGuestphone() {
        return guestphone;
    }

    public void setGuestphone(String guestphone) {
        this.guestphone = guestphone;
    }

    public String getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(String ordnum) {
        this.ordnum = ordnum;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public int getCafeid() {
        return cafeid;
    }

    public void setCafeid(int cafeid) {
        this.cafeid = cafeid;
    }

    public String getCafename() {
        return cafename;
    }

    public void setCafename(String cafename) {
        this.cafename = cafename;
    }

    public int getMenunum() {
        return menunum;
    }

    public void setMenunum(int menunum) {
        this.menunum = menunum;
    }

    public String getPrdname() {
        return prdname;
    }

    public void setPrdname(String prdname) {
        this.prdname = prdname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIcehot() {
        return icehot;
    }

    public void setIcehot(String icehot) {
        this.icehot = icehot;
    }

    public String getCupsize() {
        return cupsize;
    }

    public void setCupsize(String cupsize) {
        this.cupsize = cupsize;
    }

    public int getOneprice() {
        return oneprice;
    }

    public void setOneprice(int oneprice) {
        this.oneprice = oneprice;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusmsg() {
        return statusmsg;
    }

    public void setStatusmsg(String statusmsg) {
        this.statusmsg = statusmsg;
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "ordid=" + ordid +
                ", guestphone='" + guestphone + '\'' +
                ", ordnum='" + ordnum + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", cafeid=" + cafeid +
                ", cafename='" + cafename + '\'' +
                ", menunum=" + menunum +
                ", prdname='" + prdname + '\'' +
                ", country='" + country + '\'' +
                ", quantity=" + quantity +
                ", icehot='" + icehot + '\'' +
                ", cupsize='" + cupsize + '\'' +
                ", oneprice=" + oneprice +
                ", totalprice=" + totalprice +
                ", status='" + status + '\'' +
                ", statusmsg='" + statusmsg + '\'' +
                ", completetime='" + completetime + '\'' +
                '}';
    }
}

