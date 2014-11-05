package pe.mil.ejercito.sipr.parametro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;



import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericResponseBean;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.ParametroDetalleEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ParametroEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreParametro;
import pe.mil.ejercito.sipr.model.SipreParametroDetalle;

@ManagedBean(name = "parametroMb")
@ViewScoped
public class ParametroMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;

	private ParametroEjbRemote ejb;
	private List<SipreParametro> beanList;
	private SipreParametro bean;

	private ParametroDetalleEjbRemote ejbParametroDetalle;
	private SipreParametroDetalle beanSipreParametroDetalle;
	private List<SipreParametroDetalle> beanParametroDetalleList;
	private GenericResponseBean<SipreParametro> sessionBean ; 

	public ParametroMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (ParametroEjbRemote) findServiceRemote(ParametroEjbRemote.class);
			ejbParametroDetalle = (ParametroDetalleEjbRemote) findServiceRemote(ParametroDetalleEjbRemote.class);

			beanParametroDetalleList = ejbParametroDetalle.findAll(100);
			beanList = ejb.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String goParametroDetalleMb(){
		sessionBean = new  GenericResponseBean<>();
		sessionBean.setObjeto(bean);
		registrarVariable("vparametro", sessionBean);
		return "/modules/parametro/parametroDetalle.xhtml";
		
	}
	public void newBean(ActionEvent event) {
		bean = new SipreParametro();
		//bean.set
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

	public List<SipreParametro> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreParametro> beanList) {
		this.beanList = beanList;
	}

	public SipreParametro getBean() {
		return bean;
	}

	public void setBean(SipreParametro bean) {
		this.bean = bean;
	}

	public SipreParametroDetalle getBeanSipreParametroDetalle() {
		return beanSipreParametroDetalle;
	}

	public void setBeanSipreParametroDetalle(
			SipreParametroDetalle beanSipreParametroDetalle) {
		this.beanSipreParametroDetalle = beanSipreParametroDetalle;
	}

	public List<SipreParametroDetalle> getBeanParametroDetalleList() {
		return beanParametroDetalleList;
	}

	public void setBeanParametroDetalleList(
			List<SipreParametroDetalle> beanParametroDetalleList) {
		this.beanParametroDetalleList = beanParametroDetalleList;
	}


	public GenericResponseBean<SipreParametro> getSessionBean() {
		return sessionBean;
	}


	public void setSessionBean(GenericResponseBean<SipreParametro> sessionBean) {
		this.sessionBean = sessionBean;
	}

}