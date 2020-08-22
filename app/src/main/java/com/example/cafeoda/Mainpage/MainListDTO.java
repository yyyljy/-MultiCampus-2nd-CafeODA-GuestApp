package com.example.cafeoda.Mainpage;

public class MainListDTO {
    private int cafeid;
    private String cafename;
    private String address;
    private double latitude;
    private double longitude;
    private String weekend_opentime;
    private String weekend_closetime;
    private String weekday_opentime	;
    private String weekday_closetime;
    private String regday;
    private int mostpick1;
    private int mostpick2;
    private int mostpick3;
    private String nearstation1;
    private String nearstation2;
    private String tel;
    private String pass;
    private int img;

    public MainListDTO() {

    }

    public MainListDTO(int cafeid, String cafename, int img) {
        this.cafeid = cafeid;
        this.cafename = cafename;
        this.img = img;
    }

    public MainListDTO(String cafename) {
        this.cafename = cafename;
    }

    public MainListDTO(String cafename, int img) {
        this.cafename = cafename;
        this.img = img;
    }

    public MainListDTO(int cafeid, String cafename) {
        this.cafeid = cafeid;
        this.cafename = cafename;
    }

    public MainListDTO(int cafeid, String cafename, String address, double latitude, double longitude,
                       String weekend_opentime, String weekend_closetime, String weekday_opentime, String weekday_closetime,
                       String regday, int mostpick1, int mostpick2, int mostpick3, String nearstation1, String nearstation2,
                       String tel, String pass) {
        super();
        this.cafeid = cafeid;
        this.cafename = cafename;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weekend_opentime = weekend_opentime;
        this.weekend_closetime = weekend_closetime;
        this.weekday_opentime = weekday_opentime;
        this.weekday_closetime = weekday_closetime;
        this.regday = regday;
        this.mostpick1 = mostpick1;
        this.mostpick2 = mostpick2;
        this.mostpick3 = mostpick3;
        this.nearstation1 = nearstation1;
        this.nearstation2 = nearstation2;
        this.tel = tel;
        this.pass = pass;
    }


    public MainListDTO(int cafeid, String cafename, String address, double latitude, double longitude, String weekend_opentime, String weekend_closetime, String weekday_opentime, String weekday_closetime, String regday, int mostpick1, int mostpick2, int mostpick3, String nearstation1, String nearstation2, String tel, String pass, int img) {
        this.cafeid = cafeid;
        this.cafename = cafename;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weekend_opentime = weekend_opentime;
        this.weekend_closetime = weekend_closetime;
        this.weekday_opentime = weekday_opentime;
        this.weekday_closetime = weekday_closetime;
        this.regday = regday;
        this.mostpick1 = mostpick1;
        this.mostpick2 = mostpick2;
        this.mostpick3 = mostpick3;
        this.nearstation1 = nearstation1;
        this.nearstation2 = nearstation2;
        this.tel = tel;
        this.pass = pass;
        this.img = img;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getWeekend_opentime() {
        return weekend_opentime;
    }

    public void setWeekend_opentime(String weekend_opentime) {
        this.weekend_opentime = weekend_opentime;
    }

    public String getWeekend_closetime() {
        return weekend_closetime;
    }

    public void setWeekend_closetime(String weekend_closetime) {
        this.weekend_closetime = weekend_closetime;
    }

    public String getWeekday_opentime() {
        return weekday_opentime;
    }

    public void setWeekday_opentime(String weekday_opentime) {
        this.weekday_opentime = weekday_opentime;
    }

    public String getWeekday_closetime() {
        return weekday_closetime;
    }

    public void setWeekday_closetime(String weekday_closetime) {
        this.weekday_closetime = weekday_closetime;
    }

    public String getRegday() {
        return regday;
    }

    public void setRegday(String regday) {
        this.regday = regday;
    }

    public int getMostpick1() {
        return mostpick1;
    }

    public void setMostpick1(int mostpick1) {
        this.mostpick1 = mostpick1;
    }

    public int getMostpick2() {
        return mostpick2;
    }

    public void setMostpick2(int mostpick2) {
        this.mostpick2 = mostpick2;
    }

    public int getMostpick3() {
        return mostpick3;
    }

    public void setMostpick3(int mostpick3) {
        this.mostpick3 = mostpick3;
    }

    public String getNearstation1() {
        return nearstation1;
    }

    public void setNearstation1(String nearstation1) {
        this.nearstation1 = nearstation1;
    }

    public String getNearstation2() {
        return nearstation2;
    }

    public void setNearstation2(String nearstation2) {
        this.nearstation2 = nearstation2;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "NewShopDTO{" +
                "cafeid=" + cafeid +
                ", cafename='" + cafename + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", weekend_opentime='" + weekend_opentime + '\'' +
                ", weekend_closetime='" + weekend_closetime + '\'' +
                ", weekday_opentime='" + weekday_opentime + '\'' +
                ", weekday_closetime='" + weekday_closetime + '\'' +
                ", regday='" + regday + '\'' +
                ", mostpick1=" + mostpick1 +
                ", mostpick2=" + mostpick2 +
                ", mostpick3=" + mostpick3 +
                ", nearstation1='" + nearstation1 + '\'' +
                ", nearstation2='" + nearstation2 + '\'' +
                ", tel='" + tel + '\'' +
                ", pass='" + pass + '\'' +
                ", img=" + img +
                '}';
    }
}
