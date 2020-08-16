package com.company.model;

public class Ingredients {
    private double hot_water;
    private double hot_milk;
    private double ginger_syrup;
    private double sugar_syrup;
    private double tea_leaves_syrup;
    private double green_mixture;
    public Ingredients()
    {

    }
    public Ingredients(double hot_water, double hot_milk, double ginger_syrup, double sugar_syrup, double tea_leaves_syrup, double green_mixture)
    {
        this.ginger_syrup = ginger_syrup;
        this.hot_milk = hot_milk;
        this.hot_water = hot_water;
        this.sugar_syrup = sugar_syrup;
        this.tea_leaves_syrup = tea_leaves_syrup;
        this.green_mixture = green_mixture;
    }
    public double getHot_water() {
        return hot_water;
    }

    public void setHot_water(double hot_water) {
        this.hot_water = hot_water;
    }

    public double getHot_milk() {
        return hot_milk;
    }

    public void setHot_milk(double hot_milk) {
        this.hot_milk = hot_milk;
    }

    public double getGinger_syrup() {
        return ginger_syrup;
    }

    public void setGinger_syrup(double ginger_syrup) {
        this.ginger_syrup = ginger_syrup;
    }

    public double getSugar_syrup() {
        return sugar_syrup;
    }

    public void setSugar_syrup(double sugar_syrup) {
        this.sugar_syrup = sugar_syrup;
    }

    public double getTea_leaves_syrup() {
        return tea_leaves_syrup;
    }

    public void setTea_leaves_syrup(double tea_leaves_syrup) {
        this.tea_leaves_syrup = tea_leaves_syrup;
    }

    public double getGreen_mixture() {
        return green_mixture;
    }

    public void setGreen_mixture(double green_mixture) {
        this.green_mixture = green_mixture;
    }
}
