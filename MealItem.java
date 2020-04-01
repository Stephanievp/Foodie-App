package com.example.foodieapp;

public class MealItem {

    private String title;
    private String description;
    private String meal_info;
    private int imageResource;

    //constructor for the meal data
    public MealItem(String title, String description){
        this.title = title;
        this.description = description;
    }

    public MealItem(String title, String description, int imageId){
        this.title = title;
        this.description = description;
        this.imageResource = imageId;
    }

    public MealItem(String title, String description, int imageId, String info){
        this.title = title;
        this.description = description;
        this.imageResource = imageId;
        this.meal_info = info;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public int getImageId(){
        return imageResource;
    }

    public String getMeal_info(){
        return meal_info;
    }

}
