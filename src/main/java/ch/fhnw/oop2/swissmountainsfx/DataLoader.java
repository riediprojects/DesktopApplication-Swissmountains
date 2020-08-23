package ch.fhnw.oop2.swissmountainsfx;

import ch.fhnw.oop2.swissmountainsfx.dao.JpaDaoFactory;
import ch.fhnw.oop2.swissmountainsfx.dao.MountainAccess;
import ch.fhnw.oop2.swissmountainsfx.model.Mountain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to setup the derby database and load data from the mountain.csv file
 * intitallay in the mountains table.
 *
 * @author Linus
 */
public class DataLoader {

    MountainAccess mountainAccess;

    DataLoader() {
        mountainAccess = JpaDaoFactory.getInstance().getMountainAccessInstance();
    }

    /**
     * Setup method for the derby database. Loads the mountain data from the
     * mountains.csv file into database if no mountain in database exists.
     */
    public void setupDatabase() {

        createDatabaseAndTable();

        if (mountainAccess.getAllMountains().isEmpty()) {
            loadMountains(getClass().getResourceAsStream("resources/data/mountains.csv"), mountainAccess);
        } else {
            System.out.println("Found " + mountainAccess.getAllMountains().size() + " mountains in the database.");
        }
    }

    /**
     * Creates a database called swissmountains at the location of the project
     * directory, if it does not already exist.<br>
     * Creates the table mountains if the table does not exist in the database.
     */
    private void createDatabaseAndTable() {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName = "swissmountains";

        // Create a new database if not existing
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";

        String USER = "nbuser";
        String PASS = "nbuser";

        Connection conn = null;

        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(connectionURL, USER, PASS);

            // Create the table it does not exist
            ResultSet rs = conn.getMetaData().getTables(null, "NBUSER", "mountains".toUpperCase(), null);
            if (rs.next()) {
                System.out.println("Table mountains exists");
            } else {
                Statement stmt = conn.createStatement();

                String createMountainTableSql = "CREATE TABLE \"MOUNTAINS\" (\n"
                        + "    \"MOUNTAIN_ID\" BIGINT not null primary key,\n"
                        + "    \"MOUNTAINNAME\" VARCHAR(255),\n"
                        + "    \"HEIGHT\" DOUBLE,\n"
                        + "    \"MOUNTAINTYPE\" VARCHAR(255),\n"
                        + "    \"REGION\" VARCHAR(255),\n"
                        + "    \"CANTONS\" VARCHAR(512),\n"
                        + "    \"RANGE\" VARCHAR(255),\n"
                        + "    \"MOUNTAINISOLATION\" DOUBLE,\n"
                        + "    \"ISOLATIONPOINT\" VARCHAR(255),\n"
                        + "    \"CAPTION\" VARCHAR(512),\n"
                        + "    \"PROMINENCEPOINT\" VARCHAR(255),\n"
                        + "    \"PROMINENCE\" DOUBLE,\n"
                        + "    \"IMAGENAME\" VARCHAR(512)\n"
                        + ")";

                stmt.executeUpdate(createMountainTableSql);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Loads all Mountains from a file and stores them into the mounatin table.
     * Call this method only if you are sure that the mountains do not already
     * exist in the database.
     *
     * @param inputSream
     * @param mountainAccess
     */
    private void loadMountains(InputStream inputSream, MountainAccess mountainAccess) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputSream, StandardCharsets.UTF_8))) {
            bufferedReader.lines().skip(1).forEach(e -> {
                String[] tokens = e.split(";");

                Mountain mountain = new Mountain();

                mountain.setMountainId(Long.parseLong(tokens[0]));
                mountain.setMountainname(tokens[1]);
                mountain.setHeight(Double.parseDouble(tokens[2]));
                mountain.setMountaintype(tokens[3]);
                mountain.setRegion(tokens[4]);
                mountain.setCantons(tokens[5]);
                mountain.setRange(tokens[6]);
                mountain.setMountainisolation(Double.parseDouble(tokens[7]));
                mountain.setIsolationpoint(tokens[8]);
                mountain.setProminence(Double.parseDouble(tokens[9]));
                mountain.setProminencepoint(tokens[10]);
                mountain.setCaption(tokens[11]);
                mountain.setImagename(mountain.getMountainId() + ".jpg");

                System.out.println("Added mountain to database: " + mountain.getMountainname());

                mountainAccess.addMountain(mountain);
            });
        } catch (IOException exception) {
            // TODO: crash and burn
        }
    }
}
