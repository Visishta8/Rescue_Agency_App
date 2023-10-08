package com.example.recycler_view;

public class Dish {
    public int image,numItem;
    public String name;
    public String email; // Add email field
    public String location; // Add location field
    public String expertiseArea; // Add expertise area field
    public String registrationDate; // Add registration date field

    public Dish(int image, int numItem, String name, String email, String location, String expertiseArea, String registrationDate) {
        this.image = image;
        this.numItem = numItem;
        this.name = name;
        this.email = email;
        this.location = location;
        this.expertiseArea = expertiseArea;
        this.registrationDate = registrationDate;
    }
}
