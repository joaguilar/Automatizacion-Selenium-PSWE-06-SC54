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

import Paginas.AmazonPrincipal;

public class CasosAmazonPrincipal {
	AmazonPrincipal principal;
	String folderEvidencia = "";
	String timestamp = "";
	
	@Parameters({"navegador","urlPrincipal","folderEvidencia"})
	@BeforeClass
	public void beforeClass(String navegador, String urlPrincipal, String folderEvidencia) throws IOException {
		long currentTime = System.currentTimeMillis();
		timestamp = String.valueOf(currentTime);
		String fevidencia = obtenerFolderEvidencia(folderEvidencia,timestamp);
		this.folderEvidencia = fevidencia;
		principal = new AmazonPrincipal(navegador);
		principal.AbrirUrl(urlPrincipal, this.folderEvidencia+"abrirUrlPrincipal_"+timestamp+".jpg");
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

	@BeforeMethod
	public void beforeMethod() throws IOException, InterruptedException {
//		principal.cambiarIdioma(folderEvidencia+"cambioidioma_"+timestamp+".jpg");
	}
	
	@Test
	public void ValidarIrAPAginaLogin() throws InterruptedException, IOException{
		Thread.sleep(3000);
		principal.clickEnIdentificate(folderEvidencia+"clickEnId_"+timestamp+".jpg");
		Thread.sleep(3000);
		principal.validarPaginaDeLogin();
	}
	
	@Test
	public void testCase005ValidarCambioIdioma() throws InterruptedException, IOException{
		Thread.sleep(1000);
		principal.validarIdiomaEspanol(folderEvidencia+"paginaEspanol_"+timestamp+".jpg");
	}


	@AfterMethod
	public void afterTest() {
		
	}
	
	@AfterSuite
	public void afterClass() {
		principal.cerrarDriver();
	}
	
	
}
