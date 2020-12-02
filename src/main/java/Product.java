public class Product implements Item {

    private int id = 0;
    private final ItemType type;
    private String manufacturer;
    private String name;

    Product(int id, String name, String manufacturer, ItemType type) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    Product(String name, String manufacturer, ItemType type) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }


    public int getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ItemType getType() {
        return type;
    }

    public String toString() {
        return "ID: " + getID() + "\nName: " + getName() + "\nManufacturer: " + getManufacturer()
                + "\nType: " + getType();
    }
}


