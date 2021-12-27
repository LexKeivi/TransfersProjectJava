import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private final String url = "jdbc:sqlite:src\\db\\transfers.db";

    private Connection connect() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
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
                if (dataValue != -1){
                    pstmt.setString(2, String.valueOf(dataValue));
                }
                else {
                    pstmt.setString(2, null);
                }

                pstmt.setString(3, units);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<String> selectData() throws SQLException {
        ArrayList<String> datas = new ArrayList<>();
        String sql1 = "SELECT period, units, printf(\"%.2f\", sum(datavalue)) as 'Sum' FROM transfers \n" +
                "WHERE units = 'Dollars' and period like '2020%' \n" +
                "GROUP BY period";
        String sql2 = "SELECT period, units, printf(\"%.4f\", AVG(datavalue)) as 'Average data value', COUNT(datavalue) as 'Count' FROM transfers \n" +
                "WHERE units = 'Dollars' and datavalue notNULL \n" +
                "GROUP BY period";
        String sql3 = "SELECT period, Max(datavalue) as 'Max', min(datavalue) as 'Min' FROM transfers \n" +
                "WHERE (period like '2014%' or period LIKE '2016%' or period like '2020%') and units = 'Dollars' \n" +
                "GROUP BY period\n";

        try (Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql3)){
            // loop through the result set
            /*System.out.println("Date" +  "\t\t|\t\t" +
                                "Sum" + "\t\t\t\t|\t\t" +
                                "Units");
            System.out.println("----------------------------------------------------");*/
            /*System.out.println("Date" +  "\t\t|\t\t" +
                    "AVG data value" + "\t|\t\t" +
                    "Count" + "\t|\t\t" +
                    "Units");
            System.out.println("----------------------------------------------------------------------");*/
            /*System.out.println("Date" +  "\t\t|\t\t" +
                    "Max Value" + "\t|\t\t" +
                    "Min Value");
            System.out.println("----------------------------------------------------");*/
            while (rs.next()) {
                /*System.out.println(rs.getString("period") +  "\t\t|\t\t" +
                        rs.getString("max") + "\t\t|\t\t" +
                        rs.getString("min"));*/
                /*datas.add(rs.getString("period").replace(".", ",") + "," +
                        rs.getString("Average data value") + "," +
                        rs.getString("Count"));*/
                datas.add(rs.getString("period").replace(".", ",") + "," +
                        rs.getString("max") + "," +
                        rs.getString("min"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return datas;
    }
}

