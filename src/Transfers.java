import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Transfers {
    private static List<Transfer> transfers;

    public Transfers(String path) {
        transfers = new ArrayList<>();
        try {
            List<String> strings = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
            strings.remove(0);
            strings.stream()
                    .map(s -> s.split(",", 14))
                    .forEach(Transfers::addToList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addToList(String[] strings) {
        String period = strings[1];
        String dataValue = strings[2];
        String units = strings[5];

        dataValue = dataValue.replaceAll(",", ".");
        if (dataValue.equals("")){
            dataValue = null;
        }
        System.out.println(dataValue);
        transfers.add(new Transfer(period,
                Double.parseDouble(dataValue),
                units));

    }
}
