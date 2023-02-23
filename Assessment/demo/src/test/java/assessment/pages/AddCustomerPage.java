package assessment.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage 
{
    WebDriver driver;

    public AddCustomerPage(WebDriver driver){
        this.driver=driver;
    }

     By txtFirstName = By.xpath("//input[@placeholder='First Name']");
     By txtLastName = By.xpath("//input[@placeholder='Last Name']");
     By txtPostCode = By.xpath("//input[@placeholder='Post Code']");
     By btnAddCustomer = By.xpath("//button[@type='submit' and text()='Add Customer']");
     By btnAddCustomerTab = By.xpath("//button[@class='btn btn-lg tab btn-primary' and contains(text(),'Add Customer')]");
     By btnCustomers = By.xpath("//button[contains(text(),'Customers')]");
    
    public void addNewCustomer(String [] firstNames,String [] lastNames,String [] postCodes) throws InterruptedException
     {
        for(int i =0;i<firstNames.length;i++){
            driver.findElement(txtFirstName).sendKeys(firstNames[i]);
            Thread.sleep(3000);
            driver.findElement(txtLastName).sendKeys(lastNames[i]);
            Thread.sleep(3000);
            driver.findElement(txtPostCode).sendKeys(postCodes[i]);
            Thread.sleep(3000);
            driver.findElement(btnAddCustomer).click();
            Thread.sleep(2000);
                Alert alert = driver.switchTo().alert();
                alert.accept();
                Thread.sleep(3000);
        }
        Thread.sleep(2000);
           
     }

     public void navigateToCustomersTab() throws InterruptedException{
        driver.findElement(btnCustomers).click();
            Thread.sleep(2000);
     }

}
