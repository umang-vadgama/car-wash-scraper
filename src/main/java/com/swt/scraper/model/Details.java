package com.swt.scraper.model;


public class Details {

    String city;
    String store;
    String address;
    String email;
    String phoneNumber;
    String tollFreeNumber;
    String time;
    String days;

    public Details(String city, String store, String address, String email, String phoneNumber, String tollFreeNumber, String time, String days) {
        this.city = city;
        this.store = store;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tollFreeNumber = tollFreeNumber;
        this.time = time;
        this.days = days;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTollFreeNumber() {
        return tollFreeNumber;
    }

    public void setTollFreeNumber(String tollFreeNumber) {
        this.tollFreeNumber = tollFreeNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Details{" +
                "city='" + city + '\'' +
                ", store='" + store + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tollFreeNumber='" + tollFreeNumber + '\'' +
                ", time='" + time + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}
