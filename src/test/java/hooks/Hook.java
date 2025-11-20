package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.FileInputStream;
import java.util.Properties;

import static io.restassured.RestAssured.*;
public class Hook {

	    @Before
	    public void setup() throws Exception {
	        Properties prop = new Properties();
	        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
	        prop.load(fis);
	        baseURI = prop.getProperty("baseURI");
	    }
	    
	    

	    @After
	    public void teardown() {
	        reset();
	    }
	}


