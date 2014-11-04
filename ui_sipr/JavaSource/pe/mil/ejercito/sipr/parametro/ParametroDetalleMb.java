package pe.mil.ejercito.sipr.parametro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.poi.ss.formula.functions.T;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericResponseBean;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.ParametroDetalleEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreParametro;
import pe.mil.ejercito.sipr.model.SipreParametroDetalle;

@ManagedBean(name = "parametroDetalleMb")
@ViewScoped
public class ParametroDetalleMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	
	private ParametroDetalleEjbRemote ejb;
	private SipreParametroDetalle bean;
	private List<SipreParametroDetalle> beanList;
	private GenericResponseBean<T> sessionBean; 
	
	public ParametroDetalleMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (ParametroDetalleEjbRemote) findServiceRemote(ParametroDetalleEjbRemote.class);

			beanList = ejb.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		bean = new SipreParametroDetalle();
		
	}

	public void saveBean(ActionEvent event) {
		try {
			bean = ejb.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
					SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
	}

	public void updateBean(ActionEvent event) {
		try {
			bean = ejb.merge(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
					SEVERITY_INFO);

		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
	}

	public List<SipreParametroDetalle> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreParametroDetalle> beanList) {
		this.beanList = beanList;
	}

	public SipreParametroDetalle getBean() {
		return bean;
	}

	public void setBean(SipreParametroDetalle bean) {
		this.bean = bean;
	}

	
	

}