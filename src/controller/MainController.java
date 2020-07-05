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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable {
    private ControllerUtililty initializeTable = new ControllerUtililty();

//Parts Section
    
    @FXML
    private TableView<Part> partsTable;   
    @FXML
    private TableColumn<Part, Integer> partInventoryLevel;
    @FXML
    private TableColumn<Part, Double> partCostPerUnit;
    @FXML
    private TableColumn<Part, String> partName;    
    @FXML
    private TableColumn<Part, Integer> partID;   
    @FXML
    private TextField searchPartText;

    @FXML
    private void handleSearchPartButton(ActionEvent event){
        //Is the user searching with an ic# or a part name??
        try{
            int identity = Integer.parseInt(searchPartText.getText());
            ObservableList<Part> idSearch = FXCollections.observableArrayList(Inventory.lookupPart(identity));
            partsTable.setItems(idSearch);
        }
        catch(NumberFormatException e){
           partsTable.setItems(Inventory.lookupPart(searchPartText.getText())); 
        }
        
        initializeTable.setTableCells(partID, partName, partInventoryLevel, partCostPerUnit);
    }
    
    @FXML
    private void handleAddPartButton(ActionEvent event) throws IOException{
        initializeTable.stageBuilder(event, "/view/AddPart.fxml");
    }
    
    @FXML
    private void handleModifyPartButton(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        loader.load();
        
        ModifyPartController MPC = loader.getController();
        MPC.sendPart(partsTable.getSelectionModel().getSelectedItem());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    
     @FXML
     private void handlePartDelete(ActionEvent event){
        Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
     }  

//Product Section     
     
    @FXML
    private TableView<Product> productTable;
    
    @FXML
    private TableColumn<Product, Integer> productInventory;

    @FXML
    private TableColumn<Product, Double> pricePerUnitProduct;

    @FXML
    private TableColumn<Product, String> productName;
    
    @FXML
    private TableColumn<Product, Integer> productId;
    
    @FXML
    private TextField productSearchText;
    
    @FXML
    private void handleProductSearch(ActionEvent event){
        //Is the user searching with an ic# or a product name??
        try{
            int identity = Integer.parseInt(productSearchText.getText());
            ObservableList<Product> idSearch = FXCollections.observableArrayList(Inventory.lookupProduct(identity));
            productTable.setItems(idSearch);
                    
        }
        catch(NumberFormatException e){
            productTable.setItems(Inventory.lookupProduct(productSearchText.getText()));
        }
        initializeTable.setTableCells(productId, productName, productInventory, pricePerUnitProduct);
    }
    
    @FXML
    private void handleProductAdd(ActionEvent event) throws IOException{
        initializeTable.stageBuilder(event, "/view/AddProduct.fxml");
    }
    
    @FXML
    private void handleProductModify(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
        loader.load();
        
        ModifyProductController MPC = loader.getController();
        MPC.sendProduct(productTable.getSelectionModel().getSelectedItem());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    
     @FXML
     private void handleProductDelete(ActionEvent event){
        Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
     }
     
    
    @FXML
    private void handleExitButton(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialize Parts table
        partsTable.setItems(Inventory.getAllParts());
        initializeTable.setTableCells(partID, partName, partInventoryLevel, partCostPerUnit);
        
        ////initialize Product table
        productTable.setItems(Inventory.getAllProducts());
        initializeTable.setTableCells(productId, productName, productInventory, pricePerUnitProduct);
    }    
    
}
