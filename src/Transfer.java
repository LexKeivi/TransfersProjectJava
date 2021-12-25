public class Transfer {
    String period;
    Double dataValue;
    private final String units;


    public Transfer(String period, Double dataValue, String units){
        this.period = period;
        this.dataValue = dataValue;
        this.units = units;
    }
}
