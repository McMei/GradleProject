import java.util.*;

public class ProductionRecord {
    private int productionNumber = 0;
    private String productName = "";
    private int productID = 0;
    private String serialNumber;
    private Date dateProduced;

    ProductionRecord(int productID) {
        this.productID = productID;
        productionNumber = 0;
        serialNumber = "0";
        dateProduced = new Date();
    }

    ProductionRecord(int productionNumber, String productName, String serialNumber, Date dateProduced) {
        this.productionNumber = productionNumber;
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.dateProduced = dateProduced;
    }

    ProductionRecord(String productName, String serialNumber, Date dateProduced) {
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.dateProduced = dateProduced;
    }


    ProductionRecord(Product product, int count) {
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

    public String getName() {
        return productName;
    }

    public void setName(String productName) {
        this.productName = productName;
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
    public String toString() {
        return "Prod. Num: " + getProductionNum() + "  Product Name: " + getName()
                + "  Serial Num: " + getSerialNum() + "  Date: " + getProdDate();
    }

}
