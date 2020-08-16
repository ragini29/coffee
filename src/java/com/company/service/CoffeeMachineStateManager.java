package com.company.service;

import com.company.model.Beverages;
import com.company.model.Ingredients;
import com.company.model.Outlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CoffeeMachineStateManager {

    private  static volatile CoffeeMachineStateManager coffeeMachineStateManager = null;

    private long timeToMakeCoffee = 60000;
    private Semaphore slots ;
    private Semaphore update = new Semaphore(1);
    private Semaphore updateSlotAvailable = new Semaphore(1);;
    private int slotsAvailable = 10;
    private Beverages totalBeveragesQuantity = new Beverages("total", new Ingredients());
    private CoffeeMachineStateManager()
    {
    }

    public synchronized static CoffeeMachineStateManager getInstance()
    {
        if(coffeeMachineStateManager == null)
            coffeeMachineStateManager = new CoffeeMachineStateManager();
        return coffeeMachineStateManager;
    }
    public void initializeCoffeeMachine(Outlets outlets, Ingredients total_items_quantity)
    {
        totalBeveragesQuantity.setIngredients(total_items_quantity);
        this.slotsAvailable = outlets.getCount_n();
        this.slots = new Semaphore(outlets.getCount_n());

    }

    public void refillCoffeeMachineQuantity(Beverages beverages)
    {
        addIngredientsQuantity(beverages);
    }

    public Map<String, Double> isValidRequest(Beverages beverage) throws InterruptedException {

        update.acquire();
        Map<String, Double> res= totalBeveragesQuantity.checkUnavalibility(beverage);
        update.release();
        return  res;
    }

    public void processCoffeeRequest(Beverages coffee) {

        try {
            System.out.println("Trying to acquire a slot!!");
            if(slots.tryAcquire(0, TimeUnit.SECONDS)) {
                System.out.println("Slot Acquired!!");
                update.acquire();
                    System.out.println("Slot: " + slotsAvailable + " Preparing to make coffee");
                    reduceIngredientQuantity(coffee);
                    updateSlotAvailable.acquire();
                    slotsAvailable--;
                    updateSlotAvailable.release();
                    update.release();
                    makeCoffee();
                    updateSlotAvailable.acquire();
                    slotsAvailable++;
                    System.out.println("Rel");
                    updateSlotAvailable.release();
                    slots.release();

                 update.release();
                }
            }

         catch (InterruptedException e) {
            System.out.println("Sorry!! No slots available");
        }
    }

    public Map<String,Double> getAvailableQuantity() throws InterruptedException {
        update.acquire();
        Map<String, Double> availableIngredients = new HashMap<>();
        availableIngredients.put("hot_water", totalBeveragesQuantity.getIngredients().getHot_water());
        availableIngredients.put("hot_milk", totalBeveragesQuantity.getIngredients().getHot_milk());
        availableIngredients.put("ginger_syrup", totalBeveragesQuantity.getIngredients().getGinger_syrup());
        availableIngredients.put("sugar_syrup", totalBeveragesQuantity.getIngredients().getSugar_syrup());
        availableIngredients.put("tea_leaves_syrup", totalBeveragesQuantity.getIngredients().getTea_leaves_syrup());
        availableIngredients.put("green_mixture", totalBeveragesQuantity.getIngredients().getGreen_mixture());

        update.release();
        return availableIngredients;
    }

    private void reduceIngredientQuantity(Beverages coffee) {
        totalBeveragesQuantity.reduce(coffee);
    }

    private void addIngredientsQuantity(Beverages coffee)
    {
        totalBeveragesQuantity.add(coffee);
    }

    private void makeCoffee() throws InterruptedException {
        System.out.println("Slot: "+ (slotsAvailable+1)+ " !!Please hold on while we are serving your order!!");
        Thread.sleep(timeToMakeCoffee);
        System.out.println("Slot: "+ (slotsAvailable+1)+ " Enjoy your coffee ");
    }
    public int getSlotsAvailable()
    {
        return slotsAvailable;
    }

}
