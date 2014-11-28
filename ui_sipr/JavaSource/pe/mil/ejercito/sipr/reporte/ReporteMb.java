package pe.mil.ejercito.sipr.reporte;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;


@ManagedBean(name = "reporteMb")
@ViewScoped
public class ReporteMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioEjbRemote ejbUsuario;
	private Date fechaInicio;
	private Date fechaFin;
	private StreamedContent file;
	
	public ReporteMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 public String generarRpt() throws FileNotFoundException {
	        JasperPrint print = null;

	        try {
	            print = imprimir(file);

	            if (print != null) {
	                FacesContext f = FacesContext.getCurrentInstance();
	                HttpServletResponse rp = (HttpServletResponse) f.getExternalContext().getResponse();

	                rp.setContentType("application/pdf");
	                rp.setHeader("Content-disposition", "Attachment;filename=" + "Reporte expedientes");
	                OutputStream os = rp.getOutputStream();
	                JRPdfExporter expPdf = new JRPdfExporter();

	                expPdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
	                expPdf.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, os);
	                //expPdf.exportReport();

	                try {
	                    expPdf.exportReport();
	                } catch (JRException e) {
	                    e.printStackTrace();
	                }
	                os.close();
	                f.responseComplete();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }


	        return "";
	    }

	    public JasperPrint imprimir(StreamedContent file) {
	        JasperPrint print = null;
	       /* try {
	            List<Expediente> lstExpedientes = ejbExpediente.listarExpedientes(expdnt.getCdgExp(), expdnt.getTpExp(), expdnt.getCondjrdcExp(),
	                    expdnt.getNomdistExp(), expdnt.getEstdprdoExp(), expdnt.getNtrlzExp(),expdnt.getTramoExp(), Estado.TPO_BSQD_RPT);

	            if (lstExpedientes != null && !lstExpedientes.isEmpty()) {
	                ExpedienteDataSource lstExpDS = new ExpedienteDataSource(lstExpedientes);
	                String path = UtilPath.getRutaImagenReporte(FacesContext.getCurrentInstance(), UtilPath.REPORT_EXP);

	                Map parametro = new HashMap();
	                parametro.put("RUTA_IMAGEN", UtilPath.getRutaImagenReporte(FacesContext.getCurrentInstance(), UtilPath.LOGO));
	                parametro.put("RUTA_CHECK", UtilPath.getRutaImagenReporte(FacesContext.getCurrentInstance(), UtilPath.IMGCHECK));
	                parametro.put("RUTA_DELETE", UtilPath.getRutaImagenReporte(FacesContext.getCurrentInstance(), UtilPath.IMGDELETE));

	                File fp = new File(path);
	                InputStream reportSt = new BufferedInputStream(new FileInputStream(fp));
	                print = JasperFillManager.fillReport(reportSt, parametro, lstExpDS);
	            }



	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	        return print;
	    }

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public StreamedContent getFile() {
		return file;
	}


	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	
	
	
}

