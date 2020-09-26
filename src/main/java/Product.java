public abstract class Product implements Item{

    private int id = 0;
    private String type = "";
    private String manufacturer = "";
    private String name = "";

    Product(int id, String type, String manufacturer, String name){
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.name = name;
    }

    public int getID(){
        return id;
    }

    public String getType(){
        return type;
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

    public String toString(){
        return "Name: " + getName() + "\nManufacturer: " + getManufacturer()
                + "\nType: " + getType();
    }
}
