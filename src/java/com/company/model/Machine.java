package com.company.model;

import java.util.List;

public class Machine {
    private Outlets outlets;
    private Ingredients total_items_quantity;
    private List<Beverages> beverages;

    public Outlets getOutlets() {
        return outlets;
    }

    public void setOutlets(Outlets outlets) {
        this.outlets = outlets;
    }

    public Ingredients getTotal_items_quantity() {
        return total_items_quantity;
    }

    public void setTotal_items_quantity(Ingredients total_items_quantity) {
        this.total_items_quantity = total_items_quantity;
    }

    public List<Beverages> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverages> beverages) {
        this.beverages = beverages;
    }
}