package com.example.zaevtour.ui.bookmark;

public class BookmarkItem {
    int image;
    String name;
    String location;
    String hours;

    public BookmarkItem(int image, String name, String location, String hours){
        this.image = image;
        this.name = name;
        this.location = location;
        this.hours = hours;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}