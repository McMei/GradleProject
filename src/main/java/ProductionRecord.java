import java.util.*;

public class ProductionRecord {
    private int productionNumber = 0;
    private int productID = 0;
    private String serialNumber = "";
    private Date dateProduced = new Date();
    private Product product;
    private int count;

    ProductionRecord(int productID){
        this.productID = productID;
        productionNumber = 0;
        serialNumber = "0";
        dateProduced = new Date();
    }

    ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced){
        this.productionNumber = productionNumber;
        this.productID = productID;
        this.serialNumber = serialNumber;
        this.dateProduced = dateProduced;
    }

    ProductionRecord(Product product, int count){
        this.product = product;
        this.count = count;
        serialNumber = product.getManufacturer().substring(0, 3) + product.getType().getCode() + String.format("%05d", count);
    }

    public int getProductionNum() {
        return productionNumber;
    }

    public void setProductionNum(int productionNumber) {
        this.productionNumber = productionNumber;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSerialNum() {
        return serialNumber;
    }

    public void setSerialNum(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getProdDate() {
        return dateProduced;
    }

    public void setProdDate(Date dateProduced) {
        this.dateProduced = dateProduced;
    }

    @Override
    public String toString(){
        return "Prod. Num: " + getProductionNum() + " Product ID: " + getProductID()
                + " Serial Num: " + getSerialNum() + " Date: " + getProdDate();
    }
}
