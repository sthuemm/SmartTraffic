package data;


import cep.esper.EsperServiceProvider;
import com.espertech.esper.adapter.InputAdapter;
import com.espertech.esperio.csv.AdapterInputSource;
import com.espertech.esperio.csv.CSVInputAdapter;
import com.espertech.esperio.csv.CSVInputAdapterSpec;

public class CsvReader implements FileReader {

    private AdapterInputSource source;

    private CSVInputAdapterSpec spec;

    private InputAdapter inputAdapter;

    public CsvReader(String csvPath){
        source = new AdapterInputSource(csvPath);
        spec = new CSVInputAdapterSpec(source, "TrafficEvent");
        spec.setEventsPerSec(1);
        spec.setLooping(true);
        inputAdapter = new CSVInputAdapter(EsperServiceProvider.getInstance(),spec);
    }

    public void runPlayback(){
        if(inputAdapter != null) this.inputAdapter.start();
    }

    public AdapterInputSource getSource() {
        return source;
    }

    public void setSource(AdapterInputSource source) {
        this.source = source;
    }
}
