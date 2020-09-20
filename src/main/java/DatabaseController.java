import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * This class implements a simple Database Controller.
 *
 * @author Michelle Quan
 */
public class DatabaseController {

    @FXML
    private Button eventHandler;

    @FXML
    private Button RecordProduction;

    @FXML
    private ComboBox<String> cmbQuantity;

    @FXML
    private ChoiceBox<String> cbType;

    @FXML
    private TextField txtpro_name;

    @FXML
    private TextField txtManufacturer;

    /**
     * Connect and run the database every time
     * this event happens.
     *
     * @param event User click the
     *              'Add Product' bottom.
     */
    @FXML
    void addProduct(ActionEvent event) {
        connectToDb();

    }

    /**
     * Populate with value 1-10 in the ComboBox,
     * and allow users to enter values in the ComboBox.
     */
    public void initialize() {
        //Add 'AUDIO' to the ChoiceBox.
        cbType.getItems().add("AUDIO");

        cmbQuantity.getSelectionModel().selectFirst();
        cmbQuantity.setEditable(true);

        for (int i = 1; i <= 10; i++) {
            cmbQuantity.getItems().add(String.valueOf(i));
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
            String type = cbType.getValue();


            String sql = "INSERT INTO PRODUCT SET TYPE = ?, MANUFACTURER = ?, NAME = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //Add product to the database
            preparedStatement.setString(1, type);
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