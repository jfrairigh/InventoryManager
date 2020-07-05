package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {   

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Outsourced part1 = new Outsourced("washer", 0.99, 1, 1, 100, "Key Stone");
        InHouse part2 = new InHouse("nut", 0.99, 1, 1, 100, 2);
        Product product1 = new Product(1,"shelf", 10.99, 1, 1, 100);
        Product product2 = new Product(1,"chair", 21.25, 1, 1, 100);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
       
        launch(args);
    }
}
