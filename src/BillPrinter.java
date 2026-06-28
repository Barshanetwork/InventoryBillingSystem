

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class BillPrinter {
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();

    public  void printBill(int orderId){
        Order order = orderDAO.getOrderByID(orderId);
        if(order==null) {
            System.out.println("Order not found");
            return ;
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter=
            DateTimeFormatter.ofPattern("dd-MM--yy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        List<OrderItem> items = orderItemDAO.getOrderItemsById(orderId);
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tINVENTORY BILL");
        System.out.println("\n=====================================================");
        System.out.println("OrderId :"+orderId);
        System.out.println("Date :"+formattedDateTime);
        System.out.println("-----------------------------------------------------");
        System.out.println("Product\t\tQuantity\tPrice");
        for(OrderItem item : items){
            Product product = productDAO.getProductById(item.getProductId());
            System.out.println(product.getName()+"\t\t"+
                item.getQuantity()+"\t\t"+
                item.getPriceAtSale()
                );
        }
        System.out.println("\n--------------------------------------------------");
        System.out.println("Total Amount :"+order.getTotalAmount());
        System.out.println("\n=====================================================");
        System.out.println("\n\t\t\t THANKYOU!");
        System.out.println("\n=====================================================");

    }
}
