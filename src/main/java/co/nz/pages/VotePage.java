package co.nz.pages;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.webelement.FindWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.List;

public class VotePage {
    TestContext testContext;
    TestDriverManager testDriverManager;
    private WebDriver webDriver;
    private FindWebElement find;
    @FindBy(xpath = "//img[@title='Alfa Romeo']")
    private WebElement alfaRomeoTile;
    public boolean result = false;
    @FindBy(xpath = "//table[@class=\"cars table table-hover\"]/tbody/tr")
    private List<WebElement> carsList;
    @FindBy(xpath = "//button[@class=\"btn btn-success\"]")
    private WebElement voteBtn;
    @FindBy(id = "comment")
    private WebElement userComment;
    @FindBy(xpath = "//p[@class='card-text']")
    private WebElement succesVoteMsg;
    @FindBy(xpath = "//table[@class='table']//tbody/tr")
    private List<WebElement> authorDetails;

    public VotePage(TestContext context) throws MalformedURLException {
        testContext = context;
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
        this.webDriver = testDriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void IclickAlfaRomeo(){
        alfaRomeoTile.click();
    }

    public void selectCar(){
        for (WebElement car: carsList ) {
            WebElement carDetails = car;
            if(carDetails.findElement(By.xpath("//td[2]/a")).getText().equals("Giulietta")){
                carDetails.findElement(By.xpath("//td[2]/a")).click();
            }
        }
    }

    public void clickVoteBtn(){
        voteBtn.click();
    }

    public void enterComment(){
        userComment.sendKeys("Highly Recommended");
    }

    public boolean successVotemsg() {
        for (WebElement authDetail : authorDetails) {
            String comt = authDetail.findElement(By.xpath("//td[3]")).getText();
            if (comt.equals("Highly Recommended") && succesVoteMsg.getText().equals("Thank you for your vote!")){
                result = true;
            }
        }
        return result;
    }
}
