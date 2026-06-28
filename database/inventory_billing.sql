DROP DATABASE IF EXISTS inventory_billing_sys;
CREATE DATABASE inventory_billing_sys;
USE inventory_billing_sys;

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL,
    category VARCHAR(50) NOT NULL
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL
);


CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price_at_sale DECIMAL(10,2) NOT NULL,

    FOREIGN KEY (order_id)
        REFERENCES orders(order_id),

    FOREIGN KEY (product_id)
        REFERENCES products(product_id)
);


INSERT INTO products (name, price, stock_quantity, category) VALUES
('Laptop', 60000.00, 30, 'Electronics'),
('Mouse', 500.00, 17, 'Accessories'),
('Monitor', 30000.00, 8, 'Electronics'),
('Keyboard', 1200.00, 20, 'Accessories'),
('Printer', 20000.00, 10, 'Electronics'),
('Speaker', 3500.00, 9, 'Accessories'),
('Pen Drive', 1000.00, 15, 'Storage Device');