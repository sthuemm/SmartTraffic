import cep.esper.EsperServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import data.CsvReader;


public class SmartTrafficMain {

    private final static String TRAFFIC_ARRIVED_STATEMENT = "select type from TrafficEvent";
    private final static String COUNT_TRAFFIC_WITHIN_FIVE_SECONDS = "select count(*) from TrafficEvent#time(5 sec)";

    public static void main(String[] args) {
        System.out.println("Start Programm");

        SmartTrafficMain smartTrafficMain = new SmartTrafficMain();
        smartTrafficMain.createTrafficArrivedListener();
        smartTrafficMain.createCountTrafficWithinFiveSecondsListener();

        new CsvReader("/datasets/trafficData.csv").runPlayback();
    }

    private void createTrafficArrivedListener(){
        EPStatement statement = EsperServiceProvider.getInstance().getEPAdministrator().createEPL(TRAFFIC_ARRIVED_STATEMENT);
        statement.addListener(new UpdateListener() {
            public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
                System.out.println("Event: " + eventBeans[0].getUnderlying());
            }
        });
    }

    private void createCountTrafficWithinFiveSecondsListener(){
        EPStatement statement = EsperServiceProvider.getInstance().getEPAdministrator().createEPL(COUNT_TRAFFIC_WITHIN_FIVE_SECONDS);
        statement.addListener(new UpdateListener() {
            public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
                System.out.println("number Per 10 seconds: " + eventBeans[0].getUnderlying());
            }
        });
    }

}
