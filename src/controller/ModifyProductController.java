package controller;

import model.Part;
import model.Product;
import model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ModifyProductController implements Initializable {
    
    ControllerUtililty stage = new ControllerUtililty();
    Product oldProduct;
    
//Tables and Text Fields
    
    //Product Information Left Side of Screen    
    @FXML
    private TextField id;
    @FXML
    private TextField name; 
    @FXML
    private TextField inventory;
    @FXML
    private TextField price;    
    @FXML
    private TextField max;    
    @FXML
    private TextField min;
    
    //Table for all parts in inventory
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Part> inventoryPartsTable;
    @FXML
    private TableColumn<Part, Integer> inventoryPartId;
    @FXML
    private TableColumn<Part, String> inventoryPartName;
    @FXML
    private TableColumn<Part, Integer> inventoryPartStock;
    @FXML
    private TableColumn<Part, Double> inventoryPartPrice;    


    //Table for associate parts to this product
    @FXML
    private TableView<Part> associatedPartTable;
    @FXML
    private TableColumn<Part, Integer> associatedPartId;
    @FXML
    private TableColumn<Part, String> associatedPartName;
    @FXML
    private TableColumn<Part, Integer> associatedPartStock;
    @FXML
    private TableColumn<Part, Double> associatedPartPrice;  
    
// Action Events
    @FXML
    void handleSearch(ActionEvent event) {
        try{
            int identity = Integer.parseInt(searchTextField.getText());
            ObservableList<Part> idSearch = FXCollections.observableArrayList(Inventory.lookupPart(identity));
            inventoryPartsTable.setItems(idSearch);
        }
        catch(NumberFormatException e){
            inventoryPartsTable.setItems(Inventory.lookupPart(searchTextField.getText()));
        }
    }

    @FXML
    void handleAdd(ActionEvent event) {
        oldProduct.addAssociatedPart(inventoryPartsTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void handleDelete(ActionEvent event) {
        oldProduct.deleteAssociatedPart(associatedPartTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void handleSave(ActionEvent event) throws IOException {
        
        String itemName = null;
        int stock = 0;
        double cost = 0;
        int maximum = 0;
        int minimum= 0;
        boolean readyToSave;        
        
        try{
            readyToSave = stage.checkForRequiredFields(name, price);
            
            if(readyToSave){            
                //retrieving data from known text fields
                itemName = name.getText();
                if(inventory.getText().isEmpty()){
                    stock = 0; // Zero is the default inventory amount
                }
                else{
                    stock = Integer.parseInt(inventory.getText());
                }
                cost = Double.parseDouble(price.getText());
                maximum = Integer.parseInt(max.getText());
                minimum = Integer.parseInt(min.getText());

                //check logic of fields before saving
                readyToSave = stage.checkInventoryParameter(maximum, minimum, stock);

                if(readyToSave){            
                    //setting Product fields
                    oldProduct.setName(itemName);
                    oldProduct.setStock(stock);
                    oldProduct.setPrice(cost);
                    oldProduct.setMax(maximum);
                    oldProduct.setMin(minimum);

                    //Moving back to Main Screen after saving
                    stage.stageBuilder(event, "/view/Main.fxml");
                }
            }
        }
        catch(NumberFormatException e){
            stage.alertNumberFormatException(true);
        }
    }
 
    
    @FXML 
    public void handleCancel (ActionEvent event) throws IOException{
        stage.stageBuilder(event, "/view/Main.fxml");
    }
    
    @FXML
    void handleCancelButton(ActionEvent event) throws IOException{
        stage.stageBuilder(event, "/view/Main.fxml");
    }
            
    // The caller will send the initializiation information for the selected product.
    public void sendProduct(Product product){
        oldProduct = product;
        
        id.setText(String.valueOf(product.getId()));
        name.setText(String.valueOf(product.getName()));
        inventory.setText(String.valueOf(product.getStock()));
        price.setText(String.valueOf(product.getPrice()));
        max.setText(String.valueOf(product.getMax()));
        min.setText(String.valueOf(product.getMin()));
        
        associatedPartTable.setItems(oldProduct.getAllAssociatedParts());
        stage.setTableCells(associatedPartId, associatedPartName, associatedPartStock, associatedPartPrice);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       // Initialize inventory parts table
       inventoryPartsTable.setItems(Inventory.getAllParts());
       stage.setTableCells(inventoryPartId, inventoryPartName, inventoryPartStock, inventoryPartPrice);

    }    
    
}
