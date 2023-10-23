package helper;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private Map<String, Object> storage;
    private static ScenarioContext scenarioContext;

    private ScenarioContext(){
        storage = new HashMap<>();
    }

    public static ScenarioContext getInstance(){
        if (scenarioContext == null){
            synchronized (ScenarioContext.class){
                scenarioContext = new ScenarioContext();
            }
        }
        return scenarioContext;
    }

    public void setContext(String key, Object value) {
        storage.put(key, value);
    }

    public Object getContext(String key){
        return storage.get(key);
    }
}
