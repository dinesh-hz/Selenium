package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Config_Manager {
	
	public static Properties properties = new Properties();


	public void file_reader() throws FileNotFoundException {

		FileInputStream file = new FileInputStream("./src/test/resources/config.properties");


		try {

			properties.load(file);
			

		} catch (Exception e) {

			e.printStackTrace();
		}



	}
	
	public String getProperty(String value) {
		
	return	properties.getProperty(value);

	}

}
