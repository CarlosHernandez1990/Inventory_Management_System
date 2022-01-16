package sample.Model;

/**
 * @author Carlos Hernandez
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    /**
     * an
     */

    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();

    /**
     *
     * @return getAllAssociatedParts returns an observable list of an associated part.
     */

    public ObservableList<Part> getAllAssociatedPart() {
        return associatedPart;
    }

    /**
     *
     * @param part adds a part to the observable list of associated parts.
     */

    public void addAssociatedPart(Part part){
        associatedPart.add(part);
    }

    /**
     *
     * @param part deletes an associated part.
     */

    public void deleteAssociatedPart(Part part){ associatedPart.remove(part);}

    /**
     *
     * @param associatedPart the observable list that will hold the data of an associated part.
     * @param prodId the unique identification of a product.
     * @param prodName the name of the product.
     * @param price the price of the product.
     * @param stock the inventory level of the product.
     * @param min the minimum value of product allowed.
     * @param max the maximum value of product allowed.
     */

    public Product(ObservableList associatedPart, int prodId, String prodName, double price, int stock, int min, int max) {
        this.associatedPart = associatedPart ;
        this.id = prodId;
        this.name = prodName;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;


    }

    /**
     * Id, name, price, stock, min, max are the private variables declared to construct a Product.
     */



    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     *
     * @param prodId the product identification.
     * @param prodName the product name.
     * @param price the price of the product.
     * @param stock the Inventory value of the product.
     * @param min the minimum value allowed for the product.
     * @param max the maximum value allowed for the product.
     */

    public Product(int prodId, String prodName, double price, int stock, int min, int max) {
        this.id = prodId;
        this.name = prodName;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return getId returns the value of a Id of a product.
     */

        public int getId () {
            return id;
        }

    /**
     *
     * @param id the setId method establishes the value of the Id.
     */

        public void setId ( int id){
            this.id = id;
        }

    /**
     *
     * @return the getName method gets the name of the product.
     */

        public String getName() {
            return name;
        }

    /**
     *
     * @param name the setName method sets the name of the product.
     */

        public void setName (String name){
            this.name = name;
        }

    /**
     *
     * @return the getPrice method gets the price of a product.
     */

        public double getPrice () {
            return price;
        }

    /**
     *
     * @param price sets the value of a products price.
     */

        public void setPrice ( double price){
            this.price = price;
        }

    /**
     *
     * @return gets the value of the current inventory level of a product.
     */

        public int getStock () {
            return stock;
        }

    /**
     *
     * @param stock sets the value of the current inventory level of a product.
     */

        public void setStock ( int stock){
            this.stock = stock;
        }

    /**
     *
     * @return the getMin method returns the value of the minimum inventory level.
     */
        public int getMin () {
            return min;
        }

    /**
     *
     * @param min the setMin method sets the minimum inventory level of a product.
     */

        public void setMin ( int min){
            this.min = min;
        }

    /**
     *
     * @return the getMax method gets the max value of inventory of a product.
     */

        public int getMax () {
            return max;
        }

    /**
     *
     * @param max the setMax method sets the max value of inventory of a product.
     */

        public void setMax ( int max){
            this.max = max;
        }






}
