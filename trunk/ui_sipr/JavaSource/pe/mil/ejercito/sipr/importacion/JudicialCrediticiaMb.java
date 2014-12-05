package pe.mil.ejercito.sipr.importacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;







import org.primefaces.event.FileUploadEvent;







import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

import pe.mil.ejercito.sipr.commons.ConfiguracionDefault;
import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.TmpEntidadCrediticiaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpJudicialEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreEntidadCrediticia;
import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticia;
import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticiaPK;


@ManagedBean(name = "judicialCrediticiaMb")
@ViewScoped
public class JudicialCrediticiaMb extends MainContext implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private UsuarioEjbRemote ejbUsuario;
	private Date anioMes;
	
	
	private TmpJudicialEjbRemote ejbJudicial;
	private TmpEntidadCrediticiaEjbRemote ejbEntidad;
	private String tipoArchivo;
	
	public static final String		JBOSS_CATALINA		= "catalina.home";
	public static final String		JBOSS_TEMP			= "tmpFiles";

	Integer foo;
	
	public JudicialCrediticiaMb(){
		super();
		try{
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbJudicial = (TmpJudicialEjbRemote) findServiceRemote(TmpJudicialEjbRemote.class);
			ejbEntidad = (TmpEntidadCrediticiaEjbRemote) findServiceRemote(TmpEntidadCrediticiaEjbRemote.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void seteandoTipo(){
		System.out.println("tipoArchivo::"+tipoArchivo);
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			foo = (Integer) event.getComponent().getAttributes().get("typeFile");
			transferFile(event);
		    InputStream is = event.getFile().getInputstream();
			readDbf(is,tipoArchivo!=null?Integer.parseInt(tipoArchivo):null);

		} catch (FileNotFoundException e) {
			showMessage("Archivo no encontrado.", SEVERITY_ERROR);
		} catch (IOException e) {
			showMessage("No se pudo leer el archivo.", SEVERITY_ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			showMessage("No se copio el contenido del Excel correctamente.", SEVERITY_ERROR);
		}
		
		
	}
	
	private void readDbf(InputStream is,Integer tipo) throws IOException{
		DBFReader reader   = new DBFReader( is); 
		int numberOfFields = reader.getFieldCount();
		for( int i=0; i<numberOfFields; i++) {
            DBFField field = reader.getField(i);
            System.out.println(field.getName());
	    }
		 System.out.println("foo archivo::>"+foo);
		 System.out.println("tipo archivo::>"+tipoArchivo);
		
		Object []rowObjects;
        while( (rowObjects = reader.nextRecord()) != null) {
        	if(tipo==ConstantesUtil.TIPO_FILE_ENTIDAD){
        		SipreTmpEntidadCrediticia ent=new SipreTmpEntidadCrediticia();
        		SipreTmpEntidadCrediticiaPK pk=new SipreTmpEntidadCrediticiaPK();
        		int numbColumn=0;
        		for(int i=0; i<rowObjects.length; i++) {
        			switch (numbColumn) {
					case 1:{pk.setCtecTipoMovim(String.valueOf(rowObjects[i]));numbColumn++;} break;
					case 2:{pk.setCpersonaNroAdm(String.valueOf(rowObjects[i]));numbColumn++; }break;
					case 3:{pk.setCecCodigo(String.valueOf(rowObjects[i]));numbColumn++; }break;
					case 4:{ent.setNtecMonto((BigDecimal)rowObjects[i]);numbColumn++; }break;
					case 5:{ent.setNtecMtoAnterior((BigDecimal)rowObjects[i]);numbColumn++; }break;
					case 6:{ent.setNtecNroCuota((Integer) rowObjects[i]);numbColumn++;}break;
					case 9:{pk.setCtecMesProceso(String.valueOf(rowObjects[i]));numbColumn++;}break;
					}
        			
        			if(numbColumn==numberOfFields){
        				numbColumn=0;
        				ent.setSipreTmpEntidadCrediticiaPK(pk);
        				ejbEntidad.persist(ent);
        				 ent=new SipreTmpEntidadCrediticia();
                		 pk=new SipreTmpEntidadCrediticiaPK();
        				
        			}
        			 System.out.println(rowObjects[i]+"==>nro fila:"+i);
  	            } 
        		
        	}else if(tipo == ConstantesUtil.TIPO_FILE_JUDICIAL){
        		
        	}
             
	      }
	      is.close();
	}
	
	private File transferFile(FileUploadEvent event) {
		String fileOldName = event.getFile().getFileName();
		
		File archivo = null;
		String rutaGuardar = null;
		SimpleDateFormat fileId = null;
		String fileNewName = null;

		try {
			rutaGuardar = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

			fileId = new SimpleDateFormat("yyyyMMddHHmmss");
			fileNewName = fileId.format(new Date()) + fileOldName + fileOldName.substring(event.getFile().getFileName().lastIndexOf('.'));

			archivo = new File(rutaGuardar + "/" +ConfiguracionDefault.RUTA_FILE_SYSTEM+ fileNewName);
			System.out.println("path :" + rutaGuardar);
			InputStream is = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(archivo);
			byte buf[] = new byte[1024];
			int len;
			while ((len = is.read(buf)) > 0)
				out.write(buf, 0, len);
			is.close();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return archivo;

		
	}

	

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public Date getAnioMes() {
		return anioMes;
	}

	public void setAnioMes(Date anioMes) {
		this.anioMes = anioMes;
	}
	
	
	
	
	

}
