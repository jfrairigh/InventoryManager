package controller;

import model.Part;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPartController implements Initializable {
    ControllerUtililty stage = new ControllerUtililty();
    
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
    @FXML
    private TextField toBeDecidedText;    
    @FXML
    private Label toBeDecidedLabel;
    
    private boolean outsourcedSelected = true;

    @FXML
    void handleInHouseRadio(ActionEvent event) {
        toBeDecidedLabel.setText("Machine ID");
        toBeDecidedText.setPromptText("Machine ID");
        outsourcedSelected = false;
    }

    @FXML
    void handleOutsourcedRadio(ActionEvent event) {
        toBeDecidedLabel.setText("Company Name");
        toBeDecidedText.setPromptText("Company Name");
        outsourcedSelected = true;
    }
    
    @FXML
    void handleSaveButton(ActionEvent event) throws Exception {
        //retrieving data from known text fields
        String itemName = name.getText();
        
        try{
            double cost = Double.parseDouble(price.getText());
            int maximum = Integer.parseInt(max.getText());
            int minimum = Integer.parseInt(min.getText());
            int stock = Integer.parseInt(inventory.getText());
            
            //check logic of fields before saving
            boolean readyToSave;
            readyToSave = stage.checkInventoryParameter(maximum, minimum, stock);
            
            if(readyToSave){
            //Deciding part type, creating new objects and data type accordingly, adding part to inventory
                if(outsourcedSelected){     
                    String company = toBeDecidedText.getText();
                    Inventory.addPart(new Outsourced (itemName, cost, stock, minimum, maximum, company));
                }
                else{
                    int machineId = Integer.parseInt(toBeDecidedText.getText());
                    Inventory.addPart(new InHouse (itemName, cost, stock, minimum, maximum, machineId));
                }
             
                //Moving back to Main Screen after saving
                stage.stageBuilder(event, "/view/Main.fxml");
            }
        }
        catch(NumberFormatException e){
            stage.alertNumberFormatException(outsourcedSelected);
        }
        
    }
    
    @FXML 
    public void handleCancelButton (ActionEvent event) throws IOException{
        stage.stageBuilder(event, "/view/Main.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       id.setText(String.valueOf(Part.getNextId()));
    }    
    
}
