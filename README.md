# Orders App Test

## Step 1: Orders CLI and Orders Service
The orders-cli folder contains a Kotlin application in which a user can submit an order of apples and/or oranges. 

**Decisions**
* Keeping this as simple as possible, I interpreted the word "service" as you would find in Domain Driven Design or part of the Service Layer pattern described in Patterns of Enterprise Application Architecture.
* To keep things simple, I just used USD cents as the currency and formatted appropriately for display. I can avoid fun JVM double conversion fun and BigDecimals. 
* Output of the CLI is just the total
* Anything outside of "apple" or "orange" did not add to the price, throw an exception, or notify the user of the product not being valid.
* I created a domain model, however, I did not define any aggregate roots or entities. Everything besides the OrderService was a value object. 


## Step 2: Simple Offers
The OrderService now will calculate a discount based on the number of apples and oranges in the order. Apples are buy one get one free and oranges are buy 2 get the third free. 

**Decisions**
* Started to implement a chain of responsibility, but this didn't really fit the use case. I opted to just have a list of functions, each representing the strategy to calculate an offer. 
* I decided to make Offers a domain object instead of an application service. This was because the term "offer" was used in the description.

## Step 3: Notification Service
Incorporate a notification service that subscribes to events from the OrderService. Furthermore, the console should subscribe to notifications. 

**Decisions**
* I decided to continue with a monolithic application. Instead of splitting anything out into separate services, I utilzed the builtin Observer pattern. The seemed easiest given the time constraint as well as no scaling requirements. 
* I added an infra package to represent the infrastructure layer as you would in DDD or clean architecture. 