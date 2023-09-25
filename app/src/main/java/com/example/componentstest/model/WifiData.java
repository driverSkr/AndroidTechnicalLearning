package com.example.componentstest.model;

public class WifiData {
    private String bssid;
    private String name;
    private String level;
    private int frequency;
    private String capabilites;

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getCapabilites() {
        return capabilites;
    }

    public void setCapabilites(String capabilites) {
        this.capabilites = capabilites;
    }
}
