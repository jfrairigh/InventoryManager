package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
     
    public static void addPart(Part newPart){
        allParts.add(newPart);
     }
     
    public static void addProduct(Product newProduct){
         allProducts.add(newProduct);
     }
     
    public static Part lookupPart(int id){
        for(Part part: allParts){
            if(part.getId() == id){
                return part;
            }
        }
        return null;
     }
   
    public static Product lookupProduct(int id){
        for(Product product: allProducts){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
     }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList <Part> foundParts = FXCollections.observableArrayList();
        for(Part part: allParts){
           if(part.getName().contains(partName)){
               foundParts.add(part);
           }
        }
        return foundParts;
       }
    
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for(Product product: allProducts){
           if(product.getName().contains(productName)){
               foundProducts.add(product);
           }
        }
        return foundProducts;
       }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    
    public static void updatePart(Part oldPart, Part newPart){
        int index = allParts.indexOf(oldPart);
        allParts.set(index, newPart);
    }
    
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    
    public static void updateProduct(Product oldProduct, Product newProduct){
        int index = allProducts.indexOf(oldProduct);
        allProducts.set(index, newProduct);
    }
    
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
     
