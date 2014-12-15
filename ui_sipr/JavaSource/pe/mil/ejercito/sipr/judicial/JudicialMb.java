package pe.mil.ejercito.sipr.judicial;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.MainContext;


@ManagedBean(name = "judicialMb")
@ViewScoped
public class JudicialMb extends MainContext implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer nroResolucion;
	
	public JudicialMb(){
		try{
			//ejbPlanilla = (PlanillaEjbRemote) findServiceRemote(PlanillaEjbRemote.class);
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String searchJudicial(){
		return "";
	}
	
	public String newJudicial(){
		return "";
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
	public Integer getNroResolucion() {
		return nroResolucion;
	}
	public void setNroResolucion(Integer nroResolucion) {
		this.nroResolucion = nroResolucion;
	}
	

}
