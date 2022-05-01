package Paginas;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import Adaptador.Driver;

public class AmazonLogin {

	private Driver adaptador;
	
	public AmazonLogin(String navegador) {
		adaptador = Driver.getInstance(navegador);
	}
	
	public void AbrirUrl(String url, String evidencia) throws IOException {
		adaptador.abrirURL(url);
		adaptador.tomarEvidencia(evidencia);
	}
	
	public void ingresarUsuario(String user, String evidencia) throws IOException {
		adaptador.ingresarTexto(By.id("ap_email"), user);
		adaptador.tomarEvidencia(evidencia);
	}
	
	public void clickEnContinuar(String evidencia) throws IOException {
		adaptador.hacerClick(By.id("continue"));
		adaptador.tomarEvidencia(evidencia);
	}
	
	public void cerrarDriver() {
		adaptador.cerrarDriver();
	}

	public void validarMensajeLoginFallido(String evidencia) throws IOException {
		boolean errorPresente = 
				adaptador.validarSiElementeExiste(
						By.id("auth-error-message-box"));
		Assert.assertTrue(errorPresente,"El mensaje de error no fue desplegado");
		boolean errorTexto =
				adaptador.validarSiElementeExiste(
						By.xpath("//*[@id=\"auth-error-message-box\"]/div/div/ul/li/span"));
						//By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/div/div/div/div/ul/li/span/text()"));
		Assert.assertTrue(errorTexto,"El texto del mensaje de error no fue desplegado");
		String mensajeError = "";
		if (errorTexto) {
			mensajeError = 
					adaptador.extraerTexto(
							By.xpath("//*[@id=\"auth-error-message-box\"]/div/div/ul/li/span"));
		}
		adaptador.tomarEvidencia(evidencia);
		Assert.assertEquals(mensajeError, "No pudimos encontrar una cuenta con esa dirección de correo electrónico");
		Assert.assertTrue(errorPresente, "El msj de error no fue mostrado");
	}
	
}
