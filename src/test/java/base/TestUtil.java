package base;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    public String applicationUrl;
    public Integer implicitWaitSeconds;
    public String userNameCfg;
    public String passwordCfg;
    public String picture404Cfg;

    @BeforeTest
    public void setUp() {
        String targetBrowser;
        try {
            FileInputStream configFile = new
                    FileInputStream("src/test/resources/config.properties");
            Properties config = new Properties();
            config.load(configFile);
            configFile.close();
            applicationUrl = config.getProperty("url");
            targetBrowser = config.getProperty("targetBrowser");
            implicitWaitSeconds = Integer.parseInt(config.getProperty("implicitWait"));
            userNameCfg = config.getProperty("userName");
            passwordCfg = config.getProperty("password");
            picture404Cfg = config.getProperty("picture404");

        } catch (IOException e) {
               return;
        }
        switch (targetBrowser) {
            case "chrome" :
                driver = DriverFactory.getChromeDriver(implicitWaitSeconds);
                break;
            case "firefox" :
                driver = DriverFactory.getFireFoxDriver(implicitWaitSeconds);
                break;
            case "edge" :
                driver = DriverFactory.getEdgeDriver(implicitWaitSeconds);
                break;
            default :
                throw new IllegalStateException("Unexpected value: " + targetBrowser);
        }

        driver.get(applicationUrl);
    }


    @AfterTest
    public void tearDown() {
        //   webDrv.close();
        driver.quit();
    }


}
