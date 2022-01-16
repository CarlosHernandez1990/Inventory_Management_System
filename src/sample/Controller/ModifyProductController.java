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
import java.util.Objects;

import static sample.Model.Inventory.*;

/**
 * @author Carlos Hernandez
 *
 * The ModifyProductController is in charge of the page used to modify products.
 */

public class ModifyProductController {

    @FXML
    AnchorPane modifyProduct;
    @FXML
    Button add;
    @FXML
    Button save;
    @FXML
    Button cancel;
    @FXML
    Button delete;
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
    TableView<Part> partTableView;
    @FXML
    TableColumn<Part, Integer> idCol;
    @FXML
     TableColumn<Part, String> nameCol;
    @FXML
    TableColumn<Part, Double> priceCol;
    @FXML
     TableColumn<Part, Integer> stockCol;
    @FXML
    TableView<Part> associatedPartTableView;
    @FXML
    TableColumn<Part, Integer> prodIdCol;
    @FXML
    TableColumn<Part, String> prodNameCol;
    @FXML
    TableColumn<Part, Double> prodPriceCol;
    @FXML
    TableColumn<Part, Integer> prodStockCol;
    @FXML
    TextField idSearchBox;

    Product prodRowSelected;


    /**
     * This method initializes the page by auto-populating the data of parts and associated parts in the tableviews.
     */

    public void initialize(){

        partTableView.setItems(Inventory.getAllParts());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     *  The cancelHandler is an action event method that is controlled by the cancel button. When users press the cancel
     *  button they will be directed back to the main page.
     *
     * @throws Exception
     */


    public void cancelHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/Main.fxml")));
        Stage window = (Stage) cancel.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    /**
     *The saveHandler is an action event method that changes a specific Products properties by pressing the save button.
     * It will get the text submitted in the text fields and apply any changes to the the product. Users also have the
     * functionality to either remove or add more associated parts.
     *
     * @throws Exception to minimize chances for errors.
     */

    public void saveHandler() throws Exception{
        try {
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());
            double price = Double.parseDouble(priceField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());
            int id = 0;
            String companyName = nameField.getText();
            ObservableList<Part> associatePart = prodRowSelected.getAllAssociatedPart();
            Product dataEntered = new Product(associatePart, id, name, price, stock, min, max);

            if ((max >= stock)&(stock >= min)) {
                Inventory.updateProduct(prodRowSelected, dataEntered);


                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/Main.fxml")));
                Stage window = (Stage) save.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Maximum must be higher than value. Inventory value must fall in between them.");
                alert.showAndWait();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter correct values.");
            alert.showAndWait();
        }

    }

    /**
     * The addHandler is an action event method that is controlled by users pressing the add button. When users select a
     * row from the top table and press submit, the selected row will then be added to the bottom table. If the
     * user presses the add button without having selected a row then an alert will be populated.
     *
     * @throws Exception
     */


    public void addHandler() throws Exception {

            Part selected = partTableView.getSelectionModel().getSelectedItem();
            if (selected == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a row.");
                alert.showAndWait();
            }
            else {
                prodRowSelected.addAssociatedPart(selected);
                associatedPartTableView.setItems(prodRowSelected.getAllAssociatedPart());
                prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        }

    /**
     * The deleteHandler is an action event method that is controlled by users selecting a row on the bottom table and then
     * pressing delete. The row selected in the bottom table will then be removed. An error will be populated if users
     * press delete without having selected a row.
     *
     * @throws Exception is used to minimize the risks of errors being populated.
     */


    public void deleteHandler() throws Exception {
        try {
            Part selected = associatedPartTableView.getSelectionModel().getSelectedItem();
            prodRowSelected.deleteAssociatedPart(selected);

            associatedPartTableView.setItems(prodRowSelected.getAllAssociatedPart());
            prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            System.out.print(selected.getName());
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a row.");
            alert.showAndWait();
        }

    }

    /**
     * Show product is a method that receives data from the previous controller and populates info in the text field from
     * the selected row on the previous page.
     *
     * @param prodRowSelected is the product row select on the main page.
     */


    public void showProduct(Product prodRowSelected){

        this.prodRowSelected = prodRowSelected;
        nameField.setText(prodRowSelected.getName());
        idField.setText(Integer.toString(prodRowSelected.getId()));
        stockField.setText(Integer.toString(prodRowSelected.getStock()));
        maxField.setText(Integer.toString(prodRowSelected.getMax()));
        minField.setText(Integer.toString(prodRowSelected.getMin()));
        priceField.setText(Integer.toString((int) prodRowSelected.getPrice()));

        associatedPartTableView.setItems(prodRowSelected.getAllAssociatedPart());

    }

    /**
     *  The searchHandler is a action event method that allows users to input text into a text field to search for parts.
     *  The user can find multiple parts at a time by entering in the full name or partial string of a parts name to find
     *  parts. These parts will then be displayed by the parts tableview. Error messages are populated if users enter info
     *  in the text field that do not correlate with any parts. Users can also type in a parts Id number to find a specific
     *  part. When a user presses enter while text field is empty all parts show up in the tableview.
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

