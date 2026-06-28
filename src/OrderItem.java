
public class OrderItem{
    private int orderItemId;
    private int orderId;
    private int productId;
    private int quantity;
    private double priceAtSale;

    public OrderItem(){

    }

    public OrderItem(int orderId,int productId,int quantity,double priceAtSale){
        this.orderId=orderId;
        this.productId=productId;
        this.quantity=quantity;
        this.priceAtSale=priceAtSale;
    }

    // Constructor used when retrieving data from the database
    public OrderItem(int orderItemId,int orderId,int productId,int quantity,double priceAtSale){
        this.orderItemId=orderItemId;
        this.orderId = orderId;
        this.productId=productId;
        this.quantity=quantity;
        this.priceAtSale=priceAtSale;
    }

    public OrderItem(int productId,int quantity,double priceAtSale){
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtSale = priceAtSale;
    }


    public int getOrderItemId(){
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId){
        this.orderItemId=orderItemId;
    }


    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int orderId){
        this.orderId=orderId;
    }


    public int getProductId(){
        return productId;
    }

    public void setProductId(int productId){
        this.productId=productId;
    }


    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }


    public double getPriceAtSale(){
        return priceAtSale;
    }

    public void setPriceAtSale(double priceAtSale){
        this.priceAtSale=priceAtSale;
    }

    @Override
    public String toString(){
        return "OrderItem{"+"OrderItemId"+orderItemId+
        "OrderId "+orderId+"ProductId "+productId+
        "Quantity "+quantity+"Price At Sale "
        +priceAtSale+'}';
    }
}