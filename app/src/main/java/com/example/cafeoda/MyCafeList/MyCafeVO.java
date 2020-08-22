package com.example.cafeoda.MyCafeList;

public class MyCafeVO {

    int cafeid;
    String cafename;

    public MyCafeVO(int cafeid, String cafename) {
        this.cafeid = cafeid;
        this.cafename = cafename;
    }


    public int getcafeid() {
        return cafeid;
    }

    public String getName() {
        return cafename;
    }
}

