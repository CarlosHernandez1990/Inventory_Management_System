package sample.Model;

/**
 * @author Carlos Hernandez
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;

public class Inventory {
    /**
     * allParts and allProducts are the observable list arrays that will hold most of the data that will be used by the application.
     */

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *
     * @param parts the addPart method allow parts to be added to Part class. Also assigns parts a unique Id.
     */



    public static void addPart(Part parts) {
        for (Part part : allParts) {
            int increment = part.getId();
            int newId = parts.getId();
            if (increment != newId)
                parts.setId(newId);
            else if (increment == newId) {
                newId = increment + 1;
                parts.setId(newId);
            }
            for (Part oldPart : allParts){
                int newerId = oldPart.getId();
                if(increment != newerId){
                    parts.setId(newId);}
                    else{ newerId = newerId + 1;
                        parts.setId(newerId);

                    }
                }

            }

        allParts.add(parts);

    }

    /**
     *
     * @param products the addProduct method adds observable lists of product to the Product class.
     */

    public static void addProduct(Product products) {
        for (Product product : allProducts) {
            int increment = product.getId();
            int newId = products.getId();
            if (increment != newId)
                products.setId(newId);
            else if (increment == newId) {
                newId = increment + 1;
                products.setId(newId);
            }
            for (Product oldProduct : allProducts) {
                int newerId = oldProduct.getId();
                if (increment != newerId) {
                    products.setId(newId);
                } else {
                    newerId = newerId + 1;
                    products.setId(newerId);

                }
            }

        }
        allProducts.add(products);
    }

    /**
     *
     * @param id the id parameter gives the lookupPartId method an argument to look for a Product by id.
     *           an enhanced for loop was used to search for product Ids through arrays. Once a matching id
     *           is found an observable list is returned.
     * @return The part that is found is returned in the form of an observable list. If there is no Id match
     * than the method returns null.
     */

    public static ObservableList lookupPartId(int id) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> idSearch = FXCollections.observableArrayList();

        for (Part idMatch : allParts) {
            if (idMatch.getId() == id) {
                idSearch.add(idMatch);
                return idSearch;
            }

        }

        return null;

    }

    /**
     *
     * @param id a product id is passed as an argument to the lookupProductId method. This method returns an observable
     *           list of a product. The product is found my iterating through a collection of observable lists. Each list
     *           is compared to id parameter that was passed to the method.
     * @return the observable list's id that matches the id argument is than returned by the method. If no matching id
     * is found than the method returns null.
     */

    public static ObservableList lookupProductId(int id) {
        ObservableList<Product> allProduct = Inventory.getAllProducts();
        ObservableList<Product> idSearch = FXCollections.observableArrayList();

        for (Product idMatch : allProduct) {
            if (idMatch.getId() == id) {
                idSearch.add(idMatch);
                return idSearch;
            }

        }

        return null;
    }

    /**
     *
     * @param partialPart the lookupPart method is passed an argument in the form of a string that represents a part's
     *                    name. That parameter is compared to lists of arrays to find if there is a partial match to a
     *                    name in those arrays.
     * @return This method returns each array that contain a partial match to any of the names of observable lists.
     */

    public static ObservableList<Part> lookupPart(String partialPart) {
        ObservableList<Part> nameSearch = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part nameMatch : allParts) {
            int index = 0;
            index++;
            String search = nameMatch.getName();
            if (search.toLowerCase(Locale.ROOT).contains(partialPart.toLowerCase(Locale.ROOT))) {
                nameSearch.addAll(nameMatch);


            }

        }
        if (nameSearch.isEmpty())
        {return null;}
        else {
            return nameSearch;
        }


    }

    /**
     *
     * @param partialProduct the lookupProduct method is passed an argument in the form of a string that represents a Product's
     *                         name. That parameter is compared to lists of arrays to find if there is a partial match to a
     *                         name in those arrays.
     * @return This method returns each array that contains a partial match to any of the names of observable lists.
     */

    public static ObservableList<Product> lookupProduct(String partialProduct) {
        ObservableList<Product> nameSearch = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product nameMatch : allProducts) {
            String search = nameMatch.getName();
            if (search.toLowerCase(Locale.ROOT).contains(partialProduct.toLowerCase(Locale.ROOT))) {
                nameSearch.add(nameMatch);

            }
        }
        if (nameSearch.isEmpty())
        {return null;}
        else {
            return nameSearch;}
    }

    /**
     * This method updates a part by deleting an old instance of a part and replacing it with a new instance of that
     * part. This method also retains the id of the part.
     *
     * @param oldPart this is the old instance of a part that will be removed.
     * @param newPart this is the new instance of a part that will be replaced.
     */

    public static void updatePart(Part oldPart, Part newPart) {


        if (oldPart instanceof InHouse) {
            int oldId = oldPart.getId();

            allParts.remove(oldPart);
            allParts.add(newPart);
            newPart.setId(oldId);



        } else if (oldPart instanceof OutSource) {

            int oldId = oldPart.getId();
            allParts.remove(oldPart);
            allParts.add(newPart);
            newPart.setId(oldId);

        }


    }

    /**
     *
     * This method updates a product my deleting an old instance of a product and replacing it with a new one. This
     * update retains all associated parts of a product as well as the id of a product.
     *
     * @param oldProd this is the old instance of a product to be removed from products.
     * @param newProd this is the new instance of a product that will replace the old one.
     */

    public static void updateProduct(Product oldProd, Product newProd) {
        if (oldProd instanceof Product) {

            allProducts.remove(oldProd);
            allProducts.add(newProd);
            int oldId = oldProd.getId();
            newProd.setId(oldId);

        }


    }

    /**
     *
     * @param parts the method removes parts from the part class.
     */

    public static void deletePart(Part parts) {

        allParts.remove(parts);

    }

    /**
     *
     * @param products the method removes a product from the Product class.
     */

    public static void deleteProduct(Product products) {

        allProducts.remove(products);

    }

    /**
     *
     * @return this method returns all observable lists contained within the part class.
     */

    public static ObservableList<Part> getAllParts() {

        return allParts;

    }

    /**
     *
     * @return this method returns all observable lists contained within the product class.
     */

    public static ObservableList<Product> getAllProducts() {

        return allProducts;

    }








}




