
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductDAO {
    
    public boolean addProduct(Product product){
        try{
            Connection con = DBConnection.getConnection(); 
            if(con==null) return false;

        
            String sql="Insert into products(name,price,stock_quantity,category) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setInt(3, product.getStockQuantity());
            ps.setString(4, product.getCategory());

            int rowsAffected=ps.executeUpdate();   

            ps.close();
            con.close();

            return rowsAffected>0;

        }
        catch(SQLException e){
            e.printStackTrace();
            
        }
         return false;
    }

    public Product findProduct(String name,String Category){
        try {
            Connection con = DBConnection.getConnection();

            String sql="Select * from products where name=? and category=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, Category);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int productid=rs.getInt("product_id");
                String productname = rs.getString("name");
                double price= rs.getDouble("price");
                int stockQuantity = rs.getInt("stock_quantity");
                String category = rs.getString("category");

                Product product = new Product(
                    productid,
                    productname,
                    category,
                    price,
                    stockQuantity
                );

                rs.close();
                ps.close();
                con.close();

                return product;

            }
            rs.close();
            ps.close();
            con.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }


    public List<Product> getAllProducts(){
         List<Product> products = new ArrayList<>();
        try{
            Connection con =DBConnection.getConnection();
            String sql="Select * from products";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                int productid=rs.getInt("product_id");
                String name = rs.getString("name");
                double price= rs.getDouble("price");
                int stockQuantity = rs.getInt("stock_quantity");
                String category = rs.getString("category");

                Product product = new Product(
                    productid,
                    name,
                    category,
                    price,
                    stockQuantity
                );
                products.add(product);
                
            }

            rs.close();
            ps.close();
            con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return products;

        
    }


    public Product getProductById(int productId){
        try{
            Connection con = DBConnection.getConnection();
            String sql="Select * from products where product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int productID=rs.getInt("product_id");
                String name = rs.getString("name");
                double price= rs.getDouble("price");
                int stockQuantity = rs.getInt("stock_quantity");
                String category = rs.getString("category");

                Product product = new Product(
                    productID,
                    name,
                    category,
                    price,
                    stockQuantity
                );
                rs.close();
                ps.close();
                con.close();
                return product;
            }
            rs.close();
            ps.close();
            con.close();
            
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean updateStock(Connection con,int productId ,double price, int newStock){
        try {
            //Connection con = DBConnection.getConnection();//as this will be getting from billingService
            String sql="Update products set price=?,stock_quantity=? where product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2,newStock);
            ps.setDouble(1, price);
            ps.setInt(3,productId);

            int rowsAffected = ps.executeUpdate();
            ps.close();
            return rowsAffected > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    
     public boolean updateStock(int productId ,double price, int newStock){
        try {
            Connection con = DBConnection.getConnection();//as this will be getting from billingService
            String sql="Update products set price=?,stock_quantity=? where product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2,newStock);
            ps.setDouble(1, price);
            ps.setInt(3,productId);

            int rowsAffected = ps.executeUpdate();
            ps.close();
            con.close();
            return rowsAffected > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
