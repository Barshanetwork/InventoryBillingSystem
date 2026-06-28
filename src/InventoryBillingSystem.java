
//Menu and User Print
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryBillingSystem {
    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();
    BillingService billingService = new BillingService();
    BillPrinter billPrinter = new BillPrinter();
    //OrderHistory orderHistory = new OrderHistory();

    while(true){

        System.out.println("\n===== Inventory Billing System =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Place Order");
        System.out.println("4. View Order History");
        System.out.println("5. Exit");

        System.out.print("Enter Choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        // switch-case will come here
        switch (choice) {
            case 1:
                System.out.print("Enter Product name :");
                String name =sc.nextLine();
                System.out.print("Enter Category :");
                String category = sc.nextLine();
                System.out.print("Enter price :");
                double price = sc.nextDouble();
                System.out.print("Enter Quantity:");
                int stock = sc.nextInt();
                Product existing = productDAO.findProduct(name, category);
                if(existing == null){
                Product product = new Product(
                    name,
                    category,
                    price,
                    stock
                );
                boolean added = productDAO.addProduct(product);
                if(added) System.out.println("Product added Successfully");
                else System.out.println("Failed to Add Product");
            }
            else{
                int newStock = existing.getStockQuantity()+stock;
                boolean update=productDAO.updateStock(existing.getProductId(),price,newStock);

                if(update) System.out.println("Stock Updated");
                else System.out.println("Failed to Update Stock");

            }
                
                break;
            
            case 2 :
                System.out.printf("%-5s %-15s %-12s %-8s %-20s%n","ID","Name","Price","Stock","Category");
                List<Product> products = productDAO.getAllProducts();
                for(Product product1 : products){
                    System.out.printf("%-5d %-15s %-12.2f %-8d %-20s%n",
                        product1.getProductId(),
                        product1.getName(),
                        product1.getPrice(),
                        product1.getStockQuantity(),
                        product1.getCategory());
                    
                }
                break;

            case 3:
                List<OrderItem> orderItems= new ArrayList<>();
                while(true){
                    System.out.print("Enter product Id :");
                    int productId=sc.nextInt();

                    Product product2 = productDAO.getProductById(productId);
                    if(product2== null){
                        System.out.println("Product does not exist");
                        continue;
                    }

                    System.out.print("Enter Quantity:");
                    int quantity=sc.nextInt();

                    if(quantity>product2.getStockQuantity()){
                        System.out.println("Insufficient Stock!");
                        continue;
                    }

                    OrderItem item = new OrderItem(
                        productId,
                        quantity,
                        product2.getPrice()
                    );
                    orderItems.add(item);

                    System.out.println("1.Add more items");
                    System.out.println("2.Finish Order");
                    System.out.print("Enter your choice :");
                    int ch = sc.nextInt();
                    if(ch==1) continue;
                    else if(ch==2){
                        break;
                    }
                    else System.out.println("Invalid choice");
                }

                int orderId = billingService.placeOrder(orderItems);
                    if(orderId>0){
                        System.out.println("Order Placed successfully");

                        billPrinter.printBill(orderId);
                    }
                    else System.out.println("Order Failed");

                break;

            case 4:
                List<Order> orders = orderDAO.getAllOrders();
                System.out.println("\t\tORDER HISTORY");
                System.out.println("Order ID\t\tDate\t\tTotal Amount");
                System.out.println("------------------------------------------------");
                for(Order order : orders){
                System.out.println(
                    order.getOrderId()+"\t\t"+
                    order.getOrderDate()+"\t\t"+
                    order.getTotalAmount()
                    );
                }
                System.out.print("Enter Order Id to print the bill(0 to go back):");
                int id = sc.nextInt();
                if(id!=0){
                    billPrinter.printBill(id);
                }

                break;
            
            case 5:
                System.out.println("Thank you for using Inventory Billing System!");
                sc.close();
                return;

            default:
                System.out.println("Invalid choice");
                continue;
            }

        }
    }
}
