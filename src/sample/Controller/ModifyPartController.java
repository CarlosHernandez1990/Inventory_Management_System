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

import java.util.Objects;

/**
 * @author Carlos Hernandez
 *
 * The ModifyPartController is in charge of updating both InHouse and Outsourced parts.
 */


public class ModifyPartController {


    @FXML
    AnchorPane modifyPartInPage;
    @FXML
    Button save;
    @FXML
    Button cancel;
    @FXML
    RadioButton modifyPartOutButton;
    @FXML
    RadioButton modifyPartInButton;
    @FXML
    ToggleGroup modifyPartToggle;
    @FXML
    TextField idField;
    @FXML
    TextField genericField;
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
    TextField companyName;
    @FXML
    Label genericText;

    Part rowSelected;

    /**
     * The showPart method initializes the modify page by carrying data over from the main controller. The row selected
     * from the previous scene will auto populate in the textfields of this page. The if statement in the showPart method
     * selects a radio button from the toggle group according to whether the part is Inhouse or Outsource. If the inhouse
     * radio button is selected then the last textfield's label shows "MachineId". If outsouce is selected then that last
     * label show the text of "Company Name".
     *
     * @param rowSelected is the row that was selected to modify the corresponding part.
     *
     *
     */

    public void showPart(Part rowSelected) {


        if (rowSelected instanceof InHouse) {

            nameField.setText(rowSelected.getName());
            idField.setText(new Integer(rowSelected.getId()).toString());
            stockField.setText(new Integer(rowSelected.getStock()).toString());
            maxField.setText(new Integer(rowSelected.getMax()).toString());
            minField.setText(new Integer(rowSelected.getMin()).toString());
            priceField.setText(new Integer((int) rowSelected.getPrice()).toString());
            genericField.setText(new Integer(((InHouse) rowSelected).getMachineId()).toString());
            modifyPartInButton.setSelected(true);


            genericText.setText("MachineID");

        } else if (rowSelected instanceof OutSource) {
            nameField.setText(rowSelected.getName());
            idField.setText(new Integer(rowSelected.getId()).toString());
            stockField.setText(new Integer(rowSelected.getStock()).toString());
            maxField.setText(new Integer(rowSelected.getMax()).toString());
            minField.setText(new Integer(rowSelected.getMin()).toString());
            priceField.setText(new Integer((int) rowSelected.getPrice()).toString());
            genericField.setText(((OutSource) rowSelected).getCompanyName());
            modifyPartToggle.selectToggle(modifyPartOutButton);


            genericText.setText("Company Name");
        }
    }


    /**
     * The inHouseRadio method is an action event the displays the "MachineID" label next to the last textfield if the
     * inHouse radio button is selected.
     */


    public void inHouseRadio(){
        genericText.setText("MachineID");
    }

    /**
     * The outsourceRadio method is an action event that display the text "Company Name" next to the last textfield if the
     * outsource radio button is selected.
     */

    public void outsourceRadio(){
        genericText.setText("Company Name");
    }

    /**
     * The cancelHandler is an action event method that is controlled by the cancel button. When users press the cancel
     * button they will be directed back to the main page.
     *
     * @throws Exception is used to minimize any errors that may be populated.
     */


    public void cancelHandler() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
        Stage window = (Stage) cancel.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    /**
     * The saveHandler is an action event handler that is in charge of saving any changes made to a part. The previous
     * part is auto populated in the text field. If the user makes any changes to the part, then it will be recorded when the
     * user presses the save button. Depending on which radio button is selected at the time, the part will either be
     * saved to Inhouse or outsourced parts. The if statement ensures accuracy of correct data types being submitted. If
     * users enter incorrect information in the data fields then an error messages will appear on the screen.
     *
     * @throws Exception to ensure minimal errors
     */

    public void saveHandler() throws Exception {
        try { Part old = rowSelected;
            int maximumStock = Integer.parseInt(maxField.getText());
            int minimumStock = Integer.parseInt(minField.getText());
            int currentStock = Integer.parseInt(stockField.getText());
            double partPrice = Integer.parseInt(priceField.getText());
            int ids = Integer.parseInt(idField.getText());
            Part oldPart = null;
            for (Part part : Inventory.getAllParts()){
                if (part.getId() == ids) {
                    oldPart = part;
                }
            }
            System.out.print(oldPart.getId());
            if ((maximumStock >= currentStock) & (currentStock >= minimumStock)) {


                if (genericText.getText() == "MachineID") {
                    String name = nameField.getText();
                    int stock = Integer.parseInt(stockField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    int max = Integer.parseInt(maxField.getText());
                    int min = Integer.parseInt(minField.getText());
                    int machineId = Integer.parseInt(genericField.getText());

                    InHouse newPart = new InHouse(ids, name, price, stock, min, max, machineId);

                    Inventory.updatePart(oldPart, newPart);
                }




                 else if (genericText.getText() == "Company Name") {
                    String name = nameField.getText();
                    int stock = Integer.parseInt(stockField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    int max = Integer.parseInt(maxField.getText());
                    int min = Integer.parseInt(minField.getText());
                    String companyName = genericField.getText();

                    OutSource newPart = new OutSource(ids, name, price, stock, min, max, companyName);
                    Inventory.updatePart(oldPart, newPart);



                }
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/View/Main.fxml")));
                Stage window = (Stage) save.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            }

            else {
                Alert numAlert = new Alert(Alert.AlertType.ERROR);
                numAlert.setTitle("Error");
                numAlert.setContentText("Maximum value must be higher than Minimum value. Current Inventory value should be In between those values.");
                numAlert.show();

            }


        }
        catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter correct values.");
                alert.showAndWait();}



        }

    }








