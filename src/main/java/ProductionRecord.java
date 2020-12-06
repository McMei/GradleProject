import java.util.*;

/**
 * Represents a Recorded Product.
 *
 * @author Michelle Quan
 */
public class ProductionRecord {

    /**
     * Production number.
     */
    private int productionNumber = 0;

    /**
     * Product name.
     */
    private String productName = "";

    /**
     * Product ID.
     */
    private int productID = 0;

    /**
     * Product serial number.
     */
    private String serialNumber;

    /**
     * Production date.
     */
    private Date dateProduced;


    /**
     * Creates a production date.
     *
     * @param productID Product ID.
     */
    ProductionRecord(int productID) {
        this.productID = productID;
        dateProduced = new Date();
    }

    /**
     * Creates a recorded product with production number, product name, serial number, and date produced.
     *
     * @param productionNumber Production number.
     * @param productName      Product name.
     * @param serialNumber     Product serial number.
     * @param dateProduced     Production date.
     */
    ProductionRecord(int productionNumber, String productName, String serialNumber, Date dateProduced) {
        this.productionNumber = productionNumber;
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.dateProduced = dateProduced;
    }

    /**
     * Creates a recorded product with product name, serial number, and date produced.
     *
     * @param productName  Product name.
     * @param serialNumber Product serial number.
     * @param dateProduced Production date.
     */
    ProductionRecord(String productName, String serialNumber, Date dateProduced) {
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.dateProduced = dateProduced;
    }

    /**
     * Creates a serial number.
     *
     * @param product Product.
     * @param count   Number of Products.
     */
    ProductionRecord(Product product, int count) {
        serialNumber = product.getManufacturer().substring(0, 3) + product.getType().getCode() + String.format("%05d", count);
    }

    /**
     * Gets the production number.
     *
     * @return An integer representing the production number.
     */
    public int getProductionNum() {
        return productionNumber;
    }

    /**
     * Sets the production number.
     *
     * @param productionNumber Production number.
     */
    public void setProductionNum(int productionNumber) {
        this.productionNumber = productionNumber;
    }

    /**
     * Gets the product ID.
     *
     * @return An integer representing the product ID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets the product ID.
     *
     * @param productID Product ID.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Gets the product name.
     *
     * @return A String representing the product name.
     */
    public String getName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName Product name.
     */
    public void setName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the serial number.
     *
     * @return A String representing the serial number.
     */
    public String getSerialNum() {
        return serialNumber;
    }

    /**
     * Sets the serial number.
     *
     * @param serialNumber Serial number.
     */
    public void setSerialNum(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the production date.
     *
     * @return A Date representing the production date.
     */
    public Date getProdDate() {
        return dateProduced;
    }

    /**
     * Sets the production date.
     *
     * @param dateProduced Production date.
     */
    public void setProdDate(Date dateProduced) {
        this.dateProduced = dateProduced;
    }

    /**
     * Gets production logs.
     *
     * @return A String with production logs.
     */
    @Override
    public String toString() {
        return "Prod. Num: " + getProductionNum() + "  Product Name: " + getName()
                + "  Serial Num: " + getSerialNum() + "  Date: " + getProdDate();
    }

}
