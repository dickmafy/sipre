package pe.mil.ejercito.sipr.boleta;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;



@ManagedBean(name = "boletaMb")
@ViewScoped
public class BoletaMb extends MainContext implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	private Date fechaProceso;
	private StreamedContent file;
	private String nameBoleta;
	
	public BoletaMb(){
		setNameBoleta(ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConstantesUtil.RUTA_REPORT_FILE)+"boleta.pdf");
	}
	
	public String generarRpt() throws FileNotFoundException {
        JasperPrint print = null;

        try {
            print = imprimir(file);

            if (print != null) {
                FacesContext f = FacesContext.getCurrentInstance();
                HttpServletResponse rp = (HttpServletResponse) f.getExternalContext().getResponse();

                rp.setContentType("application/pdf");
                rp.setHeader("Content-disposition", "Attachment;filename=" + "Boleta2");
                OutputStream os = rp.getOutputStream();
                
                
              /*  JRPdfExporter expPdf = new JRPdfExporter();
               
                expPdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
                expPdf.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, os);
                //expPdf.exportReport();

                try {
                    expPdf.exportReport();
                   
                } catch (JRException e) {
                    e.printStackTrace();
                }*/
                os.close();
                f.responseComplete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        
        //setNameBoleta(ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConstantesUtil.RUTA_REPORT_FILE)+"boleta.pdf");
        setNameBoleta("boleta.pdf");
        
        return "";
    }

    public JasperPrint imprimir(StreamedContent file) {
        JasperPrint print = null;
        //JasperPrint print = new JasperPrint();
    /*    try {
           // List<Expediente> lstExpedientes = ejbExpediente.listarExpedientes(expdnt.getCdgExp(), expdnt.getTpExp(), expdnt.getCondjrdcExp(),
           //         expdnt.getNomdistExp(), expdnt.getEstdprdoExp(), expdnt.getNtrlzExp(),expdnt.getTramoExp(), Estado.TPO_BSQD_RPT);

          //  if (lstExpedientes != null && !lstExpedientes.isEmpty()) {
               // ExpedienteDataSource lstExpDS = new ExpedienteDataSource(lstExpedientes);
                String path = ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConstantesUtil.REPORT_BOLETA);

                Map parametro = new HashMap();
                parametro.put("LOGO_EJERCITO", ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConstantesUtil.LOGO));
              
                File fp = new File(path);
                InputStream reportSt = new BufferedInputStream(new FileInputStream(fp));
               // print = JasperFillManager.fillReport(reportSt, parametro, lstExpDS);
                //print=JasperFillManager.fillReport(reportSt, parametro);
               JasperFillManager.fillReportToFile(path, ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConstantesUtil.RUTA_REPORT_FILE)+"/Boleta2.pdf", parametro);
          //  }



        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return print;
    }

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getNameBoleta() {
		return nameBoleta;
	}

	public void setNameBoleta(String nameBoleta) {
		this.nameBoleta = nameBoleta;
	}
    
    
}
