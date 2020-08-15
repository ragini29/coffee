package com.company;

import com.company.model.Beverages;
import com.company.model.Ingredients;
import com.company.model.Machine;
import com.company.model.Outlets;
import com.company.service.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private static CoffeeMachine coffeeMachine = new CoffeeMachine();

    public static void main(String args[])
    {
        Machine machine = getMachine();
        try {
            List<String > result = coffeeMachine.serveMachineRequest(machine);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Machine getMachine() {
        Machine machine = new Machine();
        machine.setOutlets(new Outlets(3));
        machine.setTotal_items_quantity(new Ingredients(500, 500, 100, 100, 100,0));
        List<Beverages>beverages = new ArrayList<>();
        beverages.add(new Beverages("hot_tea", new Ingredients(200.0,100.0,10.0,10.0,30.0, 0.0)));
        beverages.add(new Beverages("hot_coffee", new Ingredients(100.0,30,400,50,30, 0)));
        beverages.add(new Beverages("black_tea", new Ingredients(300,0,30,50,30,0)));
        beverages.add(new Beverages("green_tea",new Ingredients(100,0,30,50,0,30)));
        machine.setBeverages(beverages);
        return machine;
    }
}
