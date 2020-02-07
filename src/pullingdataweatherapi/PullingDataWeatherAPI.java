/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pullingdataweatherapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;




/**
 *
 * @author juank
 */
public class PullingDataWeatherAPI {
	
	public static Map<String, Object> jsonToMap(String str){
		Map<String, Object> map = new Gson().fromJson(
				str, new TypeToken<HashMap<String, Object>>() {}.getType()
				);
		return map;
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	String API_KEY ="1c0bc0e63c2e6d1b7f786f0793569ec8";
    	String LOCATION = "Miami,USA";
    	String urlAddress ="http://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&appid="+API_KEY+"&units=imperial";
    	
		 try {
	        	StringBuffer result = new StringBuffer();
	        	URL url = new URL(urlAddress);
	        	URLConnection connect = url.openConnection();
	        	InputStreamReader input = new InputStreamReader(connect.getInputStream());	        	
	        	BufferedReader reader = new BufferedReader(input);
	        	
	        	String line;
	        	
	        	while((line = reader.readLine())  != null) {
	        		
	        		result.append(line);        		
	        	}
	        	reader.close();
	        	System.out.println(result);  
	        	
	        	
	        	Map<String, Object>respMap = jsonToMap(result.toString());
	        	List<Map<String, Object >> weather = (List<Map<String, Object>>) (respMap.get("weather"));
	        	Map<String, Object> Sky = weather.get(0);
	        	Map<String, Object>Clouds = jsonToMap(respMap.get("clouds").toString());
	        	Map<String, Object>mainMap = jsonToMap(respMap.get("main").toString());
	        	Map<String, Object>windMap = jsonToMap(respMap.get("wind").toString());
	        	
	        	
	        	System.out.println("Temperature "+mainMap.get("temp"));
	        	System.out.println("Sky "+ Sky.get("description"));
	        	System.out.println("Clouds "+Clouds.get("all")+"%");
	        	System.out.println("Humidiy "+mainMap.get("humidity"));
	        	System.out.println("Wind Speed "+windMap.get("speed"));
	        	System.out.println("wind Direction "+windMap.get("deg"));        	
	        	
	        } catch (IOException e) {
	        	System.out.println(e.getMessage());
	        }
    	
    	
    }
    
}
