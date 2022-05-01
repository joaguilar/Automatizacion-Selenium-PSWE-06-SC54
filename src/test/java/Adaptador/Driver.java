package Adaptador;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Driver {

	private WebDriver driver;
	private static Driver instancia = null;
	
	public static Driver getInstance(String navegador) {
		synchronized (Driver.class) {
			if(instancia == null) {
				instancia = new Driver(navegador);
			}
		}
		return instancia;
	}
	
	private Driver(String navegador) {
		if(navegador.equals("Chrome")) {
			
//			System.setProperty("webdriver.chrome.driver","/Users/joseaguilar/Library/CloudStorage/OneDrive-Personal/Cenfotec/MISIA/PSWE-06 Calidad y Pruebas de Software SC54/Semana 15/chromedriver");
			String webdriverProperty = System.getProperty("webdriver.chrome.driver");
			if (null == webdriverProperty || webdriverProperty.isEmpty())
				System.setProperty("webdriver.chrome.driver","./chromedriver");
            ChromeOptions optionsChrome = new ChromeOptions();
            optionsChrome.addArguments("test-type");
            optionsChrome.addArguments("ignore-certificate-errors");
            optionsChrome.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(optionsChrome);
            
		}else if(navegador.equals("Firefox")){
			
			System.setProperty("webdriver.gecko.driver","C:\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void abrirURL(String url) {
		driver.get(url);
	}
	
	public void cerrarDriver() {
		driver.close();
	}
	
	public void ingresarTexto(By localizador, String texto) {
		WebElement textBox = driver.findElement(localizador);
		textBox.clear();
		textBox.sendKeys(texto);
	}
	
	public void hacerClick(By localizador) {
		WebElement textBox = driver.findElement(localizador);
		textBox.click();
	}
	
	public boolean validarSiElementeExiste(By localizador) {
		WebElement elemento = driver.findElement(localizador);
		return elemento.isDisplayed();
	}
	
	public void seleccionarOpcionValue(By localizador, String value) {
		Select selec = new Select(driver.findElement(localizador));
		selec.selectByValue(value);
	}
	
	public void seleccionarOpcionTexto(By localizador, String texto) {
		Select selec = new Select(driver.findElement(localizador));
		selec.selectByVisibleText(texto);
	}
	
	public void tomarEvidencia(String ruta) throws IOException {
		TakesScreenshot scr = ((TakesScreenshot) driver);
		File scrFile = scr.getScreenshotAs(OutputType.FILE);
		File destFile = new File(ruta);
		FileUtils.copyFile(scrFile, destFile);
	}

	public String extraerTexto(By localizador) {
		WebElement elemento = driver.findElement(localizador); 
		String texto = elemento.getText();
		return texto;
	}

	public void abrirSubPanel(By localizador) {
		Actions actions = new Actions(driver);
		WebElement elemento = driver.findElement(localizador); 
		actions.moveToElement(elemento);		
	}

	public void enviarTecla(Keys tecla) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);	
	}
}


