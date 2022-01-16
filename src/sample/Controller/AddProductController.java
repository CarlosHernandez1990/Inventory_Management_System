package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Model.Inventory;
import sample.Model.Part;
import sample.Model.Product;

import java.io.IOException;
import java.util.Objects;

import static sample.Model.Inventory.*;

/**
 * @author Carlos Hernadez
 *
 * The AddProductController is in charge of controlling the add product page.
 */

public class AddProductController {

    @FXML
    AnchorPane addProductPage;
    @FXML
    Button save;
    @FXML
    Button cancel;
    @FXML
    Button add;
    @FXML
    TableView partTableView;
    @FXML
    TableView prodTableView;
    @FXML
    TableColumn idCol;
    @FXML
    TableColumn nameCol;
    @FXML
    TableColumn priceCol;
    @FXML
    TableColumn stockCol;
    @FXML
    TableColumn prodIdCol;
    @FXML
    TableColumn prodNameCol;
    @FXML
    TableColumn prodStockCol;
    @FXML
    TableColumn prodPriceCol;
    @FXML
    TextField idField;
    @FXML
    TextField nameField;
    @FXML
    TextField stockField;
    @FXML
    TextField priceField;
    @FXML
    TextField minField;
    @FXML
    TextField maxField;
    @FXML
    TextField companyNameField;
    @FXML
    Button delete;
    @FXML
    TextField idSearchBox;

    Product associatedProd;

    /**
     * This method initializes the page by auto-populating the data of parts and associated parts in the tableviews.
     * @throws Exception minimizes the risk of populating errors
     *
     * A null pointer exception was populating by the initialization code because of the chance of the associatedProd
     * property being null. I fixed this by adding in an If statement that only populated the table view if the associatedProd
     * property was not null.
     */

    public void initialize() throws Exception {


        partTableView.setItems(Inventory.getAllParts());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));


        if (associatedProd != null) {
            nameField.setText(associatedProd.getName());
            idField.setText(new Integer(associatedProd.getId()).toString());
            stockField.setText(new Integer(associatedProd.getStock()).toString());
            maxField.setText(new Integer(associatedProd.getMax()).toString());
            minField.setText(new Integer(associatedProd.getMin()).toString());
            priceField.setText(new Integer((int) associatedProd.getPrice()).toString());

        }
    }

    /**
     * The addHandler is an action event method that is controlled by users pressing the add button. When users select a
     * row from the top table and press submit, the selected row will then be added to the bottom table. If the
     * user presses the add button without having selected a row then an alert will be populated.
     *
     * @throws IOException to minimize risk of errors.
     */


    public void addHandler() throws IOException {
        Part parts = (Part) partTableView.getSelectionModel().getSelectedItem();
        if(parts == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a row.");
            alert.showAndWait();
        }

        if (associatedProd == null) {
            try {
                saveProd();
            }
            catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter correct values.");
                alert.showAndWait();

            }

        }

        if (associatedProd != null) {
            associatedProd.addAssociatedPart(parts);
            prodTableView.setItems(associatedProd.getAllAssociatedPart());
            prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        }


    }

    /**
     *  The deleteHandler is an action event method that is controlled by users selecting a row on the bottom table and then
     *  pressing delete. The row selected in the bottom table will then be removed. An error will be populated if users
     *
     *
     * @throws IOException to minimize the chances of an error.
     */

    public void deleteHandler() throws IOException {
        Part selected = (Part) prodTableView.getSelectionModel().getSelectedItem();
        if(selected == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row");
            alert.show();
        }
        if (associatedProd != null) {
            associatedProd.deleteAssociatedPart(selected);
        }

    }

    /**
     *  The cancelHandler is an action event method that is controlled by the cancel button. When users press the cancel
     *  button they will be directed back to the main page.
     *
     * @throws Exception to minimize the chances of an error.
     */

    public void cancelHandler() throws Exception {
        Inventory.deleteProduct(associatedProd);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/Main.fxml")));
        Stage window = (Stage) cancel.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    /**
     *  The saveHandler is an action event method that saves a product to the Product class. This method calls on another
     *  method to complete this action. It is initiated by users pressing the save buttons.
     * @throws Exception
     */

    public void saveHandler() throws Exception {

            saveProd();
            if (associatedProd != null) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/Main.fxml")));
                Stage window = (Stage) cancel.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            }




    }

    /**
     * The saveProd method gets the info selected from the text fields to create a new record of a product. If users submit
     * incorrect data in the date fields an error message will appear.
     *
     * A number format error was populated because of the chance of one or more of the text fields receiving the wrong
     * data type. I fixed this by implementing the try and catch method to catch any errors.
     */



    public void saveProd() {
        try {
        int maximum = Integer.parseInt(maxField.getText());
        int minimum = Integer.parseInt(minField.getText());
        int inventory = Integer.parseInt(stockField.getText());

            if ((maximum >= inventory)&(inventory >= minimum)) {
                String name = nameField.getText();
                int stock = Integer.parseInt(stockField.getText());
                double price = Double.parseDouble(priceField.getText());
                int max = Integer.parseInt(maxField.getText());
                int min = Integer.parseInt(minField.getText());

                int id = 0;

                associatedProd = new Product(id, name, price, stock, min, max);
                if (Inventory.lookupProduct(name) == null) {

                    Inventory.addProduct(associatedProd);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Maximum must be high than minimum. Inventory value will be in between those.");
                alert.show();
            }



    }
            catch (NumberFormatException e)

    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please enter correct values.");
        alert.showAndWait();
    }

    }

    /**
     *  The searchHandler is a action event method that allows users to input text into a text field to search for parts.
     *  The user can find multiple parts at a time by entering in the full name or partial string of a parts name to find
     *  parts. These parts will then be displayed by the parts tableview. Error messages are populated if users enter info
     *  in the text field that do not correlate with any parts. Users can also type in a parts Id number to find a specific
     *  part. When a user presses enter while text field is empty all parts show up in the tableview.
     *
     *
     */

    public void searchHandler() {
        String query = idSearchBox.getText();

        if (query.isEmpty()) {
            partTableView.setItems(getAllParts());
        }
        else {
            try{
                ObservableList<Part> search = FXCollections.observableArrayList();
                search = lookupPart(query);
                if(search != null){
                    partTableView.setItems(search);
                }
                else {
                    int numQuery = Integer.parseInt(query);
                    if ((lookupPart(query) == null)&(lookupPartId(numQuery)) == null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Search");
                        alert.setContentText("Part was not found");
                        alert.show();
                    }if (lookupPartId(numQuery) != null) {
                        partTableView.setItems(lookupPartId(numQuery));
                    }
                }
            }
            catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search");
                alert.setContentText("Part was not found");
                alert.show();
            }
        }

    }


}

