package org.example.ex_21122024.CureHealthServiceTesting;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CureHealthServicePageVerify {

    @Description("Login Cure Health and verify page title")
    @Test
    public void VerifyCureHealtheServicePage() throws InterruptedException {
        FirefoxOptions firefoxOpt = new FirefoxOptions();
        firefoxOpt.addPreference("dom.webnotifications.enabled", false);


        WebDriver driver = new FirefoxDriver(firefoxOpt);
        driver.manage().window().maximize();

        driver.get("https://katalon-demo-cura.herokuapp.com/");

        verifyTitleOfPage(driver, "CURA Healthcare Service");
        getCurrentUrl(driver, "https://katalon-demo-cura.herokuapp.com/");

        webElement(driver,"linkText","Make Appointment").click();

        getCurrentUrl(driver, "https://katalon-demo-cura.herokuapp.com/profile.php#login");

        Thread.sleep(2000);
        webElement(driver,"id","txt-username").click();
        webElement(driver,"id","txt-username").sendKeys("John Doe");

        webElement(driver,"name","password").click();
        webElement(driver,"name","password").sendKeys("ThisIsNotAPassword");

        webElement(driver,"cssSelector","button[id='btn-login']").click();

        getCurrentUrl(driver, "https://katalon-demo-cura.herokuapp.com/#appointment");

        webElement(driver,"id","chk_hospotal_readmission").click();
     try
     {
       List<WebElement> radioBtnType = driver.findElements(By.xpath("//label[@class='radio-inline']"));

    for (WebElement ele : radioBtnType)
    {
        if (ele.getText().contains("Medicaid"))
        {
            ele.click();
            break;
        }
    }
       webElement(driver, "cssSelector", "input[name='visit_date']").sendKeys("01/01/2025");
       webElement(driver, "cssSelector", "textarea[class='form-control']").sendKeys("Automation Testing Using Selenium");
       webElement(driver, "cssSelector", "[class='btn btn-default']").click();
     }
     catch (Exception e)
     {
     throw new RuntimeException(e);
     }
        getCurrentUrl(driver, "https://katalon-demo-cura.herokuapp.com/appointment.php#summary");

        String message = webElement(driver, "xpath","//div[@class='col-xs-12 text-center']//h2").getText();
        System.out.println("Appointment Secuss message is:- "+message);

        webElement(driver,"linktext","Go to Homepage").click();
        getCurrentUrl(driver, "https://katalon-demo-cura.herokuapp.com/");

        driver.quit();

    }


    public static void verifyTitleOfPage(WebDriver driver, String expectedPageTitle) {

        try {
            assertThat(driver.getTitle()).isNotBlank().isNotEmpty().isEqualTo(expectedPageTitle);
            System.out.println("Page title is:- " + driver.getTitle());
        } catch (NullPointerException e) {
            System.out.println("title is Null and for batter understand please go through error message" + e.getMessage());
            throw new NullPointerException();
        }
    }

    public static void getCurrentUrl(WebDriver driver, String expectedPageUrl) {
        try {
            assertThat(driver.getCurrentUrl()).isNotBlank().isNotEmpty().isEqualTo(expectedPageUrl);
            System.out.println("Current Page Url is:- " + driver.getCurrentUrl());

        } catch (NullPointerException e) {
            System.out.println("Url is Null and for batter understand please go through error message" + e.getMessage());
            throw new NullPointerException();
        }
    }

    public static WebElement webElement(WebDriver driver, String locatorType, String locatorValue) {
        WebElement ele = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                ele = driver.findElement(By.id(locatorValue));
                break;
            case "name":
                ele = driver.findElement(By.name(locatorValue));
                break;
            case "classname":
                ele = driver.findElement(By.className(locatorValue));
                break;
            case "linktext":
                ele = driver.findElement(By.linkText(locatorValue));
                break;
            case "partiallinktext":
                ele = driver.findElement(By.partialLinkText(locatorValue));
                break;
            case "cssselector":
                ele = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "xpath":
                ele = driver.findElement(By.xpath(locatorValue));
                break;
            default:
                System.out.println("Invalid LocatorType");
                throw new IllegalArgumentException();
        }
        return ele;
    }
}

