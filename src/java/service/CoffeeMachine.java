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
        initializeMachine(machine.getOutlets(), machine.getTotal_items_quantity());
        return processAllRequest(machine.getBeverages());
    }

    private List<String> processAllRequest(List<Beverages> beverages) throws InterruptedException {
        List<String> result = new ArrayList<>();
        for (Beverages beverage:
             beverages) {
            if(coffeeMachineStateManager.getSlotsAvailable()>=1) {
                Map<String, Double> unavailable = coffeeMachineStateManager.isValidRequest(beverage);
                if (unavailable == null) {
                    UUID uuid = UUID.randomUUID();
                    ProcessRequestThread thread = new ProcessRequestThread("Thread: "+ uuid, beverage);
                    thread.start();
                    result.add(beverage.getName() + " is prepared.");
                } else {
                    String str = (beverage.getName() + "  cannot be prepared because");
                    for (Map.Entry<String, Double> mp :
                            unavailable.entrySet()) {
                        if (mp.getValue() > 0.0)
                            str += " " + mp.getKey() + " is not sufficient ";
                        else
                            str += " " + mp.getKey() + " is not available ";
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
        coffeeMachineStateManager.initializeCoffeeMachine(outlets, total_items_quantity);
    }

    private void refillMachine(Beverages beverages)
    {
        coffeeMachineStateManager.refillCoffeeMachineQuantity(beverages);
    }


}
