package siteIterasys;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Login {

    String url;
    String pathChromeDriverExe;
    String propriedade;
    WebDriver driver;

    @Before
    public void acessarAreaLogin(){
        url = "https://www.iterasys.com.br";
        propriedade = "webdriver.chrome.driver";
        pathChromeDriverExe = "C:\\Users\\CSouza\\IdeaProjects\\IteraSys-Workspace\\drivers\\chrome\\version83\\chromedriver.exe";
        System.setProperty(propriedade, pathChromeDriverExe);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void finalizarLogin(){
        driver.quit();
    }

    @Test
    public void tentarFazerLoginEfalhar(){
        driver.get(url);
        driver.findElement(By.cssSelector("li.active.login_header > a")).click();

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(Keys.chord("email@ficticio.com"));
        driver.findElement(By.id("senha")).clear();
        driver.findElement(By.id("senha")).sendKeys(Keys.chord("senhaFicticia123"));
        driver.findElement(By.id("btn_login")).click();

        // pendente implementar Page Objects
        String textoParaVerificar = "Dados de acesso incorretos!";
        String textoAtual = driver.findElement(By.cssSelector("div.alert-danger > h4")).getText();
        assertEquals(textoParaVerificar, textoAtual);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
