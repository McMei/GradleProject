public class Product implements Item{

    private int id = 0;
    private ItemType type;
    private String manufacturer = "";
    private String name = "";

    Product(String name, String manufacturer, ItemType type){
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }


    public int getID(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public ItemType getType(){
        return type;
    }

    public String toString(){
        return "Name: " + getName() + "\nManufacturer: " + getManufacturer()
                + "\nType: " + getType();
    }
}

class Widget extends Product{

    Widget(String name, String manufacturer, ItemType type){
        super(name, manufacturer, type);
    }

}
