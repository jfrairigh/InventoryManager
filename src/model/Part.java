package model;

public abstract class Part {
    
    private static int partIdOffset = 0;
    
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    public Part (String name, double price, int stock, int min, int max){
        ++partIdOffset;
        this.id = partIdOffset;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    //This constructor is used when "modifying" a part in the inventory.
    public Part (int id, String name, double price, int stock, int min, int max){
        this.id= id; //allows "modified" part to keep the same id#
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    public void setId(int id){

    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    
    public void setMin(int min){
        this.min = min;
    }
    
    public void setMax(int max){
        this.max = max;
    }
    
    public int getId(){
        return id;
    }
    
    public static int getNextId(){
        return partIdOffset + 1;
    }
    
    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getStock(){
        return stock;
    }
    
    public int getMin(){
        return min;
    }
    
    public int getMax(){
        return max;
    }

    @Override
    public String toString() {
        return "Part:\n" + "id: " + id + ", name: " + name + ", price: " + price + ", stock: " +
                stock + ", min: " + min + ", max: " + max + '\n';
    }
    
    
    
}
