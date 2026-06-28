

import java.sql.Timestamp;

public class Order {
    
    private int orderId;
    private Timestamp orderDate;
    private double totalAmount;

    //for creating empty object
    public Order(){

    }

    //for inserting
    public Order(double totalAmount){
        this.totalAmount = totalAmount;
    }
    

    //for reading from DB
    public Order(int orderId,Timestamp orderDate, double totalAmount){
        this.orderId=orderId;
        this.orderDate = orderDate;
        this.totalAmount=totalAmount;
    }


    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;

    }


    public Timestamp getOrderDate(){
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate){
        this.orderDate=orderDate;
    }


    public double getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount){
        this.totalAmount=totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
            "OrderId=" + orderId +
            ", OrderDate='" + orderDate  +
            ", TotalAmount=" + totalAmount +
            '}';
    }
}
