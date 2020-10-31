//import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
//import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * This class implements a simple Database Controller.
 *
 * @author Michelle Quan
 */
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

    private ObservableList<Product> productLine = FXCollections.observableArrayList();
    private ArrayList<ProductionRecord> productionRecord = new ArrayList<>();

    /**
     * Connect and run the database every time
     * this event happens.
     *
     * @param event User click the
     *              'Add Product' bottom.
     */
    @FXML
    void addProduct(ActionEvent event) {
        //connectToDb();
        productLine.add(new Product(txtpro_name.getText(),
                txtManufacturer.getText(), cbType.getValue()));
    }

    @FXML
    void RecordProduction(ActionEvent event) {

        int num = 0;
        while(num < Integer.valueOf(cmbQuantity.getValue())){
            productionRecord.add(new ProductionRecord(0,0,"0", new Date()));
            num++;
        }

        for(int i = 0; i < productionRecord.size(); i++){
            txtProductionRecord.appendText(productionRecord.get(i).toString() + "\n");
        }
        productionRecord.clear();
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

        cmbQuantity.setEditable(true);

        for (int i = 1; i <= 10; i++) {
            cmbQuantity.getItems().add(String.valueOf(i));
            if(i == 1){
                cmbQuantity.getSelectionModel().selectFirst();
            }
        }

        setupProductionLineTable();
        tableView.setItems(productLine);
        listView.setItems(productLine);

        //testMultimedia();
    }


    public void setupProductionLineTable(){
        //idCol.setCellValueFactory(new PropertyValueFactory("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));
        manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    }


    public static void testMultimedia() {
        AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
                "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
        Screen newScreen = new Screen("720x480", 40, 22);
        MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
                MonitorType.LCD);
        ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
        productList.add(newAudioProduct);
        productList.add(newMovieProduct);
        for (MultimediaControl p : productList) {
            System.out.println(p);
            p.play();
            p.stop();
            p.next();
            p.previous();
        }
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

            //Get the values from the user interface
            String pro_name = txtpro_name.getText();
            String manufact = txtManufacturer.getText();
            ItemType type = cbType.getValue();


            String sql = "INSERT INTO PRODUCT SET TYPE = ?, MANUFACTURER = ?, NAME = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //Add product to the database
            preparedStatement.setString(1, type.getCode());
            preparedStatement.setString(2, manufact);
            preparedStatement.setString(3, pro_name);

            preparedStatement.executeUpdate();



            String print_product = "SELECT * FROM PRODUCT";

            ResultSet rs = stmt.executeQuery(print_product);

            //print out full list of products to the console.
            while (rs.next()) {
                System.out.println(rs.getString(1) + " "
                        + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4));
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