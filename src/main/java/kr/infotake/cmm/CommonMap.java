package kr.infotake.cmm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 */
public class CommonMap {

    private Map<String, Object> map = new LinkedHashMap<>();

    public Object get(String key) {
        return map.get(key);
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object remove(String key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public Map<String, Object> toMap() {
        return map;
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
    
    public String toString(String key) {
        Object obj = map.get(key);
        return obj == null ? "" : obj.toString();
    }
}
