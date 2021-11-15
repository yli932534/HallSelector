package com.app.hallselector.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Search_Record {
    private String username;
    private Date date;
    private List<String> filters;
    private List<Building> buildings;

    public Search_Record(String username, Date date, List<String> filters, List<Building> buildings){
        this.username = username;
        this.date = date;
        this.buildings = buildings;
        this.filters = filters;
    }

    public Search_Record(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public List<String> getBuildingName(){
        List<String> b = new ArrayList<>();
        for(int i = 0; i<buildings.size();i++){
            Building temp = buildings.get(i);
            b.add(temp.getName());
        }
        return b;
    }



}