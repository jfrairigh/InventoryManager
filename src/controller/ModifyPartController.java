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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ModifyPartController implements Initializable {
    
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
    @FXML
    private RadioButton inHouseRadio;    
    @FXML
    private RadioButton outsourcedRadio;
    
    private boolean outsourcedSelected;

    Part oldPart;
    public void sendPart(Part part){
        
        id.setText(String.valueOf(part.getId()));
        name.setText(String.valueOf(part.getName()));
        inventory.setText(String.valueOf(part.getStock()));
        price.setText(String.valueOf(part.getPrice()));
        max.setText(String.valueOf(part.getMax()));
        min.setText(String.valueOf(part.getMin()));
        oldPart = part;
        
        if(part instanceof Outsourced ){
           outsourcedRadio.setSelected(true);
           outsourcedSelected = true;
           toBeDecidedText.setText(String.valueOf(((Outsourced) part).getCompanyName()));
           toBeDecidedLabel.setText("Company Name");
        }
        else{
            inHouseRadio.setSelected(true);
            outsourcedSelected = false;
            toBeDecidedText.setText(String.valueOf(((InHouse)part).getMachineID()));
            toBeDecidedLabel.setText("Machine ID");
        }
    }

    @FXML
    void handleInHouseRadio (ActionEvent event){
        toBeDecidedLabel.setText("Machine ID");
        toBeDecidedText.setPromptText("Machine ID");
        outsourcedSelected = false;
    }
    
    @FXML
    void handleOutsourcedRadio (ActionEvent event){
        toBeDecidedLabel.setText("Company Name");
        toBeDecidedText.setPromptText("Company Name");
        outsourcedSelected = true;
    }
    
    @FXML
    void handleSaveButton(ActionEvent event) throws IOException {
        //retrieving data from known text fields
        try{
            int identity = Integer.parseInt(id.getText());
            String itemName = name.getText();
            int stock = Integer.parseInt(inventory.getText());
            double cost = Double.parseDouble(price.getText());
            int maximum = Integer.parseInt(max.getText());
            int minimum = Integer.parseInt(min.getText());

            //check logic of fields before saving
            boolean readyToSave;
            readyToSave = stage.checkInventoryParameter(maximum, minimum, stock);
            
            if(readyToSave){
                //Deciding part type, creating new objects and data type accordingly, adding part to inventory
                if(outsourcedSelected){     
                    String companyOrMachine = toBeDecidedText.getText();
                    Inventory.updatePart(oldPart, new Outsourced (identity, itemName, cost, stock, minimum, maximum, companyOrMachine));
                }
                else{
                    int companyOrMachine = Integer.parseInt(toBeDecidedText.getText());
                    Inventory.updatePart(oldPart, new InHouse (identity, itemName, cost, stock, minimum, maximum, companyOrMachine));
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
    void handleCancelButton(ActionEvent event) throws IOException{
        stage.stageBuilder(event, "/view/Main.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
