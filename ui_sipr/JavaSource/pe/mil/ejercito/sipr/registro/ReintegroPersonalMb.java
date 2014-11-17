package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.log.Log;

import com.sun.mail.util.BEncoderStream;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaAdicionalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaAdicionalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;
import pe.mil.ejercito.sipr.model.SiprePerfil;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicionalPK;
import pe.mil.ejercito.sipr.model.SiprePlanillaPK;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "reintegroPersonalMb")
@ViewScoped
public class ReintegroPersonalMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SiprePlanillaAdicional> beanList;
	private SiprePlanillaAdicional beanSelected;
	private PlanillaAdicionalEjbRemote ejb;
	private SiprePlanillaAdicional bean;

	public ReintegroPersonalMb() {
		super();
		try {
			ejb = (PlanillaAdicionalEjbRemote) findServiceRemote(PlanillaAdicionalEjbRemote.class);
			beanList = ejb.findAll(ConstantesUtil.LISTAR_EJB_REMOTE);
			cleanBean();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void cleanBean() {
		bean = new SiprePlanillaAdicional();
		SiprePlanillaAdicionalPK pk = new SiprePlanillaAdicionalPK();
		bean.setSiprePlanillaAdicionalPK(pk);
	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void saveBean(ActionEvent event) {
		try {
			ejb.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA, SEVERITY_INFO);
			beanList = ejb.findAll();
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL, SEVERITY_ERROR);
		}
		
	}
	
	public void deleteBean(ActionEvent event) {
		try {
			ejb.remove(beanSelected);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA, SEVERITY_INFO);
			beanList = ejb.findAll();
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL, SEVERITY_ERROR);
		}
		
	}

	public List<SiprePlanillaAdicional> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SiprePlanillaAdicional> beanList) {
		this.beanList = beanList;
	}

	public SiprePlanillaAdicional getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SiprePlanillaAdicional beanSelected) {
		this.beanSelected = beanSelected;
	}

	public SiprePlanillaAdicional getBean() {
		return bean;
	}

	public void setBean(SiprePlanillaAdicional bean) {
		this.bean = bean;
	}


}
