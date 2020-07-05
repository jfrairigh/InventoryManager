package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product{
    
    private static int productIdOffset = 0;
    
    private ObservableList <Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    public Product (){
        ++productIdOffset;
        id = productIdOffset;
    }
    
    public Product (int id, String name, double price, int stock, int min, int max){
        ++productIdOffset;
        this.id = productIdOffset;
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
    
    public void setPrice(double price){
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
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    public boolean deleteAssociatedPart(Part selectedAspart){
        return associatedParts.remove(selectedAspart);
    }
    
    public ObservableList <Part> getAllAssociatedParts(){
        return associatedParts;
    }

    @Override
    public String toString() {
        return "Product: \n" + "associatedParts: " + associatedParts + ", id: " + id + ", name: " + name + ", price: " + price + ", stock: " + stock + ", min: " + min + ", max: " + max;
    }
    
    
    
}
