package sample.Model;

/**
 *
 * @author Carlos Hernandez
 */

/**
 * This child class extends the Parent class. InHouse  inherits the properties and methods of the parent class Part.
 *
 * This class creates a subtype of the Part inventory.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InHouse extends Part {
    /**
     * Private integer machineId declared to provide InHouse parts identification.
     *
     */

    private int machineId;

    /**
     *
     * @param id is inherited from the parent class. It allows each part to have a Unique Id.
     * @param name is inherited from the parent class. It is the name of the part.
     * @param price is inherited from the parent class. This variable holds the price of the part.
     * @param stock is inherited from the parent class. This is the current inventory level of the part.
     * @param min is inherited from the parent class. This is the minimum value of stock that should be in inventory.
     * @param max is inherited from the parent class. This is the maximum value of stock that should be in inventory.
     * @param machineId is an exclusive Identifier that extends the set of the Parent class. This uniquely identifies an InHouse part.
     *
     *
     */


    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     *
     * @return getMachineId returns the value of the machineId.
     */

    public int getMachineId() {
        return machineId;
    }

    /**
     *
     * @param machineId this method set the machineID
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}
