import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateNewTicketWindow;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

import static org.testng.Assert.assertTrue;

public class CreateIssue {

    HomePage homePage = null;
    LoginPage loginPage = null;
    CreateNewTicketWindow createNewTicketWindow = null;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");

        homePage = new HomePage(WebDriverFactory.getDriver());
        loginPage = new LoginPage(WebDriverFactory.getDriver());
        createNewTicketWindow = new CreateNewTicketWindow(WebDriverFactory.getDriver());

        homePage.navigateTo();
    }


    @Test

    public void createTicket() {

        homePage.isButtonCreateLinkPresent();
        homePage.clickCreateIssue();
        createNewTicketWindow.isProjectFieldDisplayed();
        createNewTicketWindow.clearProjectField();
        createNewTicketWindow.inputProjectField("Webinar (WEBINAR)");
        createNewTicketWindow.pressTabAfterProjectField();

        createNewTicketWindow.isIssueTypeFieldDisplayed();
        createNewTicketWindow.clearIssueTypeField();
        createNewTicketWindow.enterIssueTypeField("Task");
        createNewTicketWindow.pressTabAfterIssueTypeField();

        createNewTicketWindow.isSummaryFieldDisplayed();
        createNewTicketWindow.enterSummary("Test task");
        createNewTicketWindow.clearReporterField();
        createNewTicketWindow.enterReporterField("DianaSurovtseva");

        createNewTicketWindow.pressCreateIssueButton();

        assertTrue(createNewTicketWindow.isPopUpPresent());
        assertTrue(createNewTicketWindow.getPopUpText().contains("WEBINAR"));

    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.getDriver().quit();
    }

}