package pe.mil.ejercito.sipr.gestion;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.VerificarCodigoBancoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@ManagedBean(name = "verificarCodigoBanco")
@ViewScoped
public class VerificarCodigoBanco extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private VerificarCodigoBancoEjbRemote ejb;

	private List<SipreTmpBanco> beanList;
	private SipreTmpBanco bean;

	public VerificarCodigoBanco() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (VerificarCodigoBancoEjbRemote) findServiceRemote(VerificarCodigoBancoEjbRemote.class);

			beanList = ejb.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		bean = new SipreTmpBanco();

	}

	public void saveBean(ActionEvent event) {
		try {
			if (null != bean.getCpersonaNroAdm()) {
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

}