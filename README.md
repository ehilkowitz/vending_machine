# vendingmachine
Vending Machine App - CLI Driven Vending Machine - Eric Hilkowitz

Vending Machine Application

The vending machine dispenses beverages, candy, chips, and gum.

Each vending machine item has a Name and a Price. As well as Qty Remaining.

A main menu must display when the software runs, presenting the following options:
(1) Display Vending Machine Items
(2) Purchase
(3) Exit

The vending machine inventory is stocked via an input file when the vending machine starts.

● The vending machine is automatically restocked each time the application runs.

● When the customer selects "(1) Display Vending Machine Items", they're presented with
a list of all items in the vending machine with its quantity remaining:

○ Each vending machine product has a slot identifier and a purchase price.

○ Each slot in the vending machine has enough room for 5 of that product.

○ A product that has run out will indicate that it is SOLD OUT.

![Display_Items_Menu](https://user-images.githubusercontent.com/96846431/160197649-54cb221c-eafa-42c7-8c23-f427f48b53ce.JPG)




When the customer selects
"(2) Purchase", they are guided through the purchasing process menu:
(1) Feed Money
(2) Select Product
(3) Finish Transaction

Customer must first “(1) Feed Money”, selecting from $1, $5, and $10

"(2) Select Product" allows the customer to select a product to purchase.

○ Shows the list of products available and allows the customer to enter a code to select
an item. (A1-D4)

○ If the product code does not exist, the customer is informed and returned to the
Purchase menu.

○ If a product is sold out, the customer is informed and returned to the Purchase menu.

○ If a valid product is selected, it is dispensed to the customer.

○ Dispensing an item prints the item name, cost, and the money remaining. Dispensing
also returns a message depending on item type, i.e. “Glug Glug”.

○ After the product is dispensed, the machine must update its balance accordingly and
return the customer to the Purchase menu.



![Feed_Money_Screen](https://user-images.githubusercontent.com/96846431/160197692-bc5fc3dc-f211-455e-9f4a-6e3b9fbb3485.JPG)


Selecting

"(3) Finish Transaction" allows the customer to complete the transaction and provides change.
Takes customers back to the Main Menu.
Transaction Logger

![Transaction_Log](https://user-images.githubusercontent.com/96846431/160197720-c634d9e8-3ee3-4278-8c0d-5a614b7b495a.JPG)


Vending Machine Data File
The input file that stocks the vending machine products is a pipe | delimited file. Each line is a
separate product in the file and follows the below format:
Column Name Description
Slot Location The slot location in the vending machine where the product is
set.


Product Name The display name of the vending machine product.
Price The purchase price for the product.
Type The product type for this row.
For example:
A1|Potato Crisps|3.05|Chip
B1|Moonpie|1.80|Candy
B2|Cowtales|1.50|Candy
C1|Cola|1.25|Drink
Vendingmachine.csv. Additional inventory files will be added to rotate in new items.


Sales Report
The output sales report file is also pipe-delimited for consistency. And can be accessed with a
secret key input of pressing 4 on the main menu. This will print out a txt file with Sales by Item.

![Daily_Sales_Report](https://user-images.githubusercontent.com/96846431/160197755-c28740e0-eff3-439c-a3b0-7852201e1418.JPG)

![image](https://user-images.githubusercontent.com/96846431/160198083-24d18fb3-4a89-44a8-8276-c3a0b667e13a.png)


Unit Testing Developed. Sample below, Coinbox unit test. 
///////////////////////////////////////////////////////////////
![image](https://user-images.githubusercontent.com/96846431/160305024-b60f647e-2482-41a3-bff1-6a64544b12f9.png)


