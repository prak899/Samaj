package com.siddhi.softwares.sutsamaj.Model;

public class TempAddress {
    String address;
    int state, district, tehsil, village, pin;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getTehsil() {
        return tehsil;
    }

    public void setTehsil(int tehsil) {
        this.tehsil = tehsil;
    }

    public int getVillage() {
        return village;
    }

    public void setVillage(int village) {
        this.village = village;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
