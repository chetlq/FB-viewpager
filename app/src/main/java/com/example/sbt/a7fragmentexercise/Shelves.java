package com.example.sbt.a7fragmentexercise;

import java.util.Map;

public class Shelves {
    private String weight;
    private String redline;
    private  String id;

    public Shelves() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Shelves(Map<String, String> list, String id) {
        this.id = id;
        for (Map.Entry entry : list.entrySet()) {

            if (entry.getKey().equals("weight"))
                this.weight = (String) entry.getValue();
            if (entry.getKey().equals("redline"))
                this.redline = (String) entry.getValue();
        }
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getWeight() {
        return weight;
    }

    public String getId() {
        return id;
    }

    public String getRedline() {
        return redline;
    }

    public Shelves(String weight, String redline) {
        this.weight = weight;
        this.redline = redline;
    }
}
