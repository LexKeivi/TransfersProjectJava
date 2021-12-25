import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transfer {
    String period;
    Double dataValue;
    private String units;


    public Transfer(String period, Double dataValue, String units){
        this.period = period;
        this.dataValue = dataValue;
        this.units = units;
    }
}
