package load;


import java.io.FileInputStream;
import java.util.Properties;

public class LoadBase {

    public Properties properties;
    public final String PATH = System.getProperty("user.dir") + "/src/main/resources/secret.properties";

    public LoadBase() {

        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(PATH);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

