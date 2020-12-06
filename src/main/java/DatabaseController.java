import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


/**
 * Implements a simple Database Controller.
 * It controlls the Product database and ProductRecord database.
 *
 * @author Michelle Quan
 */
@SuppressWarnings("ALL")
public class DatabaseController {

    /**
     * Show products that have been added in the table.
     */
    @FXML
    private TableView<Product> tableView;

    /**
     * Show products that have been added in the list.
     * User is able to select product and record it from the ListView.
     */
    @FXML
    private ListView<Product> listView;

    /**
     * Shows the Product IDs in the table.
     */
    @FXML
    private TableColumn<?, ?> idCol;

    /**
     * Shows the Product Names in the table.
     */
    @FXML
    private TableColumn<?, ?> nameCol;

    /**
     * Shows the Product types in the table.
     */
    @FXML
    private TableColumn<?, ?> typeCol;

    /**
     * Shows the Product Manufacturers in the table.
     */
    @FXML
    private TableColumn<?, ?> manufacturerCol;

    /**
     * Shows a set of numbers which the user can choose.
     * It also allows the user to enter other values in the Combobox.
     */
    @FXML
    private ComboBox<String> cmbQuantity;

    /**
     * Shows the product types which the user can choose from.
     */
    @FXML
    private ChoiceBox<ItemType> cbType;

    /**
     * Takes a product name from the user input.
     */
    @FXML
    private TextField txtpro_name;

    /**
     * Takes a product manufacturer from the user input.
     */
    @FXML
    private TextField txtManufacturer;

    /**
     * Shows the product logs that have been recorded.
     */
    @FXML
    private TextArea txtProductionRecord;

    /**
     * Shows an error message when the user is trying to add a manufacturer which has two characters or less.
     */
    @FXML
    private Text manufacturerError;

    /**
     * Show an error message when the user is trying to record a product without selecting a product.
     */
    @FXML
    private Text selectProduct;

    /**
     * Shows a message when the user records a product successfully.
     */
    @FXML
    private Text txtSuccessRecord;

    /**
     * Shows a message when the user adds a product successfully.
     */
    @FXML
    private Text txtSuccess;

    /**
     * Shows a message when the user generates an employees information successfully.
     */
    @FXML
    private Text txtGenerateSuccess;

    /**
     * Takes a employees's password from the user input.
     */
    @FXML
    private PasswordField txtPassword;

    /**
     * Takes a employees's full name from the user input.
     */
    @FXML
    private TextField txtFullName;

    /**
     * Shows the employees information that has been generated.
     */
    @FXML
    private TextArea textareaShowInformation;


    /**
     * Counts the number of products that have been added in PRODUCT.
     */
    private int counter = 0;

    /**
     * Counts the number of products that have been recorded in PRODUCTIONRECORD.
     */
    private int row = 0;

    /**
     * Counts the number of AUDIO products that have been recorded in PRODUCTIONRECORD.
     */
    private int auCount = 0;

    /**
     * Counts the number of VISUAL products that have been recorded in PRODUCTIONRECORD.
     */
    private int viCount = 0;

    /**
     * Counts the number of AUDIO_MOBILE products that have been recorded in PRODUCTIONRECORD.
     */
    private int amCount = 0;

    /**
     * Counts the number of VISUAL_MOBILE products that have been recorded in PRODUCTIONRECORD.
     */
    private int vmCount = 0;

    /**
     * Shows all of the products that have been added.
     */
    private final ObservableList<Product> productLine = FXCollections.observableArrayList();

    /**
     * Shows all of the production logs that have been recorded.
     */
    private final ArrayList<ProductionRecord> productLog = new ArrayList<>();

    /**
     * Stores user input for new products.
     */
    private final ArrayList<Product> recordProduct = new ArrayList<>();

    /**
     * Stores newly recorded products.
     */
    private final ArrayList<ProductionRecord> productionRun = new ArrayList<>();

    /**
     * Stores the product serial numbers.
     */
    private final ArrayList<ProductionRecord> serialNumber = new ArrayList<>();

    /**
     * Stores the production date.
     */
    private final ArrayList<ProductionRecord> productionDate = new ArrayList<>();

    /**
     * Stores the employees information.
     */
    private final ArrayList<Employee> employees = new ArrayList<>();


    /**
     * Insert added product into database.
     * Shows an error message if the manufacturer is less than 3 characters.
     * Clear the text fields after user added product.
     *
     * @param event User click the 'Add Product' button.
     */
    @FXML
    void addProduct(ActionEvent event) {
        manufacturerError.setText("");
        txtSuccess.setText("");
        if (txtManufacturer.getLength() < 3) {
            manufacturerError.setText("At least 3 characters");
            txtManufacturer.clear();
        } else {
            recordProduct.add(new Product(txtpro_name.getText(),
                    txtManufacturer.getText(), cbType.getValue()));
            txtManufacturer.clear();
            txtpro_name.clear();
            loadProductList();
        }
    }

    /**
     * Connect and run the database.
     * Shows added items on the TableView and ListView.
     */
    public void loadProductList() {
        connectToDb();
        tableView.setItems(productLine);
        listView.setItems(productLine);
    }

    /**
     * Sets up the TableView.
     */
    @SuppressWarnings("unchecked")
    public void setupProductionLineTable() {
        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));
        manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    }

    /**
     * Stores the selected product from the ListView and quantity from the comboBox.
     * Shows an error message if the user didn't select a product.
     * Passes the selected product and quantity to addToProductionDB.
     *
     * @param event        User click the 'Record Production' button.
     * @param recordItem   It gets the selected product from the ListView.
     * @param countProduct It gets the selected quantity from the comboBox.
     */
    @FXML
    void RecordProduction(ActionEvent event) {
        txtSuccess.setText("");
        ObservableList<Product> recordItem;
        int countProduct = Integer.parseInt(cmbQuantity.getValue());
        recordItem = listView.getSelectionModel().getSelectedItems();
        selectProduct.setText("");
        txtSuccessRecord.setText("");

        if (recordItem.isEmpty()) {
            selectProduct.setText("Please select a product");
        } else {
            addToProductionDB(recordItem, countProduct);
            loadProductionLog();
        }

    }

    /**
     * Generates serial numbers that depend on the product type.
     * Record the quantity of each product type.
     *
     * @param countProduct It gets the selected quantity from the comboBox.
     * @param recordItem   It gets the selected product from the ListView.
     */
    public void addToProductionDB(ObservableList<Product> recordItem, int countProduct) {
        productionRun.clear();
        serialNumber.clear();
        productionDate.clear();

        for (int i = 0; i < countProduct; i++) {

            productionDate.add(new ProductionRecord(recordItem.get(0).getID()));

            if (recordItem.get(0).getType().getCode().equals("AU")) {
                serialNumber.add(new ProductionRecord(new Product(recordItem.get(0).getName(),
                        recordItem.get(0).getManufacturer(), recordItem.get(0).getType()), auCount));
                productionRun.add(new ProductionRecord(recordItem.get(0).getName(),
                        serialNumber.get(i).getSerialNum(), productionDate.get(i).getProdDate()));
                auCount++;
            } else if (recordItem.get(0).getType().getCode().equals("AM")) {
                serialNumber.add(new ProductionRecord(new Product(recordItem.get(0).getName(),
                        recordItem.get(0).getManufacturer(), recordItem.get(0).getType()), amCount));
                productionRun.add(new ProductionRecord(recordItem.get(0).getName(),
                        serialNumber.get(i).getSerialNum(), productionDate.get(i).getProdDate()));
                amCount++;
            } else if (recordItem.get(0).getType().getCode().equals("VI")) {
                serialNumber.add(new ProductionRecord(new Product(recordItem.get(0).getName(),
                        recordItem.get(0).getManufacturer(), recordItem.get(0).getType()), viCount));
                productionRun.add(new ProductionRecord(recordItem.get(0).getName(),
                        serialNumber.get(i).getSerialNum(), productionDate.get(i).getProdDate()));
                viCount++;
            } else {
                serialNumber.add(new ProductionRecord(new Product(recordItem.get(0).getName(),
                        recordItem.get(0).getManufacturer(), recordItem.get(0).getType()), vmCount));
                productionRun.add(new ProductionRecord(recordItem.get(0).getName(),
                        serialNumber.get(i).getSerialNum(), productionDate.get(i).getProdDate()));
                vmCount++;
            }
        }
    }

    /**
     * Connects to the database and call showProduction method.
     */
    public void loadProductionLog() {
        connectToDb();
        showProduction();
    }

    /**
     * Shows all of the production logs that are in the productlog.
     * After shows the production logs, clear anything in productlog ArrayList.
     */
    public void showProduction() {
        for (ProductionRecord productionRecord : productLog) {
            txtProductionRecord.appendText(productionRecord.toString() + "\n");
        }
        productLog.clear();

    }

    /**
     * Generates and shows employees information.
     *
     * @param event User clicks the 'Generate' button.
     */
    @FXML
    void generateButton(ActionEvent event) {
        employees.add(new Employee(txtFullName.getText(), txtPassword.getText()));
        textareaShowInformation.appendText(employees.get(0).toString());
        employees.clear();

        txtGenerateSuccess.setText("Generate user successfully!");
    }

    /**
     * Populate with value 1-10 in the ComboBox,and allow users to enter values in the ComboBox.
     * Calls the loadProductList to show products that have been added to the database.
     */
    public void initialize() {
        //Add Item type to the ChoiceBox.

        for (ItemType it : ItemType.values()) {
            cbType.getItems().add(it);
        }

        cbType.getSelectionModel().selectFirst();

        cmbQuantity.setEditable(true);

        for (int i = 1; i <= 10; i++) {
            cmbQuantity.getItems().add(String.valueOf(i));
        }
        cmbQuantity.getSelectionModel().selectFirst();

        setupProductionLineTable();
        loadProductList();
        showProduction();
    }


    /**
     * Connect to the Database, allow users to add products
     * and print out full list of products to the console.
     */
    public void connectToDb() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/database";

        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();


            //Add product to the PRODUCT database
            if (recordProduct.size() > 0 && counter != recordProduct.size()) {
                String sql = "INSERT INTO PRODUCT SET  NAME = ?, TYPE = ?, MANUFACTURER = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(2, recordProduct.get(recordProduct.size() - 1).getType().toString());
                preparedStatement.setString(3, recordProduct.get(recordProduct.size() - 1).getManufacturer());
                preparedStatement.setString(1, recordProduct.get(recordProduct.size() - 1).getName());

                preparedStatement.executeUpdate();

                txtSuccess.setText("Add product successfully!");
            }

            String mysql = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(mysql);

            int id;
            String type = "";
            String manufacturer = "";
            String name = "";

            //Print out the products that are in the database
            if (recordProduct.size() == 0 && counter == 0) {
                while (rs.next()) {
                    id = rs.getInt(1);
                    type = rs.getString(3);
                    manufacturer = rs.getString(4);
                    name = rs.getString(2);

                    productLine.add(new Product(id, name, manufacturer, ItemType.valueOf(type)));
                }
                counter = 2;
            }
            //Print out the product that just added to the database.
            else if (recordProduct.size() > 0 && counter != recordProduct.size()) {
                rs.last();
                id = rs.getInt(1);
                type = rs.getString(3);
                manufacturer = rs.getString(4);
                name = rs.getString(2);

                productLine.add(new Product(id, name, manufacturer, ItemType.valueOf(type)));

                counter = recordProduct.size();
            }

            //Add recorded product to the PRODUCTIONRECORD database
            if (productionRun.size() > 0) {
                String sql = "INSERT INTO PRODUCTIONRECORD SET  PRODUCT_NAME = ?, SERIAL_NUM = ?, DATE_PRODUCED = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                for (ProductionRecord productionRecord : productionRun) {
                    preparedStatement.setString(1, productionRecord.getName());
                    preparedStatement.setString(2, productionRecord.getSerialNum());
                    preparedStatement.setTimestamp(3, new Timestamp(productionRecord.getProdDate().getTime()));

                    preparedStatement.executeUpdate();

                    txtSuccessRecord.setText("Record product successfully!");
                }

            }

            //Print out recorded products that are in the database
            String sqlRecord = "SELECT * FROM PRODUCTIONRECORD";
            ResultSet rsRecord = stmt.executeQuery(sqlRecord);

            int productionNum = 0;
            String productName = "";
            String serialNumber = "";
            Date dateProduced;


            if (productionRun.size() == 0 && row == 0) {
                while (rsRecord.next()) {
                    productionNum = rsRecord.getInt(1);
                    productName = rsRecord.getNString(2);
                    serialNumber = rsRecord.getNString(3);
                    dateProduced = rsRecord.getTimestamp(4);

                    productLog.add(new ProductionRecord(productionNum, productName, serialNumber, dateProduced));
                    row++;
                    if (serialNumber.substring(3, 5).equals("AU")) {
                        auCount++;
                    } else if (serialNumber.substring(3, 5).equals("AM")) {
                        amCount++;
                    } else if (serialNumber.substring(3, 5).equals("VI")) {
                        viCount++;
                    } else {
                        vmCount++;
                    }
                }

            }
            //Print out recorded products that just added to the database
            else if (productionRun.size() > 0) {

                rsRecord.absolute(row + 1);
                for (int i = 0; i < productionRun.size(); i++) {
                    productionNum = rsRecord.getInt(1);
                    productName = rsRecord.getNString(2);
                    serialNumber = rsRecord.getNString(3);
                    dateProduced = rsRecord.getTimestamp(4);

                    row++;
                    productLog.add(new ProductionRecord(productionNum, productName, serialNumber, dateProduced));
                    rsRecord.next();
                }

            }


            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}