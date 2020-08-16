
# Project name: Coffee-Machine
Introduction : Project contains brewing service to serve coffee requests. It exposes 3 functions : <br>
  a. To Serve User Request <br>
  b. To Refill Machine <br>
  c. To Display Quantity Available <br>

Project Structure:<br>
Model: <br>
a. Machine <br>
b. Ingredients <br>
c. Beverages <br>
<br>
The core function of brewing service is in CoffeeMachine class. All coffee machine states are managed in CoffeeMachineStateManager class.<br>
<br>
a. To Serve User Request : Specify your request object: Machine. Use method serveUserRequest with machine object.<br>
b. To Refill Machine: Specify Beverage object with Ingredients quantity you want to refill. Use method refillMachine.<br>
c. To Display Quantity Available: Use method getAvailableIngredients.<br>


