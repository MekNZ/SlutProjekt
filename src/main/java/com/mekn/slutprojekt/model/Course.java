package com.mekn.slutprojekt.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String course;
    private boolean isVegetarian;
    private double price;

    
    private List<Ingredient> ingredients;

    public Course() {
    }

    public Course(String course, boolean isVegetarian, double price) {
        this.course = course;
        this.isVegetarian = isVegetarian;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}


