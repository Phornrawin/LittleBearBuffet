# LittleBear Buffet

## content
- purpose
- Artifact
- Technical
- Problem
## Purpose
#### subject
01418471 Introduction to software engineering
#### project
- use for manage buffet restaurant 
- use for recieve order from customer quickly
## Artifact
### Customer
#### how to run with cmd
```
java -jar Customer.jar [table]
```
parameter
- table &nbsp;&nbsp;&nbsp;&nbsp; the number of customer table (default is 1)
#### how to use this artifact
1. Fill number of customer
2. Select buffet package <br>
There are 4 package available for today.
3. Now, you can order the food by click at the food's image.
4. If the item not available for now, the image will show with gray color and you cannot order it.
5. When you click the image, the order will show at table in Ordering tab, in your right.You can delete order by double-click at row in column.
6. If you are sure, click confirm button to send your order to kitchen. The ordered order will show in the Ordered tab.
7. Waiting for the food and enjoy your meal time!!
8. When you finish your meal and want to go, click "check payment" on top right to see the price and click "back to main".

### Cashier
#### how to run
```
java -jar Cashier.jar
```
#### how to use this artifact
1. select the table number to check bill.
2. The program will show the detail of payment.
3. You can fill the amount of receive money with numpad in program.
4. The program calculate the change money and show you.
5. Click "Pay" button in your right to confirm this payment.
