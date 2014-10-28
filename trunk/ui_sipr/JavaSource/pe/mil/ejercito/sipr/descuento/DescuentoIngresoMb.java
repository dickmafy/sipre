package pe.mil.ejercito.sipr.descuento;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.DescuentoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;

@ManagedBean(name = "descuentoIngresoMb")
@ViewScoped
public class DescuentoIngresoMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private DescuentoIngresoEjbRemote ejb;
	private SipreConceptoIngreso bean;

	public DescuentoIngresoMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (DescuentoIngresoEjbRemote) findServiceRemote(DescuentoIngresoEjbRemote.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void cleanBean() {
		SipreTipoPlanilla sipreTipoPlanilla = new SipreTipoPlanilla();
		bean = new SipreConceptoIngreso();
		bean.setSipreTipoPlanilla(sipreTipoPlanilla);
	}

}