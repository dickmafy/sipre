package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.log.Log;

import com.sun.mail.util.BEncoderStream;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;
import pe.mil.ejercito.sipr.model.SiprePerfil;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SiprePlanillaPK;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "reintegroPersonalMb")
@ViewScoped
public class ReintegroPersonalMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SipreTmpBonificacion> beanList;

	private SiprePlanilla beanPlanilla;
	
	private SiprePlanillaAdicional beanSelected;
	
	private ReintegroPersonalEjbRemote ejb;
	private SipreTmpBonificacion bean;

	public ReintegroPersonalMb() {
		super();
		try {
			ejb = (ReintegroPersonalEjbRemote) findServiceRemote(ReintegroPersonalEjbRemote.class);
			beanList = ejb.listReintegroPersonal(null);
			
			beanPlanilla = new SiprePlanilla();
			SiprePlanillaPK pk = new SiprePlanillaPK();
			SiprePersona siprePersona = new SiprePersona();
			beanPlanilla.setSiprePersona(siprePersona);
			beanPlanilla.setId(pk);
			
			
			beanSelected = new SiprePlanillaAdicional();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertMensual(ActionEvent event) {
			try {
				beanPlanilla = ejb.registrar(beanPlanilla);
				showMessage("Personal editado con exito.", SEVERITY_INFO);
			} catch (Exception e) {
				showMessage("Error al guardar el Personal.", SEVERITY_ERROR);
				e.printStackTrace();
			}
	}
	
	public void updateMensual(ActionEvent event) {
		if(null != beanPlanilla.getId().getNplanillaNumProceso()
				&& null != beanPlanilla.getId().getCplanillaMesProceso() 
				&& null != beanPlanilla.getId().getCpersonaNroAdm()){
			beanPlanilla = ejb.registrar(beanPlanilla);
			showMessage("Personal editado con exito.", SEVERITY_INFO);
		}else{
			showMessage("Error al editar el Personal.", SEVERITY_ERROR);
		}
		
			
			
		
	}

	
	public void insertNuevo(ActionEvent event){
		SiprePlanillaPK pk = new SiprePlanillaPK();
		beanPlanilla = new SiprePlanilla();
		beanPlanilla.setId(pk);
		
	}
	

	public List<SipreTmpBonificacion> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreTmpBonificacion> beanList) {
		this.beanList = beanList;
	}

	public SiprePlanilla getBeanPlanilla() {
		return beanPlanilla;
	}

	public void setBeanPlanilla(SiprePlanilla beanPlanilla) {
		this.beanPlanilla = beanPlanilla;
	}

	public SiprePlanillaAdicional getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SiprePlanillaAdicional beanSelected) {
		this.beanSelected = beanSelected;
	}

	public SipreTmpBonificacion getBean() {
		return bean;
	}

	public void setBean(SipreTmpBonificacion bean) {
		this.bean = bean;
	}
}
