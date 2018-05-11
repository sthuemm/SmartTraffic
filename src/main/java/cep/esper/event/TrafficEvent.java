package cep.esper.event;

import java.util.Date;

public class TrafficEvent {

    Type type;

    public enum Type {
        CAR_ARRIVES,
        RAILWAY_BARRIER_CLOSES,
        RAILWAY_BARRIER_OPENS

    }

    public TrafficEvent(Type type /*,Date timeStamp*/) {
        //super(timeStamp);
        this.type = type;
    }


}
