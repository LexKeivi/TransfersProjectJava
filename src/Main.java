public class Main {
    public static void main(String[] args) {

        Transfers transfers = new Transfers("src\\Переводы.csv");
        DataBase db = new DataBase();
        db.createNewTable();
        //db.insertData("",2, "");
        double i = 0;
        double len = 18184;

        for (Transfer tr:Transfers.getTransfers()) {
            db.insertData(tr.period, tr.dataValue, tr.units);
            double percent = (i++ / len) * 100;
            System.out.printf("Данные %s, %f, %s были внесены в базу данных. Процент завершения - %f процентов %n", tr.period, tr.dataValue, tr.units, percent);
        }
    }
}


