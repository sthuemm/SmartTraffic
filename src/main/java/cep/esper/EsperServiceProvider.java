package cep.esper;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EsperServiceProvider {

    private static EPServiceProvider serviceProvider;

    private EsperServiceProvider(){}

    public static EPServiceProvider getInstance(){
        if(serviceProvider == null){
            serviceProvider = EPServiceProviderManager.getDefaultProvider(getConfiguration());
        }

        return serviceProvider;
    }

    private static Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addEventType("TrafficEvent", getEventProperties());
        return configuration;
    }

    private static Map<String, Object> getEventProperties(){
        Map<String, Object> eventProperties = new HashMap<String, Object>();
        eventProperties.put("type", String.class);
        eventProperties.put("timestamp", Date.class);

        return eventProperties;
    }

}
