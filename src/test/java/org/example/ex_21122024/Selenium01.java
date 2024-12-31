package org.example.ex_21122024;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.Assert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Selenium01 {

    @Description("Verify Cura Login Screen elements")
    @Test
    public void OpenBrowser() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            options.addArguments("--disable-infobars");
            options.addArguments("--version");
            //options.getPlatformName();


            WebDriver dr = new ChromeDriver(options);
            dr.get("https://katalon-demo-cura.herokuapp.com/");

           String actTitle =dr.getTitle();
           Assert.assertEquals(actTitle,"CURA Healthcare Service");
            dr.findElement(By.id("btn-make-appointment")).click();
            Thread.sleep(1000);

            // AssertJ Assertion
            assertThat(dr.getCurrentUrl()).isNotBlank().isNotEmpty().isEqualTo("https://katalon-demo-cura.herokuapp.com/profile.php#login");

            dr.getCurrentUrl();
            dr.findElement(By.id("txt-username")).sendKeys("John Doe");
            dr.findElement(By.name("password")).sendKeys("ThisIsNotAPassword");
            dr.findElement(By.id("btn-login")).click();
            assertThat(dr.getCurrentUrl()).isNotBlank().isNotEmpty().isEqualTo("https://katalon-demo-cura.herokuapp.com/#appointment");
            System.out.println(dr.getCurrentUrl());
            Thread.sleep(3000);
            dr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
