package assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;



public class CustomerLoginPage 
{
    WebDriver driver;

    public CustomerLoginPage(WebDriver driver){
        this.driver=driver;
    }
  
     By lblYourName = By.xpath("//label[contains(text(),'Your Name')]");
     By drpDwnUserName = By.xpath("//select[@id='userSelect']");
     By btnLogin = By.xpath("//button[text()='Login']");
     By drpDwnAccountNumber = By.xpath("//select[@id='accountSelect']");
     By txtAmount = By.xpath("//input[@placeholder='amount']");
     By btnWithdraw = By.xpath("//button[text()='Withdraw']");
     By btnDeposit = By.xpath("//button[text()='Deposit']");
     By btnDepositTab = By.xpath("//div[@ng-hide='noAccount']/child::button[contains(text(),'Deposit')]");
     By btnWithdrawTab = By.xpath("//div[@ng-hide='noAccount']/child::button[contains(text(),'Withdrawl')]");

    
    public void seletUserName(String userName) throws InterruptedException
    {
        try {
            if(driver.findElement(lblYourName).isDisplayed()==true){
                Select drpDwnName = new Select(driver.findElement(drpDwnUserName));
                drpDwnName.selectByVisibleText(userName);
                Thread.sleep(1000);
                //logger.trace("Logged as "+userRole);
            }else{
                //logger.trace("Login page is not loaded");
            }
        } catch (Exception e) {
            System.out.println("Exception : "+e.getMessage());
        }  
    }

    public void clickLoginButton() throws InterruptedException
    {
        driver.findElement(btnLogin).click();
        Thread.sleep(1000);
    }
    public boolean verifyLoginAsCustomer(String customerName) throws InterruptedException
    {
        boolean status=false;
        WebElement lblCustomerName=driver.findElement(By.xpath("//span[text()='"+customerName+"']"));
        
            if(lblCustomerName.isDisplayed()==true){
               
               Thread.sleep(3000);
               status=true;
            }  
        return status;
    }

    public void selectAccountNumber(String accountNumber) throws InterruptedException
    {
        Select drpDwnAccountNum = new Select(driver.findElement(drpDwnAccountNumber));
        drpDwnAccountNum.selectByVisibleText(accountNumber);
        Thread.sleep(1000);
    }

    public int getAccountBalance(String accountNumber) throws InterruptedException
    {
        WebElement lblAccountBalance=driver.findElement(By.xpath("//strong[contains(text(),'"+accountNumber+"')]/following-sibling::strong"));
        String accountBalance=lblAccountBalance.getText();
        return Integer.parseInt(accountBalance);
    }
    public void performTransaction(String accountNumber, String[] transactionTypes, int[] transactionAmounts) throws InterruptedException
    {
        int currentBalance = getAccountBalance(accountNumber);

        for(int i=0;i<transactionTypes.length;i++){
            if(transactionTypes[i].equalsIgnoreCase("credit")){
                driver.findElement(btnDepositTab).click();
                Thread.sleep(2000);
                driver.findElement(txtAmount).sendKeys(Integer.toString(transactionAmounts[i]));
                Thread.sleep(1000);
                driver.findElement(btnDeposit).click();
                Thread.sleep(1000);

                currentBalance=currentBalance+transactionAmounts[i];
                System.out.println("credited amount :  "+transactionAmounts[i]);
                System.out.println("currentBalance ater credit : "+currentBalance);

            }else if(transactionTypes[i].equalsIgnoreCase("debit")){
                driver.findElement(btnWithdrawTab).click();
                Thread.sleep(2000);
                driver.findElement(txtAmount).sendKeys(Integer.toString(transactionAmounts[i]));
                Thread.sleep(1000);
                driver.findElement(btnWithdraw).click();
                Thread.sleep(1000);

                currentBalance=currentBalance-transactionAmounts[i];
                System.out.println("debitted amount : "+transactionAmounts[i]);
                System.out.println("currentBalance ater debit : "+currentBalance);
            }else{
             System.out.println("Invalidtransaction type");
            }
            int currentBalanceAfter = getAccountBalance(accountNumber);
            System.out.println("Balance from the UI : "+currentBalance);
            System.out.println("Balance After Transaction : "+currentBalanceAfter);
            Assert.assertEquals(currentBalance, currentBalanceAfter);
        
        }

       

    }

}
