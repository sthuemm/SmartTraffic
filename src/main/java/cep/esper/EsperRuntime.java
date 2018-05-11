package cep.esper;

import cep.ComplexEventProcessingEngine;
import com.espertech.esper.client.EPRuntime;


public class EsperRuntime implements ComplexEventProcessingEngine {

    private static EPRuntime instance;

    private EsperRuntime(){}

    public static EPRuntime getInstance(){
        if(instance == null){
            instance = getRuntime();
        }
        return instance;
    }


    private static EPRuntime getRuntime(){
        return EsperServiceProvider.getInstance().getEPRuntime();
    }




}
