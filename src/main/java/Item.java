/**
 * This is an interface for Items.
 *
 * @author Michelle Quan
 */
public interface Item {
    /**
     * Gets product ID.
     *
     * @return An integer representing the product ID.
     */
    int getID();

    /**
     * Sets the product name.
     *
     * @param name Product name.
     */
    void setName(String name);

    /**
     * Gets product name.
     *
     * @return A String representing the product name.
     */
    String getName();

    /**
     * Sets the product manufacturer.
     *
     * @param manufacturer Product manufacturer.
     */
    void setManufacturer(String manufacturer);

    /**
     * Gets manufacturer.
     *
     * @return A String representing the manufacturer.
     */
    String getManufacturer();

}
