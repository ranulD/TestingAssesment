package assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomersPage 
{
    WebDriver driver;

    public CustomersPage(WebDriver driver){
        this.driver=driver;
    }

    public boolean verifyAddedCustomers(String [] firstNames,String [] lastNames,String [] postCodes) throws InterruptedException
     {
       boolean status = false;
        for(int i =0;i<firstNames.length;i++){
            WebElement customerDetails=driver.findElement(By.xpath("//td[text()='"+firstNames[i]+"']/following-sibling::td[text()='"+lastNames[i]+"']/following-sibling::td[text()='"+postCodes[i]+"']"));
            if(customerDetails.isDisplayed()==true){
                status=true;
            }
        }
        Thread.sleep(2000);

        return status;
           
     }

     public void deleteCustomers(String [] firstNames,String [] lastNames,String [] postCodes) throws InterruptedException
     {
        for(int i =0;i<firstNames.length;i++){
            WebElement customerDetails=driver.findElement(By.xpath("//td[text()='"+firstNames[i]+"']/following-sibling::td[text()='"+lastNames[i]+"']/following-sibling::td[text()='"+postCodes[i]+"']/following-sibling::td[2]/child::button[text()='Delete']"));
            if(customerDetails.isDisplayed()==true){
                customerDetails.click();
            }
        }
        Thread.sleep(2000);

    
           
     }
     


}
