package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.log.Log;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.Faces;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.ejbremote.ConceptoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.IngresoConceptoPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreIngresoOtroPK;
import pe.mil.ejercito.sipr.model.SiprePerfil;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "ingresoConceptoPersonalMb")
@ViewScoped
public class IngresoConceptoPersonalMb extends MainContext implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private IngresoConceptoPersonalEjbRemote ejb;
	private ConceptoIngresoEjbRemote ejbConceptoIngreso;

	private List<SipreIngresoOtro> beanList;
	private List<SipreConceptoIngreso> conceptoList;
		
	private SipreIngresoOtro bean;

	public IngresoConceptoPersonalMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (IngresoConceptoPersonalEjbRemote) findServiceRemote(IngresoConceptoPersonalEjbRemote.class);
			ejbConceptoIngreso = (ConceptoIngresoEjbRemote)findServiceRemote(ConceptoIngresoEjbRemote.class);
			beanList = ejb.findAll(100);
			conceptoList = ejbConceptoIngreso.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		clearBean();
	}

	private void clearBean() {
		bean = new SipreIngresoOtro();
		SipreIngresoOtroPK tmpId = new SipreIngresoOtroPK();
		bean.setId(tmpId);
	}

	public void saveBean(ActionEvent event) {
		try {
			if (null != bean.getId().getCciCodigo()
					|| null != bean.getId().getCpersonaNroAdm()) {
				bean = ejb.merge(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
			} else {
				bean = ejb.persist(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
			}
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		clearBean();
	}
	
		
	
	public List<SipreIngresoOtro> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreIngresoOtro> beanList) {
		this.beanList = beanList;
	}

	public List<SipreConceptoIngreso> getConceptoList() {
		return conceptoList;
	}

	public void setConceptoList(List<SipreConceptoIngreso> conceptoList) {
		this.conceptoList = conceptoList;
	}

	public SipreIngresoOtro getBean() {
		return bean;
	}

	public void setBean(SipreIngresoOtro bean) {
		this.bean = bean;
	}


	
	

}