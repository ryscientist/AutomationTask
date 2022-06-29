package com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UITasks {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./src/main/java/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

		WebElement rad = driver.findElement(By.xpath("//input[@value='radio1']"));
		WebElement sugg_class = driver.findElement(By.xpath("//input[@id='autocomplete']"));
		Select drop_down = new Select(driver.findElement(By.xpath("//select[@id='dropdown-class-example']")));
		WebElement switch_to_text = driver.findElement(By.xpath("//input[@name='enter-name']"));
		WebElement switch_to_button = driver.findElement(By.xpath("//input[@id='alertbtn']"));

		WebElement web_table = driver.findElement(By.xpath("//table[@id='product'][@name='courses']"));
		List<WebElement> rows = web_table.findElements(By.tagName("tr"));
		List<WebElement> cols = web_table.findElements(By.tagName("td"));

		List<WebElement> cols_name = driver
				.findElements(By.xpath("//table[@id='product'][@name='courses']/tbody/tr/td[3]"));

		System.out.println("Rows " + rows.size() + " Columns " + cols.size() + " Cols Name " + cols_name.size());

		rad.click(); // RAdio Selected
		sugg_class.sendKeys("India"); // Suggested Class selected
		drop_down.selectByValue("option1"); // Selected drop down
		switch_to_text.sendKeys("rohit"); // Entered text
		switch_to_button.click(); // clicked button

		String txt = driver.switchTo().alert().getText(); // alert text grabbed
		System.out.println(txt);
		driver.switchTo().alert().accept(); // alert accepted
		driver.switchTo().defaultContent(); // back to content

		int count = 0;
		for (int i = 0; i < cols_name.size(); i++) {
			// System.out.println(cols_name.get(i).getText());
			count = count + Integer.valueOf(cols_name.get(i).getText());
			System.out.println("Count now " + count); // count displayed

		}

		Thread.sleep(1000);
		driver.close();

	}
}
