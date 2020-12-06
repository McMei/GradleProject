/**
 * Represents a Product.
 * Implements Item.
 *
 * @author Michelle Quan
 */
public class Product implements Item {

    /**
     * Product ID.
     */
    private int id = 0;

    /**
     * Product type.
     */
    private final ItemType type;

    /**
     * Product manufacturer.
     */
    private String manufacturer;

    /**
     * Product name.
     */
    private String name;

    /**
     * Creates a Product with id, name, manufacturer, and item type.
     *
     * @param id           Product ID.
     * @param name         Product name.
     * @param manufacturer Product manufacturer.
     * @param type         Product type.
     */
    Product(int id, String name, String manufacturer, ItemType type) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }


    /**
     * Creates a Product with name, manufacturer, and item type.
     *
     * @param name         Product name.
     * @param manufacturer Product manufacturer.
     * @param type         Product type.
     */
    Product(String name, String manufacturer, ItemType type) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    /**
     * Gets product ID.
     *
     * @return An integer representing the product ID.
     */
    public int getID() {
        return id;
    }

    /**
     * Sets product name.
     *
     * @param name Product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets product name.
     *
     * @return A String representing the product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets product manufacturer.
     *
     * @param manufacturer Product manufacturer.
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets product manufacturer.
     *
     * @return A String representing the product manufacturer.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Gets product type.
     *
     * @return A string representing the product type.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Gets product information.
     *
     * @return A String with product information.
     */
    @Override
    public String toString() {
        return "ID: " + getID() + "\nName: " + getName() + "\nManufacturer: " + getManufacturer()
                + "\nType: " + getType();
    }
}


