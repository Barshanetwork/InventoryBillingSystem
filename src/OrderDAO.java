
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO {
    public int addOrder(Connection con,Order order){
        try {
           // Connection con = DBConnection.getConnection();
            if(con==null) return -1;
            String sql ="Insert into orders(total_amount) values(?)";

            PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,order.getTotalAmount());
            
            int rowsAffected =ps.executeUpdate();

            if(rowsAffected>0){
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                int orderId= rs.getInt(1);//1 means return the 1st column
                rs.close();
                ps.close();
                //con.close();
                return orderId;
            }
            rs.close();
        }
            
            
            ps.close();
            //con.close();
            
            return -1;
           


        } catch(SQLException e){
            e.printStackTrace();
            }

        return -1;
    }


    public Order getOrderByID(int orderId){
        try {
            Connection con = DBConnection.getConnection();
            String sql ="Select * from orders where order_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,orderId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Order order = new Order();
                order.setOrderId(orderId);
                order.setTotalAmount(rs.getDouble("total_amount"));
                rs.close();
                ps.close();
                con.close();
                return order;
            }
            rs.close();
            ps.close();
            con.close();

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }



    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        try{
        Connection con = DBConnection.getConnection();

        String sql = "Select * from orders";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            int orderId = rs.getInt("order_id");
            Timestamp orderDate = rs.getTimestamp("order_date");
            double totalAmount = rs.getDouble("total_amount");

            Order order = new Order(
                orderId,
                orderDate,
                totalAmount
            );
            orders.add(order);

        }
        rs.close();
        ps.close();
        con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return orders;

    }
}



