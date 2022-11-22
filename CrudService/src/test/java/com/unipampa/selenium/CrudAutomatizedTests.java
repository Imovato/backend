package com.unipampa.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

/**
 * @author <João Villa>
 * @version 1.0
 */

public class CrudAutomatizedTests {

    @Before
    public void before() throws Exception {
        System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
    }

    @Test
    public void testaSeEntrouNaPaginaDestinada() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("http://localhost:3000/");
        driver.findElement(By.xpath("//div[@id='root']/div/div/section/div/a/button/p")).click();
        driver.findElement(By.xpath("//select[@id='Tipo']")).click();
        driver.findElement(By.id("Tipo")).sendKeys("Apartamento");
        driver.findElement(By.name("name")).sendKeys("Casa muito boa askdoaskdoksod aksd");
        //driver.quit();
    }

    @Test
    public void testaSeOCampoUsuarioEEditavel01() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:3000/");

        //WebElement userName = driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 1)));
        Thread.sleep(4000);
        //Assert.assertEquals(true, userName.isEnabled());
        driver.quit();
    }

    @Test
    public void testaSeOCampoSenhaEEditavel02() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:3000/");

        //WebElement passWord = driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 2)));

        Thread.sleep(3000);
        //Assert.assertEquals(true, passWord.isEnabled());
        driver.quit();
    }


    @Test
    public void testaLoginAcessoAPesquisaEFollow() throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.instagram.com");
        /*
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 1))).sendKeys(Arquivo.lerTexto(caminho, 1));
        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 2))).sendKeys(Arquivo.lerTexto(caminho, 2));
        Thread.sleep(1500);
        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 3))).click();
        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 9))).click();
        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 10))).click();
        Thread.sleep(2000);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(window.scrollBy(0,500));
        Thread.sleep(2000);

        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 4))).sendKeys(Arquivo.lerTexto(caminho, 3));
        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 5))).click();
        driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 6))).click();

        Thread.sleep(3000);
        driver.navigate().to("https://www.instagram.com/gmlunardi/");
        String retornado = driver.findElement(By.xpath(Arquivo.lerTexto(caminho1, 7))).getText();

        assertEquals("Solicitadi", retornado);
        driver.quit();
        */
    }

    @After
    public void after() throws Exception {

    }
} 

