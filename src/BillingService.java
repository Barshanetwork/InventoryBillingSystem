
//For Placing Orders
import java.sql.Connection;
import java.util.List;


public class BillingService {

    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();

    public int placeOrder(List<OrderItem> orderItems) {

        Connection con = null;

        try {
            con = DBConnection.getConnection();

            if (con == null)
                return -1;

            con.setAutoCommit(false);

            double totalAmount = 0;

            // Step 1: Validate products and calculate total
            for (OrderItem item : orderItems) {

                Product product =
                        productDAO.getProductById(item.getProductId());

                if (product == null) {
                    con.rollback();
                    return -1;
                }

                if (product.getStockQuantity() < item.getQuantity()) {
                    con.rollback();
                    return -1;
                }

                totalAmount +=
                        item.getPriceAtSale() * item.getQuantity();
            }

            // Step 2: Create order
            Order order = new Order(totalAmount);

            int orderId = orderDAO.addOrder(con, order);

            if (orderId < 0) {
                con.rollback();
                return -1;
            }

            // Step 3: Insert order items
            for (OrderItem item : orderItems) {

                item.setOrderId(orderId);

                boolean inserted =
                        orderItemDAO.addOrderItem(con, item);

                if (!inserted) {
                    con.rollback();
                    return -1;
                }
            }

            // Step 4: Update stock
            for (OrderItem item : orderItems) {

                Product product =
                        productDAO.getProductById(item.getProductId());

                int newStock =
                        product.getStockQuantity()
                        - item.getQuantity();

                boolean updated =
                        productDAO.updateStock(
                                con,
                                product.getProductId(),
                                product.getPrice(),
                                newStock
                        );

                if (!updated) {
                    con.rollback();
                    return -1;
                }
            }

            // Step 5: Save everything permanently
            con.commit();

            return orderId;

        } catch (Exception e) {

            try {
                if (con != null)
                    con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
            return -1;

        } finally {

            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
    }
}