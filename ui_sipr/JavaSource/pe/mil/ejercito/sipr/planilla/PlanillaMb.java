package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.log.Log;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.Faces;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.IngresoConceptoPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreIngresoOtroPK;
import pe.mil.ejercito.sipr.model.SiprePerfil;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "planillaMb")
@ViewScoped
public class PlanillaMb extends MainContext implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private IngresoConceptoPersonalEjbRemote ejb;

	private List<SipreIngresoOtro> beanList;
	private SipreIngresoOtro bean;

	public PlanillaMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (IngresoConceptoPersonalEjbRemote) findServiceRemote(IngresoConceptoPersonalEjbRemote.class);

			beanList = ejb.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
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
	}

	public List<SipreIngresoOtro> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreIngresoOtro> beanList) {
		this.beanList = beanList;
	}

	public SipreIngresoOtro getBean() {
		return bean;
	}

	public void setBean(SipreIngresoOtro bean) {
		this.bean = bean;
	}

	/*
	 * tabla no tiene estado public void disableBean(ActionEvent event) { try {
	 * beanSelected.setCusuarioEst("D");
	 * ejb.updateDeshabilitarUsuario(beanSelected);
	 * showMessage("Se elminio con exito.", SEVERITY_INFO); } catch (Exception
	 * e) { e.printStackTrace();
	 * showMessage("Ocurrió un problema al intentar eliminar el perfil",
	 * SEVERITY_ERROR);
	 * 
	 * }
	 */

}