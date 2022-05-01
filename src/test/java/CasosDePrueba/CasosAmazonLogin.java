package CasosDePrueba;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Paginas.AmazonLogin;

public class CasosAmazonLogin {


	AmazonLogin login;
	String folderEvidencia = "";
	String timestamp = "";
	String usuarioInvalido = "";
	
	
	@Parameters({"navegador","url","folderEvidencia","usuarioInvalido"})
	@BeforeClass(alwaysRun=true)
	public void beforeClass(String navegador, String url, String folderEvidencia, String usuarioInvalido) throws IOException {
		long currentTime = System.currentTimeMillis();
		timestamp = String.valueOf(currentTime);
		String fevidencia = obtenerFolderEvidencia(folderEvidencia,timestamp);
		this.folderEvidencia = fevidencia;
		login = new AmazonLogin(navegador);
		this.usuarioInvalido = "jeanca";
		login.AbrirUrl(url, this.folderEvidencia+"abrirUrl_"+timestamp+".jpg");

	}
	
	@BeforeMethod
	public void beforeTest() {
		
	}
	
	
	@Test
	public void testCase002ValidarLoginNoExitoso() throws InterruptedException, IOException {
		Thread.sleep(3000);
		login.ingresarUsuario(usuarioInvalido, folderEvidencia+"ingresarUsuario_"+timestamp+".jpg");
		Thread.sleep(3000);
		login.clickEnContinuar(folderEvidencia+"login_fallido_"+timestamp+".jpg");
		Thread.sleep(5000);
		login.validarMensajeLoginFallido(folderEvidencia+"Mensaje_login_fallido_"+timestamp+".jpg");

	}
	

	
	@AfterMethod
	public void afterTest() {
		
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterClass() {
		if(null!=login) login.cerrarDriver();
	}
	
	private String obtenerFolderEvidencia(String folderEvidencia, String sufijo) throws IOException {
		String nombreFolder = folderEvidencia+(
				(null!=sufijo&&!sufijo.isEmpty())?
						"_"+sufijo:
							"");
		if  (!Files.exists(Paths.get(nombreFolder))) {
			File newDir=new File(nombreFolder);
			if(!newDir.mkdir()) {
				throw new IOException("No es posible crear directorio de evidencia "+newDir.getAbsolutePath());
			}
		}
		return nombreFolder+"/";
	}
	
}
