package com.company.service;

import com.company.model.Beverages;
import com.company.model.Ingredients;
import com.company.model.Outlets;

import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CoffeeMachineStateManager {

    private  static volatile CoffeeMachineStateManager coffeeMachineStateManager = null;

    private long timeToMakeCoffee = 120000;
    private long timeToRefill = 300000;
    private Semaphore slots ;
    private Semaphore update = new Semaphore(1);
    private Semaphore updateSlotAvailable = new Semaphore(1);;
    private int slotsAvailable = 10;
    private boolean refill = false;
    private double maxCapacity = 500.00;
    private boolean runningLow = false;
    private Beverages totalBeveragesQuantity;
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
        Ingredients ingredients = new Ingredients(total_items_quantity.getTea_leaves_syrup(),total_items_quantity.getGinger_syrup(),
                total_items_quantity.getHot_milk(), total_items_quantity.getHot_water(),total_items_quantity.getSugar_syrup(), total_items_quantity.getGreen_mixture());
        totalBeveragesQuantity = new Beverages("total", ingredients);
        this.slotsAvailable = outlets.getCount_n();
        this.slots = new Semaphore(outlets.getCount_n());

    }

    public void refillCoffeeMachineQuantity(Beverages beverages)
    {
        addIngredientsQuantity(beverages);
    }

    public Map<String, Double> isValidRequest(Beverages beverage) {

        return  totalBeveragesQuantity.checkUnavalibility(beverage);

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
                    updateSlotAvailable.release();
                    slots.release();
                 update.release();
                }
            }

         catch (InterruptedException e) {
            System.out.println("Sorry!! No slots available");
        }
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
