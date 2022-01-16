package sample.Model;

/**
 * @author Carlos Hernandez
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    /**
     *
     * @param primaryStage is declared and initializes the application
     * @throws Exception overrides any exceptions that may interrupt the start process.
     */

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     *
     * @param args accepts any arguments that may be passed
     * Some values are initialized in the application to demonstrate the functionality of the application.
     */


    public static void main(String[] args) {
        Part part1 = new InHouse(0, "wheel", 15.00, 30, 1, 100, 7) {
        };
        Part part2 = new InHouse(0, "seat", 25.00, 5,1,300, 7){};
        Part part3 = new InHouse(0, "grip", 10.00, 200,1,999,33){};
        Part part4 = new InHouse(0, "spoke", 50.00, 20,1,25,968){};
        Part part5 = new InHouse(0, "chain", 20.00, 70,1,100,3){};
        Part part6 = new OutSource(0, "frame", 20.00, 3,1,16,"Custom Frames"){};
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);

        Product product1 = new Product(0, "Snowboard",15.00,30,1,90);
        Product product2 = new Product(0, "Hoverboard", 9999.99, 1,1,1);
        Product product3 = new Product(0, "Bike", 150.24, 30,1,40);
        Product product4 = new Product(0, "Skates", 15.00, 30,1,60);
        Product product5 = new Product(0, "Knee Pads", 5.00, 70,1,100);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);








        launch(args);
    }


}
