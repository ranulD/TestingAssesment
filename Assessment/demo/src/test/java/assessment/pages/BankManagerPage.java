package assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BankManagerPage 
{
    WebDriver driver;

    public BankManagerPage(WebDriver driver){
        this.driver=driver;
    }

     By btnAddCustomer = By.xpath("//button[contains(text(),'Add Customer')]");
     By btnCustomers = By.xpath("//button[contains(text(),'Customers')]");
    

     public void clickOnTab(String tabName) throws InterruptedException
     {
        WebElement btnTab=driver.findElement(By.xpath("//button[contains(text(),'"+tabName+"')]"));
        btnTab.click();
        Thread.sleep(3000);
     }


}
