package pe.mil.ejercito.sipr.entidadCrediticia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.model.CrediticiaRegistro;

@ManagedBean(name = "registroEntidadCrediticia")
@ViewScoped
public class RegistroEntidadCrediticia extends MainContext implements Serializable {

	private static final long				serialVersionUID	= 1L;
	private List<CrediticiaRegistro>	beanList;


	public RegistroEntidadCrediticia() {

		super();
		System.out.println("RegistroEntidadCrediticia");
		try {
			beanList = new ArrayList<CrediticiaRegistro>();
			CrediticiaRegistro o = new CrediticiaRegistro();
			o.setCodClasificadorEntCred("002");
			o.setCodEntidadCrediticia("004");
			o.setDireccion("003");
			o.setEmail("12/3/2014");
			o.setRazonSocial("Coperativa Interna FDE");
			o.setRepresentanteEntidad("Juan Carlos Rodrigez");
			o.setRuc("256595995");
			o.setSituacionEntidad("Activo");
			o.setSujetaDescuento("Si");
			beanList.add(o);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		System.out.println("RegistroEntidadCrediticia - Init");
	}

	public void newBean(ActionEvent event) {
		showMessage("Se registro correctamente la entidad.", SEVERITY_INFO);
	}

	public List<CrediticiaRegistro> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<CrediticiaRegistro> beanList) {
		this.beanList = beanList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}