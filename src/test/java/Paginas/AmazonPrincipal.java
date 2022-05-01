package Paginas;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import Adaptador.Driver;

public class AmazonPrincipal {
	private Driver adaptador;
	
	public AmazonPrincipal(String navegador) {
		adaptador = Driver.getInstance(navegador);
	}
	
	public void AbrirUrl(String url, String evidencia) throws IOException {
		adaptador.abrirURL(url);
		adaptador.tomarEvidencia(evidencia);
	}

	
	public void abrirPanelIdentificate(String evidencia) throws IOException {
		adaptador.hacerClick(
				By.xpath("//*[@id=\"nav-link-accountList\"]/span/span"));
		adaptador.tomarEvidencia(evidencia);

	}
	
	public void cambiarIdioma(String evidencia) throws IOException, InterruptedException {
		adaptador.hacerClick(
				By.xpath("//*[@id=\"icp-nav-flyout\"]/span/span[2]/span[2]"));
		Thread.sleep(3000);
//		adaptador.hacerClick(
//				By.xpath("//*[@id=\"nav-flyout-icp\"]/div[2]/a[1]/span/span[1]"));
		adaptador.hacerClick(
			By.xpath("//*[@id=\"icp-language-settings\"]/div[3]/div/label/i"));
		Thread.sleep(3000);
		adaptador.hacerClick(
				By.xpath("//*[@id=\"icp-save-button\"]/span/input"));
		Thread.sleep(3000);
		adaptador.tomarEvidencia(evidencia);
		Thread.sleep(3000);
	}
	
	public void clickEnIdentificate(String evidencia) throws IOException {
		adaptador.hacerClick(
				By.xpath("//*[@id=\"nav-link-accountList\"]/span/span"));
//		adaptador.hacerClick(
//				By.xpath("//*[@id=\"nav-flyout-ya-signin\"]/a"));
		adaptador.tomarEvidencia(evidencia);
	}
	
	public void validarPaginaDeLogin() throws IOException {
		String iniciarSesion = adaptador.extraerTexto(
				By.xpath("//*[@id=\"authportal-main-section\"]/div[2]/div/div[1]/form/div/div/div/h1"));
		Assert.assertEquals(iniciarSesion,"Iniciar sesión"); 
	}
	
	public void cerrarDriver() {
		adaptador.cerrarDriver();
	}

	public void presionarEscape() {
		adaptador.enviarTecla(Keys.ESCAPE);
	}

	public void validarIdiomaEspanol(String evidencia) throws InterruptedException, IOException {
		Thread.sleep(1000);
		cambiarIdioma(evidencia);
		String  devoluciones = adaptador.extraerTexto(
				By.xpath("//*[@id=\"nav-orders\"]/span[1]")
				);
		Assert.assertEquals(devoluciones, "Devoluciones");
	}
	
}
