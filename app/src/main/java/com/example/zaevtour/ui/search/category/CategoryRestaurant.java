package com.example.zaevtour.ui.search.category;

public class CategoryRestaurant {
    int image;
    String name;
    String menu;

    public CategoryRestaurant(int image, String name, String menu){
        this.image = image;
        this.name = name;
        this.menu = menu;
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
        return menu;
    }

    public void setLocation(String menu) {
        this.menu = menu;
    }


}
