package pe.mil.ejercito.sipr.gestion;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.BancoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.VerificarCodigoBancoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBanco;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@ManagedBean(name = "verificarCodigoBanco")
@ViewScoped
public class VerificarCodigoBanco extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private VerificarCodigoBancoEjbRemote ejb;
	private BancoEjbRemote ejbBanco;

	private List<SipreTmpBanco> beanList;
	private List<SipreBanco> bancoList;
	private SipreTmpBanco bean;

	public VerificarCodigoBanco() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (VerificarCodigoBancoEjbRemote) findServiceRemote(VerificarCodigoBancoEjbRemote.class);
			ejbBanco = (BancoEjbRemote) findServiceRemote(BancoEjbRemote.class);

			beanList = ejb.findAll(100);
			bancoList = ejbBanco.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		bean = new SipreTmpBanco();
		SiprePersona persona = new SiprePersona();
		SipreBanco banco = new SipreBanco();
		//bean.setSiprePersona(persona);
		bean.setSipreBanco(banco);
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

	public List<SipreTmpBanco> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreTmpBanco> beanList) {
		this.beanList = beanList;
	}

	public SipreTmpBanco getBean() {
		return bean;
	}

	public void setBean(SipreTmpBanco bean) {
		this.bean = bean;
	}

	public BancoEjbRemote getEjbBanco() {
		return ejbBanco;
	}

	public void setEjbBanco(BancoEjbRemote ejbBanco) {
		this.ejbBanco = ejbBanco;
	}

	public List<SipreBanco> getBancoList() {
		return bancoList;
	}

	public void setBancoList(List<SipreBanco> bancoList) {
		this.bancoList = bancoList;
	}

}