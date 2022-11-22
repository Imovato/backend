package com.unipampa.selenium;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

/**
 * @author <João Villa>
 * @version 1.0
 */

public class CrudAutomatizedTests {

    private WebDriver driver;
    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void TestAdicionandoNovoApartamento(){
        driver.get("http://localhost:3000/");
        driver.findElement(By.xpath("//div[@id='root']/div/div/section/div/a/button/p")).click();
        driver.findElement(By.xpath("//select[@id='Tipo']")).click();
        driver.findElement(By.id("Tipo")).sendKeys("Apartamento");
        driver.findElement(By.name("name")).sendKeys("Casa muito boa askdoaskdoksod aksd");
        driver.findElement(By.id("area")).sendKeys("1000");
        driver.findElement(By.xpath("//input[@id='rooms']")).sendKeys("3");
        driver.findElement(By.id("address")).sendKeys("rua oemtoe eotmeoammskdm asads asd");
        driver.findElement(By.id("number")).sendKeys("123");
        driver.findElement(By.id("neighborhood")).sendKeys("aoetkaoetk aoektaoekto aeotkaeokt");
        driver.findElement(By.id("city")).sendKeys("santa rosa asd asd asd");
        driver.findElement(By.id("state")).sendKeys("santa rosa asd asd asd");
        driver.findElement(By.id("block")).sendKeys("2");
        driver.findElement(By.id("price")).sendKeys("1");
        driver.findElement(By.id("description")).sendKeys("asodmasodm aosmdoasmdoas aosdmoasmod aosmdaosmdoa asodmoas");
        driver.findElement(By.id("quantity")).sendKeys("1");
        driver.findElement(By.cssSelector(".grid:nth-child(16) .text-0")).click();
        driver.findElement(By.cssSelector(".grid:nth-child(16) .text-0")).sendKeys("C:\\fakepath\\casa.jpg");
        driver.findElement(By.cssSelector(".bg-red-200 > .flex")).click();
    }

    /*@After
    public void after() throws Exception {

    }*/
} 

