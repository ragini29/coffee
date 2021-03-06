package com.company.service;

import com.company.model.Beverages;
import com.company.model.Ingredients;
import com.company.model.Machine;
import com.company.model.Outlets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CoffeeMachine {

    CoffeeMachineStateManager coffeeMachineStateManager = CoffeeMachineStateManager.getInstance();

    public List<String> serveMachineRequest(Machine machine) throws InterruptedException {

       // Initialize machine with number of outlets and total quantity mentioned

        initializeMachine(machine.getOutlets(), machine.getTotal_items_quantity());

        // process all beverages request

        List<String> res = processAllRequest(machine.getBeverages());

        // check if no slots is occupied serving request
           while (coffeeMachineStateManager.getSlotsAvailable()!=machine.getOutlets().getCount_n()) {
                Thread.sleep(10000);
            }
        System.out.println("Finished serving request slots available  "+ coffeeMachineStateManager.getSlotsAvailable());

        return res;
    }

    public void refillMachine(Beverages beverages)
    {
        System.out.println("!! Refilling machine !!");
        coffeeMachineStateManager.refillCoffeeMachineQuantity(beverages);
    }

    public Map<String,Double> getAvailableIngredients() throws InterruptedException {
        System.out.println("!! Get Available Quantity !!");
        return  coffeeMachineStateManager.getAvailableQuantity();
    }
    private List<String> processAllRequest(List<Beverages> beverages) throws InterruptedException {
        System.out.println(" Serve all beverages request ");
        List<String> result = new ArrayList<>();
        for (Beverages beverage:
             beverages) {
            if(coffeeMachineStateManager.getSlotsAvailable()>=1) {
                Map<String, Double> unavailable = coffeeMachineStateManager.isValidRequest(beverage);
                if (unavailable == null) {
                    UUID uuid = UUID.randomUUID();
                    ProcessRequestThread thread = new ProcessRequestThread("Thread: "+ uuid, beverage);
                    thread.start();
                    result.add(beverage.getName().toLowerCase() + " is prepared.");
                } else {
                    String str = (beverage.getName().toLowerCase() + " cannot be prepared because");
                    for (Map.Entry<String, Double> mp :
                            unavailable.entrySet()) {
                        if (mp.getValue() > 0.0)
                            str += " " + mp.getKey().toLowerCase() + " is not sufficient ";
                        else
                            str += " " + mp.getKey().toLowerCase() + " is not available ";
                    }
                    result.add(str);
                }
            }
            else
                while (coffeeMachineStateManager.getSlotsAvailable()<1)
                Thread.sleep(10000);

        }

      return result;
    }

    private void initializeMachine(Outlets outlets, Ingredients total_items_quantity) {
        System.out.println("!! Initializing machine !!");
        coffeeMachineStateManager.initializeCoffeeMachine(outlets, total_items_quantity);
    }

}
