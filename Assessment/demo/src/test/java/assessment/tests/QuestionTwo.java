package assessment.tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import assessment.pages.LoginPage;
import assessment.pages.CustomerLoginPage;

public class QuestionTwo 
{
     WebDriver driver;
     

    @BeforeTest
    public void initialize() throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Malmi\\Desktop\\Assessment\\demo\\src\\test\\resources\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }
    
    @Test(description = "Verify by performing transactions", priority = 1)
    public void performTransactions() throws InterruptedException
    {
     LoginPage login= new LoginPage(driver);
     CustomerLoginPage customerLogin = new CustomerLoginPage(driver);
     login.loginToBankApplication("Customer Login");
     customerLogin.seletUserName("Hermoine Granger");
     customerLogin.clickLoginButton();
     customerLogin.verifyLoginAsCustomer("Hermoine Granger");
     customerLogin.selectAccountNumber("1003");
    String[] transactionTypes ={"Credit","Debit","Debit","Credit","Debit","Debit","Credit"};
    int[] transactionAmounts ={50000,3000,2000,5000,10000,15000,1500};
    customerLogin.performTransaction("1003", transactionTypes, transactionAmounts);
    }

    @AfterTest
    public void quitBrowser()
    {
     driver.quit();
    }
    
}
