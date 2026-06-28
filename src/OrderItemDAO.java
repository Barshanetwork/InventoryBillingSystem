

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {
    
    public boolean addOrderItem(Connection con,OrderItem orderItem){
        try {
            //Connection con = DBConnection.getConnection();
            if(con==null) return false;

            String sql ="Insert into order_items(order_id,product_id,quantity,price_at_sale) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,orderItem.getOrderId());
            ps.setInt(2,orderItem.getProductId());
            ps.setInt(3,orderItem.getQuantity());
            ps.setDouble(4,orderItem.getPriceAtSale());

            int rowsAffected=ps.executeUpdate();
            
            ps.close();
            //con.close();
            return rowsAffected>0;


        } 
        catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public List<OrderItem> getOrderItemsById(int orderId){
        List<OrderItem> items = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "Select * from order_items where order_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,orderId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPriceAtSale(rs.getDouble("price_at_sale"));

                items.add(item);
                
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }
}
