import graph.BarChartDemo;
import graph.TimeSeriesChartDemo;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {

        Transfers transfers = new Transfers("src\\Переводы.csv");

        //---------------------------------------------------------------

        DataBase db = new DataBase();
        //db.createNewTable();
        //db.insertData("",2, "");
        //db.selectData();

        double i = 0;
        double len = 18184;
        /*for (Transfer tr:Transfers.getTransfers()) {
            db.insertData(tr.period, tr.dataValue, tr.units);
            double percent = (i++ / len) * 100;
            System.out.printf("Данные %s, %f, %s были внесены в базу данных. Процент завершения - %f процентов %n", tr.period, tr.dataValue, tr.units, percent);
        }*/

        //----------------------------------------------------------------------

        /*SortedSet<String> collection = new TreeSet<>();

        for (Transfer tr:Transfers.getTransfers()) {
            collection.add(tr.period);
            System.out.println(collection.size());
        }
        System.out.println(collection);*/
        ArrayList<String> dates = db.selectData();
        //BarChartDemo.main(dates);
        TimeSeriesChartDemo.main(dates);

    }
}


