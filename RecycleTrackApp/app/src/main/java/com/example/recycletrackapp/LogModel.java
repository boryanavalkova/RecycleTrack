package com.example.recycletrackapp;

import java.util.Date;

public class LogModel {

    private int id;
    private int recycled;
    private  int general;

    //common constructor
    public LogModel(int id, int recycled, int general) {
        this.id = id;
        this.recycled = recycled;
        this.general = general;

    }

    public LogModel() {

    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id=" + id +
                ", recycled=" + recycled +
                ", general=" + general +
                '\'' +
                '}';
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecycled() {
        return recycled;
    }

    public void setRecycled(int recycled) {
        this.recycled = recycled;
    }

    public int getGeneral() {
        return general;
    }

    public void setGeneral(int general) {
        this.general = general;
    }

}
