
# Project name: Coffee-Machine
Introduction : Project contains brewing service to serve coffee requests. It exposes 3 functions :
  a. To Serve User Request
  b. To Refill Machine
  c. To Display Quantity Available

Project Structure:
Model: 
a. Machine
b. Ingredients
c. Beverages

The core function of brewing service is in CoffeeMachine class. All coffee machine states are managed in CoffeeMachineStateManager class.

a. To Serve User Request : Specify your request object: Machine. Use method serveUserRequest with machine object.
b. To Refill Machine: Specify Beverage object with Ingredients quantity you want to refill. Use method refillMachine
c. To Display Quantity Available: Use method getAvailableIngredients


