package sample.Model;

/**
 * @author Carlos Hernandez
 */

/**
 * This class extends the Part class and inherits its properties.
 *
 *
 */
public class OutSource extends Part {

    /**
     * @param companyName is a private string that is declared to identify an outsource Part.
     */

    private String companyName;

    /**
     *
     * @return getCompanyName returns a string labeled companyName.
     */

    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName is set by the setCompanyName method.
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     *
     * @param id this is inherited from the Part class. It uniquely identifies the Part through a number.
     * @param name this is inherited from the Part class. This contains the name of a product.
     * @param price this is inherited from the Part class. This contains the value of a product.
     * @param stock this is inherited from the Part class. This contains the current Inventory value of a product.
     * @param min this is inherited from the Part class. This is the minimum inventory level allowed of a product.
     * @param max this is inherited from the Part class. This is the maximum inventory level allowed of a product.
     * @param companyName this is inherited from the Part class. This is the name of a Company that distinguishes an Outsource part.
     */


    public OutSource(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

}
