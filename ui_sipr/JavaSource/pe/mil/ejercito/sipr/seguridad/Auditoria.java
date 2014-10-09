package pe.mil.ejercito.sipr.seguridad;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.AuditoriaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreAuditoria;

@SuppressWarnings("serial")
@ManagedBean(name = "auditoriaMb")
@ViewScoped
public class Auditoria extends MainContext {
	
	AuditoriaEjbRemote ejbAuditoria;
	private List<SipreAuditoria> audList;
	
	private String accion;
	private String tabla;
	private String resultado;
	private Date fecha;
	
	
	public Auditoria(){
		super();
		ejbAuditoria =(AuditoriaEjbRemote) findServiceRemote(AuditoriaEjbRemote.class);
		audList = ejbAuditoria.getAuditoriaList();
		
	}
	
	public String consultarAuditoria(){
		audList = ejbAuditoria.getAuditoriaList(accion, tabla, resultado);
		return "";
	}

	public List<SipreAuditoria> getAudList() {
		return audList;
	}

	public void setAudList(List<SipreAuditoria> audList) {
		this.audList = audList;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

}
