package com.example.thibanglai.model;

import java.io.Serializable;

public class ItemLaw implements Serializable {
    private String name;
    private String object,fines,aditional_pen,remedial_measures;
    int groupID;

    public ItemLaw(String name, String object, String fines, String aditional_pen, String remedial_measures, int groupID) {
        this.name = name;
        this.object = object;
        this.fines = fines;
        this.aditional_pen = aditional_pen;
        this.remedial_measures = remedial_measures;
        this.groupID = groupID;
    }

    public ItemLaw() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getFines() {
        return fines;
    }

    public void setFines(String fines) {
        this.fines = fines;
    }

    public String getAditional_pen() {
        return aditional_pen;
    }

    public void setAditional_pen(String aditional_pen) {
        this.aditional_pen = aditional_pen;
    }

    public String getRemedial_measures() {
        return remedial_measures;
    }

    public void setRemedial_measures(String remedial_measures) {
        this.remedial_measures = remedial_measures;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
