// Pacote
package siteIterasys;

// 2. Bibliotecas
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// importação estática roda mais rápido, é chamada apenas quando for utilizada em execução
import static org.junit.jupiter.api.Assertions.assertEquals;

// 3. Classe
public class Cursos {
    String url;
    String pathChromeDriverExe; // variavel para chegar no arquivo do chromedriver.exe
    WebDriver driver;

    // Antes de executar os metodos de testes ele atribui o valor "c:\\Users\\ ... \\chromedriver.exe";
    public Cursos(){
        // esta variável será utilizada dentro do System.setProperty("...", pathChromeDriver)
        pathChromeDriverExe = "C:\\Users\\CSouza\\IdeaProjects\\IteraSys-Workspace\\drivers\\chrome\\version83\\chromedriver.exe";
    }

    @Before
    public void iniciar(){
        url = "http://www.iterasys.com.br";
        System.setProperty("webdriver.chrome.driver", pathChromeDriverExe);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        // exemplo: variavelDaClasse.metodo().outroMetodo().metodoQueQueremosUsar(parametros1, parametro2, etc...);
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void consultarCurso(){

        // Ações de navegação e identificação dos elementos da tela
        driver.get(url);

        driver.findElement(By.id("searchtext")).clear();
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord("Mantis"));

        driver.findElement(By.id("searchtext")).sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("span.comprar")).click();

        // Massa de Dados > Variáveis
        String titulo = "Mantis";
        String preco = "R$ 49,99";

        // Variaveis contendo os resultados atuais que estão aparecendo no site > .getText()
        String resultadoAtualTitulo = driver.findElement(By.cssSelector("span.item-title")).getText();
        String resultadoAtualPreco = driver.findElement(By.cssSelector("span.new-price")).getText();

        // Verificações que tornam a automação acima em um Teste
        assertEquals(titulo, resultadoAtualTitulo);
        assertEquals(preco, resultadoAtualPreco);
    }
}
