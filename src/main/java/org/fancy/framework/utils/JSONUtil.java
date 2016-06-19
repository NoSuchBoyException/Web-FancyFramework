package org.fancy.framework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtil {

	private static volatile JSONUtil instance;
	
	private JSONUtil() {}
	
	public static JSONUtil getInstance() {
		JSONUtil ins = instance;
		if (null == ins) {
			synchronized (JSONUtil.class) {
				if (null == ins) {
					ins = new JSONUtil();
					instance = ins;
				}
			}
		}
		return ins;
	}
	
	/**
	 * Converted JSONObject to HashMap<String, Object>
	 * 
	 * @param json
	 *            the json object to convert
	 * @return the converted map
	 */
	public Map<String, Object> toMap(JSONObject json) {
		if (null == json) {
			return new HashMap<String, Object>(0);
		}
		
        HashMap<String, Object> map = new HashMap<String, Object>();
        Set<?> keys = json.keySet();
        
        for (Object key : keys) {
            Object o = json.get((String) key);
            
            if (o instanceof JSONArray) {
            	map.put((String) key, toList((JSONArray) o));
            } else if (o instanceof JSONObject) {
                map.put((String) key, toMap((JSONObject) o));
            } else {
                map.put((String) key, o);
            }
        }
        
        return map;
    }
 
	/**
	 * Converted JSONObject to List<Object>
	 * 
	 * @param json
	 *            the json array to convert
	 * @return the converted list
	 */
    public List<Object> toList(JSONArray json) {
    	if (null == json || 0 == json.length()) {
    		return new ArrayList<Object>(0);
    	}
    	
        List<Object> list = new ArrayList<Object>();
        
        for (Object o : json) {
            if (o instanceof JSONArray) {
                list.add(toList((JSONArray) o));
        	} else if (o instanceof JSONObject) {
                list.add(toMap((JSONObject) o));
        	} else {
                list.add(o);
        	}
        }
        
        return list;
    }
    
}
