package ich.configs;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class ConfigManager {
	
	private final static Logger logger = LoggerFactory.getLogger(ConfigManager.class);

	private final static ConfigManager cm = new ConfigManager();
	
	private Properties properties;

	private ConfigManager(){
		
		properties = new Properties();
		String configFile=new String();
		try{
			configFile = "config" + File.separator + "config.properties";
			logger.info("Loading config from the path : {}",configFile);
			properties.load(new FileReader(configFile));
		}catch(Exception e){
			logger.error("Unable to load config.properties file from the path " + configFile ,e);
		}
		
	}
	
	public static ConfigManager getInstance(){
		return cm;
	}
	public String getProperty(String name){
		return properties.getProperty(name);
	}	
}
