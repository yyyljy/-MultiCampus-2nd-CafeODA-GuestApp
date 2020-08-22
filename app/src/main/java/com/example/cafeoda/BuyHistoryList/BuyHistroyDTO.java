package com.example.cafeoda.BuyHistoryList;

public class BuyHistroyDTO {
    private int ordid;
    private String guestphone;
    private int ordnum;
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
    private String status;
    private String statusmsg;
    private String completeti;



    public BuyHistroyDTO() {

    }
    public BuyHistroyDTO(int ordnum,String orderdate,String cafename,String prdname,
                         int quantity,String icehot,String cupsize,int oneprice,
                         String statusmsg){
        super();
        this.ordnum = ordnum;
        this.orderdate = orderdate;
        this.cafename = cafename;
        this.prdname = prdname;
        this.quantity = quantity;
        this.icehot = icehot;
        this.cupsize = cupsize;
        this.oneprice = oneprice;
        this.statusmsg = statusmsg;
    }


    public BuyHistroyDTO(int ordid, String guestphone, int ordnum, String orderdate, int cafeid, String cafename,
                    int menunum, String prdname, String country, int quantity, String icehot, String cupsize, int oneprice,
                    int totalprice, String status, String statusmsg, String completeti) {
        super();
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
        this.status = status;
        this.statusmsg = statusmsg;
        this.completeti = completeti;
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



    public int getOrdnum() {
        return ordnum;
    }



    public void setOrdnum(int ordnum) {
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



    public String getCompleteti() {
        return completeti;
    }



    public void setCompleteti(String completeti) {
        this.completeti = completeti;
    }



    @Override
    public String toString() {
        return "OrderDTO [ordid=" + ordid + ", guestphone=" + guestphone + ", ordnum=" + ordnum + ", orderdate="
                + orderdate + ", cafeid=" + cafeid + ", cafename=" + cafename + ", menunum=" + menunum + ", prdname="
                + prdname + ", country=" + country + ", quantity=" + quantity + ", icehot=" + icehot + ", cupsize="
                + cupsize + ", oneprice=" + oneprice + ", totalprice=" + ", status=" + status
                + ", statusmsg=" + statusmsg + ", completeti=" + completeti + "]";
    }




}
