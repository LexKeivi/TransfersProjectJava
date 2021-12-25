import java.sql.*;

public class DataBase {
    //Connection connection;
    String url = "jdbc:sqlite:src\\db\\transfers.db";

    private Connection connect() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Connection success");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    public void createNewTable(){
        String sql = "CREATE TABLE IF NOT EXISTS transfers (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	period VARCHAR(50) NOT NULL,\n"
                + "	dataValue DECIMAL(20,10), \n"
                + "	units VARCHAR(50) NOT NULL \n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertData(String period, double dataValue, String units) {
        //String sql = "DROP TABLE transfers ";
        String sql = "INSERT INTO transfers (period, dataValue, units) VALUES(?, ?, ?)";

        try (Connection conn = this.connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, period);
                pstmt.setDouble(2, dataValue);
                pstmt.setString(3, units);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

