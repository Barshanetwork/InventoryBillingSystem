# Inventory Billing System

A Java console-based Inventory Billing System developed using **Java, JDBC, and MySQL**. The application allows users to manage products, place customer orders, automatically update inventory, generate bills, and view order history.

## Features

- Add new products
- Automatically update stock for existing products
- View all available products
- Place orders with multiple products
- Automatic stock deduction after successful orders
- Generate detailed bills
- View complete order history
- Transaction management using Commit and Rollback
- JDBC connectivity with MySQL database

## Technologies Used

- Java
- JDBC
- MySQL
- Object-Oriented Programming (OOP)

## Project Structure

```
InventoryBillingSystem
│
├── src
│   ├── InventoryBillingSystem.java
│   ├── DBConnection.java
│   ├── Product.java
│   ├── ProductDAO.java
│   ├── Order.java
│   ├── OrderDAO.java
│   ├── OrderItem.java
│   ├── OrderItemDAO.java
│   ├── BillingService.java
│   └── BillPrinter.java
│
├── database
│   └── inventory_billing.sql
│
└── README.md
```

## Database Setup

1. Open MySQL Workbench or MySQL Command Line.
    Download : https://dev.mysql.com/downloads/installer/
2. Run the SQL file:
    ```sql
    source database/inventory_billing.sql
3. This will automatically:

   - Create the database
   - Create all required tables
   - Insert sample products
4. Download mysql-connector-j-9.7.0.jar
    https://dev.mysql.com/downloads/connector/j/?os=26

## Configuration

Update the database credentials inside `DBConnection.java` according to your MySQL installation.

```java
private static final String url = "jdbc:mysql://localhost:3306/inventory_billing_sys";
private static final String username = "root";
private static final String password = "your_password";
```

## Running the Project

1. Compile all Java files :
    javac -cp ".;mysql-connector-j-9.7.0.jar" src\*.java  
2. Run Project :
    java -cp "src;mysql-connector-j-9.7.0.jar" InventoryBillingSystem


## Future Improvements

- Update Product Details
- Delete Product
- Search Product
- User Authentication
- Export Bills as PDF

## Author

Barsha
