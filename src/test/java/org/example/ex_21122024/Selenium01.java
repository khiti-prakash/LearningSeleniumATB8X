package org.example.ex_21122024;
import io.qameta.allure.Description;
import org.testng.Test;
import org.openqa.selenium.edge.EdgeDriver;



public class Selenium01 {

    @Description("TC :- 1")
    @Test
    public void OpenBrowser()
    {
        EdgeDriver dr = new EdgeDriver();
        dr.get("https://app.vabro.com");
    }
}
