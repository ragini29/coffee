package com.company;

import com.company.model.Beverages;
import com.company.model.Ingredients;
import com.company.model.Machine;
import com.company.model.Outlets;
import com.company.service.CoffeeMachine;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
public class CoffeeMachineTest {
    private static CoffeeMachine coffeeMachine = new CoffeeMachine();

    @Test
    public void testCoffeeMachineServeRequest()
    {
        Machine machine = getMachine();
        try {
            List<String > result = coffeeMachine.serveMachineRequest(machine);
            for (String res:
                 result) {
                System.out.println(res);
            }
            Assert.assertEquals(result.size(), 4);
            Assert.assertTrue(result.get(0).toLowerCase().contains("hot_tea is prepared") );
            Assert.assertTrue(result.get(1).toLowerCase().contains("hot_coffee cannot be prepared") );
            Assert.assertTrue(result.get(2).toLowerCase().contains("black_tea is prepared") );
            Assert.assertTrue(result.get(3).toLowerCase().contains("green_tea cannot be prepared") );

        } catch (InterruptedException e) {
            Assert.assertTrue(false);
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

    @Test
    public void testRefill()
    {
        Ingredients ingredients = new Ingredients(100,100,100,100,100,100);
        Beverages beverages = new Beverages("refill", ingredients);

        try {
            Map<String,Double> previous = coffeeMachine.getAvailableIngredients();
            coffeeMachine.refillMachine(beverages);
            Map<String,Double> result = coffeeMachine.getAvailableIngredients();
            Assert.assertTrue(result.get("hot_water") == 100 + previous.get("hot_water"));
            Assert.assertTrue(result.get("hot_milk") == 100 + previous.get("hot_milk"));
            Assert.assertTrue(result.get("ginger_syrup") == 100 + previous.get("ginger_syrup"));
            Assert.assertTrue(result.get("sugar_syrup") == 100 + previous.get("sugar_syrup"));
            Assert.assertTrue(result.get("tea_leaves_syrup") == 100 + previous.get("tea_leaves_syrup"));
            Assert.assertTrue(result.get("green_mixture") == 100 + previous.get("green_mixture"));
        } catch (InterruptedException e) {
            Assert.assertTrue(false);
        }
    }
}
