package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Model.InHouse;
import sample.Model.Inventory;
import sample.Model.OutSource;
import sample.Model.Part;

/**
 * @author Carlos Hernandez
 *
 * The AddPartController is in charge of adding both InHouse and Outsourced parts.
 */

public class AddPartController {


    @FXML
    AnchorPane addPartInPage;
    @FXML
    Button save;
    @FXML
    Button cancel;
    @FXML
    RadioButton inHouseButton;
    @FXML
    RadioButton outsourceButton;
    @FXML
    ToggleGroup partRadioToggleGroup;
    @FXML
    TextField nameField;
    @FXML
    TextField stockField;
    @FXML
    TextField priceField;
    @FXML
    TextField maxField;
    @FXML
    TextField minField;
    @FXML
    TextField machineIdField;
    @FXML
    TextField idField;
    @FXML
    Label genericText;

    /**
     * The cancelHandler is an action event method that is controlled by the cancel button. When users press the cancel
     * button they will be directed back to the main page.
     *
     * @throws Exception is used to minimize any errors that may be populated.
     */

    public void cancelHandler() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
        Stage window = (Stage) cancel.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    /**
     * The saveHandler is an action event method that gets the text in the text fields to create a new record of a part.
     * This is controlled by users entering data in the text fields and the pressing save. If users enter incorrect info
     * then an error will appear.
     *
     * @throws Exception to minimize the risk of an error.
     *
     *
     * A logical error was fixed by changing the code of the min and max field. There were two minField's getting text data
     * because one line of code was incorrectly entered. By adding a maxField property to a text box this issue was resolved.
     */


    public void saveHandler() throws Exception {

        try {
            int maximumStock = Integer.parseInt(maxField.getText());
            int minimumStock = Integer.parseInt(minField.getText());
            int currentStock = Integer.parseInt(stockField.getText());
            int machineNum = Integer.parseInt(machineIdField.getText());
            Double partPrice = Double.parseDouble(priceField.getText());

            if ((maximumStock >= currentStock)&(currentStock >= minimumStock))
            {
                if (inHouseButton.isSelected()) {
                    String name = nameField.getText();
                    int stock = Integer.parseInt(stockField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    int max = Integer.parseInt(maxField.getText());
                    int min = Integer.parseInt(minField.getText());
                    int machineId = Integer.parseInt(machineIdField.getText());
                    int id = 0;

                    Part part = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.addPart(part);


                } else if (outsourceButton.isSelected()) {
                    String name = nameField.getText();
                    int stock = Integer.parseInt(stockField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    int max = Integer.parseInt(maxField.getText());
                    int min = Integer.parseInt(minField.getText());
                    String companyName = machineIdField.getText();
                    int id = 0;

                    Part part = new OutSource(id, name, price, stock, min, max, companyName);
                    Inventory.addPart(part);
                }


                Parent root = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
                Stage window = (Stage) save.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            }
            else{
                Alert numAlert = new Alert(Alert.AlertType.ERROR);
                numAlert.setTitle("Error");
                numAlert.setContentText("Maximum value must be higher than Minimum value. Current Inventory value should be In between those values.");
                numAlert.show();
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
     * The inHouseHandler method is an action event the displays the "MachineID" label next to the last text field if the
     * inHouse radio button is selected.
     */

    public void inHouseHandler() {
        genericText.setText("MachineID");

    }

    /**
     * The outsourceHandler method is an action event that display the text "Company Name" next to the last textfield if the
     * outsource radio button is selected.
     */

    public void outsourceHandler() {
        genericText.setText("Company Name");

    }

}

