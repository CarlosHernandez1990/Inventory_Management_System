package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.Inventory;
import sample.Model.Part;
import sample.Model.Product;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static sample.Model.Inventory.*;

/**
 * @author Carlos Hernandez
 *
 * This controller class controlls the functionality of the main page of the application. It is linked with Main.fxml.
 */


public class Controller {


    @FXML
    Button addPartInButton;
    @FXML
    Button modifyPartInButton;
    @FXML
    Button mainDeletePartButton;
    @FXML
    Button modifyProductButton;
    @FXML
    Button addProductButton;
    @FXML
    Button mainDeleteProductButton;
    @FXML
    Button exit;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> idCol;
    @FXML
    private TableColumn<Part, String> nameCol;
    @FXML
    private TableColumn<Part, Double> priceCol;
    @FXML
    private TableColumn<Part, Integer> stockCol;
    @FXML
    private TableView<Product> prodTableView;
    @FXML
    private TableColumn<Product, Integer> prodIdCol;
    @FXML
    private TableColumn<Product, String> prodNameCol;
    @FXML
    private TableColumn<Product, Double> prodPriceCol;
    @FXML
    private TableColumn<Product, Integer> prodStockCol;
    @FXML
    TextField idSearchBox;
    @FXML
    TextField ProdIdSearchBox;


    /**
     * This method initializes the main application page and displays two tableviews. There are two panes located on
     * the main application page. Those panes are designated to display information of the Product class and the Part class.
     * Each pain contains a tableview that display all parts and all products. These tables are initialized on the main
     * page.
     */

    public void initialize() {
        partTableView.setItems(Inventory.getAllParts());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        prodTableView.setItems(Inventory.getAllProducts());
        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }

    /**
     *
     * The addPartInHandler is an action event that is caused by the user clicking the add button. This handler
     * will load the fxml resource of the AddPart.fxml file. The scene will be changed to take users to the Add Parts
     * page.
     *
     * @throws Exception is included to prevent any errors.
     */

    public void addPartInHandler() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/AddPart.fxml")));
        Stage window = (Stage) addPartInButton.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    /**
     * The modifyPartHandler is an action event method that allows users to select a row of the parts table view to modify.
     * Users select the row they would like to modify and then press the submit button to initiate the process. An alert
     * is executed when users press the modify button while not having selected a row . When the users have
     * selected a row while pressing the modify button, the scene changes to the modify part page. The info of the part
     * selected will shop up in the textfields of the next page.
     *
     *
     * @throws IOException to avoid any errors that may arise with FXML loader.
     */

    public void modifyPartHandler() throws IOException {
        Part rowSelected = partTableView.getSelectionModel().getSelectedItem();
        if (rowSelected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/View/ModifyPart.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) (modifyPartInButton).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();

            ModifyPartController transfer = loader.getController();
            transfer.showPart(rowSelected);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row");
            alert.show();
        }

    }

    /**
     * The mainDeletePartHandler is an action event method that gives users the ability to remove a part from the Part
     * class. Users can remove a part by selecting a row and then pressing delete. If a row is not a selected than an error
     * alert will appear. The if statement ensures that a row will be selected. When users press the delete button to
     * remove a part a confirmation will display. If users press the OK button than that part will be removed.
     *
     */

    public void mainDeletePartHandler() {

        Part partRowSelected = partTableView.getSelectionModel().getSelectedItem();


        if (partRowSelected == null) {


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete this part");
            Optional<ButtonType> result = alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                Inventory.deletePart(partRowSelected);
            }


        }
    }

    /**
     *  The mainDeleteProductHandler is an action event method that gives users the ability to remove a product from the
     *  Product class. Users can remove a product by selecting a row and pressing delete. If a row is not a selected an
     *  error alert message will be populated. The if statement ensures that a row will be selected. When users press the
     *  delete button to remove a Product a confirmation will display. If users press the OK button the product will
     *  be removed. If there is a part associated with the selected Product an error message will appear. The if statement
     *  ensures that a user know that they must delete all associated parts in order to delete a product.
     */


    public void mainDeleteProductHandler() {

        Product prodRowSelected = prodTableView.getSelectionModel().getSelectedItem();


        if (prodRowSelected == null) {


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row");
            alert.show();
        } else {
            String part = prodRowSelected.getAllAssociatedPart().toString();
            System.out.print(part);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete this product");
            Optional<ButtonType> result = alert.showAndWait();
            if (part != "[]") {
                Alert none = new Alert(Alert.AlertType.ERROR);
                none.setTitle("Error");
                none.setContentText("You must delete all associated parts in order to delete this product. Select a product and press Modify to proceed");
                none.show();
            }
            else if (alert.getResult() == ButtonType.OK) {
                Inventory.deleteProduct(prodRowSelected);
            }


        }
        }

    /**
     * The modifyProductHandler is an action event method that is initiated by users selecting a row in the products tableview
     * while pressing the modify button. A row must be selected in order to modify a product. If no row is select than an error message
     * will appear. When a row is selected and the modify button is pressed, the application will changes scenes by loading
     * the modifyProduct fxml page. The row selected info is then transferred by a controller of the Modify Product page.
     *
     * @throws IOException this will reduce the chance of an error being populated.
     */


    public void modifyProductHandler() throws IOException {
        Product prodRowSelected = prodTableView.getSelectionModel().getSelectedItem();
        if (prodRowSelected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/View/ModifyProduct.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) modifyProductButton.getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
            ModifyProductController prodTransfer = loader.getController();
            prodTransfer.showProduct(prodRowSelected);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row");
            alert.show();
        }
    }

    /**
     * The addProductHandler is an action event method that is controlled by the add button on the products pane. This
     * loads the next page from the fxml loader and changes scenes to to show the AddProduct page.
     *
     * @throws Exception
     */

    public void addProductHandler() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/AddProduct.fxml")));
        Stage window = (Stage) addPartInButton.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();

    }

    /**
     * The exitHandler is an action event method that is initiated by users clicking the exit button. This will close the
     * entire stage and application.
     *
     * @throws Exception
     */


    public void exitHandler() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/Main.fxml")));
        Stage window = (Stage) exit.getScene().getWindow();
        window.setScene(new Scene(root));
        window.close();
    }

    /**
     * The searchHandler is a action event method that allows users to input text into a text field to search for parts.
     * The user can find multiple parts at a time by entering the full name or partial string of a parts name. These parts
     * will then be displayed by the parts tableview. Error messages are populated if users enter info
     * in the text field that do not correlate with any parts. Users can also type in a parts Id number to find a specific
     * part. When a user presses enter while text field is empty, all parts show up in the tableview.
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

    /**
     * The searchHandler is a action event method that allows users to input text into a text field to search for products.
     * The user can find multiple parts at a time by entering in the full name or partial string of a products name to find
     * products. These products will then be displayed by the parts tableview. Error messages are populated if users enter info
     * in the text field that do not correlate with any products. Users can also type in a products Id number to find a specific
     * product. When a user presses enter while text field is empty all products show up in the tableview.
     */
    public void searchHandler2() {
        String query = ProdIdSearchBox.getText();


        if (query.isEmpty())
        {
            prodTableView.setItems(getAllProducts());
            System.out.print("Step 1");
        }
        else {
            try {
                ObservableList<Product> search = FXCollections.observableArrayList();
                search = lookupProduct(query);
                 if (search != null) {
                     System.out.print("Step 2");
                    prodTableView.setItems(search);

                }
                 else {
                     int numberQuery = Integer.parseInt(query);
                     if ((lookupProduct(query) == null) & (lookupProductId(numberQuery) == null)) {
                         System.out.print("Step 3");
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("Search");
                         alert.setContentText("Product was not found");
                         alert.show();
                     }
                     if (lookupProductId(numberQuery) != null) {
                         prodTableView.setItems(lookupProductId(numberQuery));
                     }
                 }
            }
            catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search");
                alert.setContentText("Product was not found");
                alert.show();
            }
        }

    }
}
































