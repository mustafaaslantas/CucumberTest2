package com.mustafaaslantas.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

public class DriverFactory {

    static WebDriver driver;
    static Properties properties;

    public static WebDriver initializeDriver(String browser){
        properties = ConfigReader.getProperties();
        System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
        driver = new ChromeDriver();
        String url = properties.getProperty("url");
        driver.get(url);
        driver.manage().window().maximize();
        return getDriver();
    }
    public static WebDriver getDriver(){
        return driver;

    }
}
