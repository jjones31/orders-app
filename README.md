# Orders App Test

## Step 1: Orders CLI and Orders Service
The orders-cli folder contains a Kotlin application in which a user can submit an order of apples and/or oranges. 

**Decisions**
* Keeping this as simple as possible, I interpreted the word "service" as you would find in Domain Driven Design or part of the Service Layer pattern described in Patterns of Enterprise Application Architecture.
* To keep things simple, I just used USD cents as the currency and formatted appropriately for display. I can avoid fun JVM double conversion fun and BigDecimals. 
* Output of the CLI is just the total
* Anything outside of "apple" or "orange" did not add to the price, throw an exception, or notify the user of the product not being valid.
* I created a domain model, however, I did not define any aggregate roots or entities. Everything besides the OrderService was a value object. 
