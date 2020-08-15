package com.company.model;

import java.util.HashMap;
import java.util.Map;

public class Beverages{
    private String name;
    Ingredients ingredients;
    public  Beverages()
    {
        
    }
    public Beverages(String name, Ingredients i)
    {
        this.name = name;
        this.ingredients = i;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Map<String, Double> checkUnavalibility(Beverages o) {
        Map<String, Double> unavailable = null;
        if (o.ingredients.getSugar_syrup()<=ingredients.getSugar_syrup() &&
            o.ingredients.getHot_water()<=ingredients.getHot_water() &&
            o.ingredients.getGinger_syrup()<=ingredients.getGinger_syrup() &&
            o.ingredients.getHot_milk()<=ingredients.getHot_milk() &&
            o.ingredients.getTea_leaves_syrup()<=ingredients.getTea_leaves_syrup()&&
                o.ingredients.getGreen_mixture()<=ingredients.getGreen_mixture()
        )
        ;
        else
        {
            unavailable = new HashMap<>();
            if (o.ingredients.getSugar_syrup()> ingredients.getSugar_syrup())
                unavailable.put("sugar_syrup", ingredients.getSugar_syrup());
            if (o.ingredients.getTea_leaves_syrup()> ingredients.getTea_leaves_syrup())
                unavailable.put("Tea_leaves_syrup", ingredients.getTea_leaves_syrup());
            if (o.ingredients.getGinger_syrup()> ingredients.getGinger_syrup())
                unavailable.put("Ginger_syrup", ingredients.getGinger_syrup());
            if (o.ingredients.getHot_milk()> ingredients.getHot_milk())
                unavailable.put("Hot_milk", ingredients.getHot_milk());
            if (o.ingredients.getHot_water()> ingredients.getHot_water())
                unavailable.put("Hot_water", ingredients.getHot_water());
            if (o.ingredients.getGreen_mixture()> ingredients.getSugar_syrup())
                unavailable.put("sugar_syrup", ingredients.getSugar_syrup());
        }

        return  unavailable;
    }

    public void reduce(Beverages o) {
        ingredients.setSugar_syrup(ingredients.getSugar_syrup() - o.ingredients.getSugar_syrup());
        ingredients.setHot_water(ingredients.getHot_water() - o.ingredients.getHot_water());
        ingredients.setHot_milk(ingredients.getHot_milk() - o.ingredients.getHot_milk());
        ingredients.setGinger_syrup(ingredients.getGinger_syrup() - o.ingredients.getGinger_syrup());
        ingredients.setTea_leaves_syrup(ingredients.getTea_leaves_syrup() - o.ingredients.getTea_leaves_syrup());
    }

    public void add(Beverages o) {
        ingredients.setSugar_syrup(ingredients.getSugar_syrup() + o.ingredients.getSugar_syrup());
        ingredients.setHot_water(ingredients.getHot_water() + o.ingredients.getHot_water());
        ingredients.setHot_milk(ingredients.getHot_milk() + o.ingredients.getHot_milk());
        ingredients.setGinger_syrup(ingredients.getGinger_syrup() + o.ingredients.getGinger_syrup());
        ingredients.setTea_leaves_syrup(ingredients.getTea_leaves_syrup() + o.ingredients.getTea_leaves_syrup());
    }

}
