package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

class ControllerUtililty{
    
    Alert alert = new Alert(Alert.AlertType.ERROR);

    public void stageBuilder(ActionEvent event, String resourceFile)throws IOException{  
        Parent parent = FXMLLoader.load(getClass().getResource(resourceFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void setTableCells(TableColumn id, TableColumn name, TableColumn stock,TableColumn price){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
    
    public void alertNumberFormatException(boolean defaultMessage){
 
        alert.setTitle("Numeric Entry Required");
        if(defaultMessage){
            alert.setContentText("Please use only numeric values for Price/Cost, Inv, Max, and Min fields." );
        }
        else{
            alert.setContentText("Please use only numeric values for ID, Price/Cost, Inv, Max, Min, and Machine ID fields." );
        }
        alert.setWidth(650);
        alert.showAndWait();
    }
    
    public boolean checkInventoryParameter(int max, int min, int inv){
        
        alert.setTitle("Inventory Outside Min and Max Parameters");
        if(inv < min || inv > max){
            alert.setContentText("The number of items in inventory cannot be less\n"
                               + " than the minimum or larger than the maximum amount.");
            alert.setWidth(550);
            alert.showAndWait(); 
            return false;
        }
        return true;
    }
    
    public boolean checkForRequiredFields(TextField name, TextField price){
        
        alert.setTitle("Required Field");
        if(name.getText().isEmpty()){
            alert.setContentText("Please enter a product name.");
            alert.showAndWait(); 
            return false;
        }
        if(price.getText().isEmpty()){
            alert.setContentText("Please enter a product price.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
