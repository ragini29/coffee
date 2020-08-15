package com.company.service;

import com.company.model.Beverages;

public class ProcessRequestThread implements Runnable{
    Thread t;
    String name;
    Beverages beverages;

    ProcessRequestThread(String name, Beverages beverages)
    {
        this.name = name;
        this.beverages = beverages;
    }

    @Override
    public void run()
    {
        System.out.println("Executing process");
        CoffeeMachineStateManager.getInstance().processCoffeeRequest(beverages);
    }

    public void start()
    {
        if(t==null)
        {
            System.out.println("Starting thread with name: "+name);
            t= new Thread(this, name);
        }
        t.start();
    }
}
