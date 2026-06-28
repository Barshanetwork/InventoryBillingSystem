
public class Product {
    private int productId;
    private String name;
    private double price;
    private int stockQuantity;
    private String category;

    public Product() {

    }

    public Product(String name,
        String category,
        double price,
        int stockQuantity) {

        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    public Product(int productId,
        String name,
        String category,
        double price,
        int stockQuantity) {

        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductId(){
        return productId;
    }

    public void setProductId(int productId){
        this.productId=productId;
    }



    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }



    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category=category;
    }



    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }


    public int getStockQuantity(){
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity){
        this.stockQuantity=stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
            "productId=" + productId +
            ", name='" + name + '\'' +
            ", category='" + category + '\'' +
            ", price=" + price +
            ", stockQuantity=" + stockQuantity +
            '}';
    }
}
