package com.example.googlemap;

public class Spot {

    private String name;
    private String type;
    private String note;

    public Spot(String name, String type,String note){

        this.name=name;
        this.type=type;
        this.note=note;

    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getNote() {
        return note;
    }




}
