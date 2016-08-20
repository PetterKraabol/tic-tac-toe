package com.kraabol.petter.tic_tac_toe.shared;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class Config {
	private static Config instance = null;
	
	private String filename = "src/main/resources/config.ini";
	private String encoding = "UTF-8";
	private Map<String, String> properties = new HashMap<String, String>();
	
	public Config() {
		this.loadConfigFromFile(this.filename);
	}
	
	public static synchronized Config getInstance() {
		if (instance == null) instance = new Config();
		return instance;
	}
	
	public String getProperty(String property) {
		return this.properties.get(property);
	}
	
	public void setProperty(String property, String value) {
		
		// Put (or update) the property
		this.properties.put(property.trim(), value.trim());
		
		// Save changes to file
		this.saveConfigToFile(this.properties, this.filename);
	}
	
	private void loadConfigFromFile(String filename) {
		
		// Try opening a stream to filename
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			
			// Read file stream, ignore #comments and properties with no value
			stream.filter((String line) -> !line.startsWith("#") && line.split("=").length == 2)
				  .forEach((String line) -> this.setProperty(line.split("=")[0], line.split("=")[1]));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveConfigToFile(Map<String, String> properties, String filename) {
		Path configFile = Paths.get(filename);
		List<String> propertyList = new ArrayList<String>();
		
		// Add config properties to a list
		properties.forEach((String key, String value) -> propertyList.add(key + "=" + value));
		
		try {
			
			// Write config properties to file
			Files.write(configFile,  propertyList,  Charset.forName(this.encoding));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
