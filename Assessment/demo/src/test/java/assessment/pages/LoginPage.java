package assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage 
{
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

   // Logger logger = Logger.getLogger(LoginPage.class.getName());
   
     By lblXyzBank = By.xpath("//strong[@class='mainHeading']");
     By lblYourName = By.xpath("//label[contains(text(),'Your Name')]");
     By drpDwnUserName = By.xpath("//select[@id='userSelect']");
     By btnLogin = By.xpath("//button[text()='Login']");
    
    public void loginToBankApplication(String userRole) throws InterruptedException
    {
        WebElement btnLogin=driver.findElement(By.xpath("//button[text()='"+userRole+"']"));
        try {
            if(driver.findElement(lblXyzBank).isDisplayed()==true){
               btnLogin.click();
               Thread.sleep(3000);
            }
        } catch (Exception e) {
            System.out.println("Exception : "+e.getMessage());
        }  
    }
}
