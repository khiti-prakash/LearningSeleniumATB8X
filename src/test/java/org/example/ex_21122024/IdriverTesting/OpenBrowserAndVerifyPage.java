package org.example.ex_21122024.IdriverTesting;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OpenBrowserAndVerifyPage {

    @Description("Verify Free Trial")
    @Test
    public void VerifyMessage() throws InterruptedException {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");
        option.addArguments("--disable-infobars");
        option.addArguments("--disable-notifications");
        option.addArguments("--headless");

        WebDriver driver = new ChromeDriver(option);
        driver.navigate().to("https://www.idrive360.com/enterprise/login");

        //Verify Login pageTitle
        verifyTitle(driver, "IDrive® 360 - Sign in to your account");


        /*
        1. Entering Email id
        2. Entering Password
        3. Entering Submit button
         */
        driver.findElement(By.id("username")).sendKeys("augtest_040823@idrive.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.className("id-hide-pwd")).click();
        driver.findElement(By.xpath("//button[starts-with(text(),'Sign in')]")).click();

        //Verify Account Details Page Title
        verifyTitle(driver, "IDrive 360 account details");

        //verify Header Part Expire
        int i = 0;
        int wait = 1000;
        while (wait > 0) {
            try {
                WebElement aaa = driver.findElement(By.xpath("//div[@id='upgrade']"));
                String attributeValue = aaa.getDomAttribute("class");
                if (attributeValue.equals("id-upgrade-info id-expire-date id-hide id-tkn-btn id-expired-acc")) {
                    throw new NullPointerException();
                }
                break;
            } catch (NullPointerException x) {
                if (wait > 1) {
                    try {
                        Thread.sleep(wait);
                    } catch (InterruptedException ex) {
                        Thread.sleep(wait);
                    }
                }
            }
            wait--;
        }

        WebElement headerPart_element = driver.findElement(By.xpath("//button[@class='id-btn id-warning-btn-drk id-tkn-btn']//preceding-sibling::span"));
        String headerPart_title = headerPart_element.getText();

        if (headerPart_title.equals("Your free trial has expired")) {
            System.out.println(headerPart_title + ":-" + "Message is present in Header part");
        } else {
            throw new RuntimeException();
        }

        int j = 0;
        int wait1 = 1000;
        while (wait1 > 0)
        {
            try {
                WebElement expireMessage_element = driver.findElement(By.cssSelector("div.id-card-blk.id-expire-msg.id-expire-msg-nw.failure h5"));

                assertThat(expireMessage_element.getText()).isNotNull().isEqualTo("Your free trial has expired");

                System.out.println(expireMessage_element.getText() + ":-" + "Message is present in Body part");

                List<WebElement> expireMessages_element = driver.findElements(By.xpath("//div[@id='expiredmsg']//div[@class='id-card-cont']//p"));

                for(WebElement ele : expireMessages_element)
                {
                  System.out.println(ele.getText());
                }
                break;
            }
        catch(AssertionError | Exception e)
            {
                if (wait1 > 1) {
                    try {
                        Thread.sleep(wait);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            wait1--;
        }
        Thread.sleep(3000);
        driver.quit();
    }

    public static String verifyTitle(WebDriver driver,String expectedTitle)
    {
        int i = 0;
        int wait = 1000;
        while (wait > 0) {
            try {

                assertThat(driver.getTitle()).isNotEmpty().isNotBlank().isEqualTo(expectedTitle);
                return expectedTitle;
            } catch (AssertionError | Exception e) {
                if (wait > 1)
                {
                    try {
                        Thread.sleep(wait);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            wait--;
        }
        return null;
    }


}
