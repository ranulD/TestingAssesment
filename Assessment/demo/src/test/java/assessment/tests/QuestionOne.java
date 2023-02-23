package assessment.tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import assessment.pages.LoginPage;
import assessment.pages.BankManagerPage;
import assessment.pages.AddCustomerPage;
import assessment.pages.CustomersPage;

public class QuestionOne 
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
    
    @Test(description = "Verify by adding new customers and deleting newly added customers", priority = 1)
    public void addAndDeleteCustomers() throws InterruptedException
    {
     LoginPage login= new LoginPage(driver);
     BankManagerPage bankManager = new BankManagerPage(driver);
     AddCustomerPage addCustomer = new AddCustomerPage(driver);
     CustomersPage customers = new CustomersPage(driver);
     login.loginToBankApplication("Bank Manager Login");
     bankManager.clickOnTab("Add Customer");
    String[] firstNames ={"Christopher","Frank","Christopher","Connely","Jackson","Minka","Jackson"};
    String[] lastNames ={"Connely","Christopher","Minka","Jackson","Frank","Jackson","Connely"};
    String[] postCodes ={"L789C349","A897N450","M098Q585","L789C349","L789C349","A897N450","L789C349"};
    addCustomer.addNewCustomer(firstNames,lastNames,postCodes);
    addCustomer.navigateToCustomersTab();
    Assert.assertTrue(customers.verifyAddedCustomers(firstNames, lastNames, postCodes));
    String[] firstNamesDelete ={"Jackson","Christopher"};
    String[] lastNamesDelete ={"Frank","Connely"};
    String[] postCodesDelete ={"L789C349","L789C349"};
    customers.deleteCustomers(firstNamesDelete, lastNamesDelete, postCodesDelete);
    Thread.sleep(100000);
    }

    @AfterTest
    public void quitBrowser()
    {
     driver.quit();
    }
    
}
