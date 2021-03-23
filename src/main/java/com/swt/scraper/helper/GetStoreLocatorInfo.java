package com.swt.scraper.helper;

import com.swt.scraper.model.Details;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class GetStoreLocatorInfo {

    public static String driverPath = "D:\\temp\\driver\\chromedriver.exe";

    ChromeDriver driver;

    public List<Details> getStoreDetails(int exitIndex){

        System.setProperty("webdriver.chrome.driver",driverPath);

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://carcarestores.3mindia.co.in/store-locator.aspx");


        WebElement city = driver.findElementByXPath("//select[@id='ctl00_ContentPlaceHolder1_ddlCity']");

        new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlCity']")));

        Select selectCity = new Select(city);

        List<WebElement> allCity = selectCity.getOptions();
        List<String>  cityNames = new ArrayList<>();

        for(WebElement element:allCity){
            if(!element.getText().equals("-Select City-")) {
                cityNames.add(element.getText());
            }
        }




        List<Details>  detailsList = new  ArrayList<>();


        int index = 0;
        for(String cityName : cityNames){

            System.out.println("City : "+cityName);

            /*driver.findElementByXPath("//select[@id='ctl00_ContentPlaceHolder1_ddlCity']").click();
            pause(3);
            driver.findElementByXPath("//option[text()='"+cityName+"']").click();*/
            new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlCity']")));

            Select cityDropdown = new Select(driver.findElementByXPath("//select[@id='ctl00_ContentPlaceHolder1_ddlCity']"));

            cityDropdown.selectByVisibleText(cityName);

            waitForPageLoad(15);

            WebElement store = driver.findElementByXPath("//select[@id='ctl00_ContentPlaceHolder1_ddlStores']");

            new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlStores']")));

            Select selectStore = new Select(store);

            List<WebElement> allStore = selectStore.getOptions();
            List<String>  storeNames = new ArrayList<>();

            for(WebElement element:allStore){
                if(!element.getText().equals("--Select--")) {
                    storeNames.add(element.getText());
                }
            }

            pause(4);

            for(String storeName : storeNames) {



                System.out.println("Store : "+storeName);

                /*driver.findElementByXPath("//select[@id='ctl00_ContentPlaceHolder1_ddlStores']").click();
                pause(3);
                driver.findElementByXPath("//option[text()='"+storeName+"']").click();*/

                new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlStores']")));

                Select storeDropdown = new Select(driver.findElementByXPath("//select[@id='ctl00_ContentPlaceHolder1_ddlStores']"));

                storeDropdown.selectByVisibleText(storeName);

                waitForPageLoad(15);

                pause(5);

                driver.findElementById("ctl00_ContentPlaceHolder1_imgbtnGo").click();

                waitForPageLoad(20);

                new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='store_content_container col-sm-6']/p")));
                String address = getTextByXpath("//div[@class='store_content_container col-sm-6']/p");
                String email = getTextByXpath("//div[@class='store_content_container col-sm-6']/p[2]").replace("Email ID:","").trim();
                String phoneNumber = getTextByXpath("//div[@id='ctl00_ContentPlaceHolder1_divPhoneNumber']").replace("Phone No -","").trim();
                String tollFreeNumber = getTextByXpath("//div[@id='ctl00_ContentPlaceHolder1_divPhoneNumber']/div").replace("Toll Free Number -","").trim();
                String[] timeAndDays = getTextByXpath("//div[@id='store_locator_mid_div']/span/p").split("\n");
                String time = "";
                String days = "";
                if(timeAndDays.length == 3 ){
                    time = timeAndDays[1];
                    days = timeAndDays[2];
                }



                detailsList.add(new Details(cityName,storeName,address,email,phoneNumber,tollFreeNumber,time,days));

                waitForPageLoad(20);
            }

            index++;
            if(index == exitIndex){
                break;
            }

        }


        driver.quit();

        return detailsList;

    }

    public void pause(int sec){
        try{
            System.out.println("wait for "+sec+" sec...");
            Thread.sleep(sec * 1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTextByXpath(String xpath){

        try {
            return driver.findElementByXPath(xpath).getText();
        }catch (NoSuchElementException e){
            return "";
        }
    }

    public void waitForPageLoad(int timeout){
        /*
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete") ;
            }
        };

        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(pageLoadCondition);
        */

        new WebDriverWait(driver,timeout).until(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {

                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete") ;
                    }
                });
    }
}
