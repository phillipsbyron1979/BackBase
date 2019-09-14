package BBA.BBA;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;

public class searchComp {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static String pageHeader;
	public static String pageMessage;
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\silver\\Documents\\BackBase Application\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,300);
		
		driver.get("http://computer-database.herokuapp.com/computers");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		screenActions(0, "searchbox", "ACE");
		screenActions(1, "searchsubmit", "");
		
		System.out.print(driver.findElement(By.xpath("//*[@id='main']/h1")).getAttribute("innerHTML"))	;
		
	}
	
	
	public static void screenActions(int action, String id, String sendData) {
		
		//Putting the screen actions here so that if i need to add any additional actions for clicks or sending data then
		//it will filter through to the rest of the controls. Hope fully this will make it less fragile and make the main 
		//script easier to follow
		
		//0 = send keys
		//1 = click 
		//2 = drop down
		//3 = submit
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));		
		System.out.println(id + " has been located");
		
		if (action == 0) { //sendKeys
			driver.findElement(By.id(id)).sendKeys(sendData);
			System.out.println("Data :" + sendData + " has been submitted to: " + id);
		} else if (action == 1) {
			driver.findElement(By.id(id)).click();
			System.out.println(id + " has been clicked");
		} else if (action == 2) {
			Select usrDropDown = new Select(driver.findElement(By.id(id)));
			usrDropDown.selectByVisibleText(sendData);
		} else if (action == 3) {
			driver.findElement(By.id(id)).submit();
			System.out.println("Paged has been submitted");
		}
		
	}
}
