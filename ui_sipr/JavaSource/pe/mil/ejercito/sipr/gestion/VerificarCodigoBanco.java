package pe.mil.ejercito.sipr.gestion;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.BancoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PersonaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpBancoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBanco;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@ManagedBean(name = "verificarCodigoBanco")
@ViewScoped
public class VerificarCodigoBanco extends MainContext implements Serializable {

	private static final long	serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote	ejbUsuario;
	private TmpBancoEjbRemote	ejb;
	private BancoEjbRemote		ejbBanco;
	private PersonaEjbRemote	ejbPersona;

	private List<SipreTmpBanco>	beanList;
	private List<SipreBanco>	beanBancoList;
	private List<SiprePersona>	beanPersonaList;

	private SipreTmpBanco		bean;

	public VerificarCodigoBanco() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (TmpBancoEjbRemote) findServiceRemote(TmpBancoEjbRemote.class);
			ejbPersona = (PersonaEjbRemote) findServiceRemote(PersonaEjbRemote.class);
			ejbBanco = (BancoEjbRemote) findServiceRemote(BancoEjbRemote.class);

			beanPersonaList = ejbPersona.findAll(2000);
			beanBancoList = ejbBanco.findAll(2000);

			beanList = ejb.findAll(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		bean = new SipreTmpBanco();
		SipreBanco banco = new SipreBanco();
		bean.setSipreBanco(banco);
		

	}

	public void saveBean(ActionEvent event) {
		try {
			bean = ejb.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA, SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO, SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
	}

	public void updateBean(ActionEvent event) {
		try {
			bean = ejb.merge(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA, SEVERITY_INFO);

		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO, SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
	}

	public UsuarioEjbRemote getEjbUsuario() {
		return ejbUsuario;
	}

	public void setEjbUsuario(UsuarioEjbRemote ejbUsuario) {
		this.ejbUsuario = ejbUsuario;
	}

	public TmpBancoEjbRemote getEjb() {
		return ejb;
	}

	public void setEjb(TmpBancoEjbRemote ejb) {
		this.ejb = ejb;
	}

	public PersonaEjbRemote getEjbPersona() {
		return ejbPersona;
	}

	public void setEjbPersona(PersonaEjbRemote ejbPersona) {
		this.ejbPersona = ejbPersona;
	}

	public List<SipreTmpBanco> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreTmpBanco> beanList) {
		this.beanList = beanList;
	}

	public List<SiprePersona> getBeanPerosnaList() {
		return beanPersonaList;
	}

	public void setBeanPerosnaList(List<SiprePersona> beanPerosnaList) {
		this.beanPersonaList = beanPerosnaList;
	}

	public SipreTmpBanco getBean() {
		return bean;
	}

	public void setBean(SipreTmpBanco bean) {
		this.bean = bean;
	}

	public List<SiprePersona> getBeanPersonaList() {
		return beanPersonaList;
	}

	public void setBeanPersonaList(List<SiprePersona> beanPersonaList) {
		this.beanPersonaList = beanPersonaList;
	}

	public BancoEjbRemote getEjbBanco() {
		return ejbBanco;
	}

	public void setEjbBanco(BancoEjbRemote ejbBanco) {
		this.ejbBanco = ejbBanco;
	}

	public List<SipreBanco> getBeanBancoList() {
		return beanBancoList;
	}

	public void setBeanBancoList(List<SipreBanco> beanBancoList) {
		this.beanBancoList = beanBancoList;
	}

}