import cep.esper.EsperServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import data.CsvReader;

public class SmartTrafficMain {

    public static void main(String[] args) {
        System.out.println("Start Programm");
        EPStatement statement = EsperServiceProvider.getInstance().getEPAdministrator().createEPL("select type from TrafficEvent");
        statement.addListener(new UpdateListener() {
            public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
                System.out.println("Event: " + eventBeans[0].getUnderlying());
            }
        });
        EPStatement averagePerMinute = EsperServiceProvider.getInstance().getEPAdministrator().createEPL("select count(*) from TrafficEvent#time(5 sec)");
        averagePerMinute.addListener(new UpdateListener() {
            public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
                System.out.println("number Per 10 seconds: " + eventBeans[0].getUnderlying());
            }
        });
        CsvReader csvReader = new CsvReader("/datasets/trafficData.csv");
    }

}
