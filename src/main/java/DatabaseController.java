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
 * This class implements a simple Database Controller.
 *
 * @author Michelle Quan
 */
@SuppressWarnings("ALL")
public class DatabaseController {

    @FXML
    private TableView<Product> tableView;

    @FXML
    private ListView<Product> listView;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> manufacturerCol;

    @FXML
    private ComboBox<String> cmbQuantity;

    @FXML
    private ChoiceBox<ItemType> cbType;

    @FXML
    private TextField txtpro_name;

    @FXML
    private TextField txtManufacturer;

    @FXML
    private TextArea txtProductionRecord;

    @FXML
    private Text manufacturerError;

    @FXML
    private Text selectProduct;

    @FXML
    private Text txtSuccessRecord;

    @FXML
    private Text txtSuccess;

    @FXML
    private Text txtGenerateSuccess;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextArea textareaShowInformation;


    private int counter = 0;
    private int row = 0;
    private int auCount = 0;
    private int viCount = 0;
    private int amCount = 0;
    private int vmCount = 0;
    private final ObservableList<Product> productLine = FXCollections.observableArrayList();
    private final ArrayList<ProductionRecord> productLog = new ArrayList<>();
    private final ArrayList<Product> recordProduct = new ArrayList<>();
    private final ArrayList<ProductionRecord> productionRun = new ArrayList<>();
    private final ArrayList<ProductionRecord> serialNumber = new ArrayList<>();
    private final ArrayList<ProductionRecord> productionDate = new ArrayList<>();
    private final ArrayList<Employee> employees = new ArrayList<>();


    /**
     * Connect and run the database every time
     * this event happens.
     *
     * @param event User click the
     *              'Add Product' bottom.
     */
    @FXML
    void addProduct(ActionEvent event) {
        manufacturerError.setText("");
        txtSuccess.setText("");
        if (txtManufacturer.getLength() < 3) {
            manufacturerError.setText("At least 3 characters");
        } else {
            recordProduct.add(new Product(txtpro_name.getText(),
                    txtManufacturer.getText(), cbType.getValue()));
            loadProductList();
        }

    }


    public void loadProductList() {
        connectToDb();
        tableView.setItems(productLine);
        listView.setItems(productLine);

    }

    @SuppressWarnings("unchecked")
    public void setupProductionLineTable() {
        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));
        manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    }

    @FXML
    void RecordProduction(ActionEvent event) {
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

    public void loadProductionLog() {
        connectToDb();
        showProduction();
    }

    public void showProduction() {
        for (ProductionRecord productionRecord : productLog) {
            txtProductionRecord.appendText(productionRecord.toString() + "\n");
        }
        productLog.clear();

    }


    @FXML
    void generateButton(ActionEvent event) {
        employees.add(new Employee(txtFullName.getText(), txtPassword.getText()));
        textareaShowInformation.appendText(employees.get(0).toString());
        employees.clear();

        txtGenerateSuccess.setText("Generate user successfully!");
    }

    /**
     * Populate with value 1-10 in the ComboBox,
     * and allow users to enter values in the ComboBox.
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


            //Add product to the database
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

            if (recordProduct.size() == 0) {
                while (rs.next()) {
                    id = rs.getInt(1);
                    type = rs.getString(3);
                    manufacturer = rs.getString(4);
                    name = rs.getString(2);

                    productLine.add(new Product(id, name, manufacturer, ItemType.valueOf(type)));
                }
                counter = 2;
            }
            if (recordProduct.size() > 0 && counter != recordProduct.size()) {
                rs.last();
                id = rs.getInt(1);
                type = rs.getString(3);
                manufacturer = rs.getString(4);
                name = rs.getString(2);

                productLine.add(new Product(id, name, manufacturer, ItemType.valueOf(type)));

                counter = recordProduct.size();
            }

            //-----------------------------------------------------------
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

            } else if (productionRun.size() > 0) {

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