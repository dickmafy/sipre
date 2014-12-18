package pe.mil.ejercito.sipr.entidadCrediticia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.model.CrediticiaConsulta;

@ManagedBean(name = "consultaAutorizacionesCrediticia")
@ViewScoped
public class ConsultaAutorizacionesCrediticia extends MainContext implements Serializable {


	private static final long			serialVersionUID	= 1L;
	private List<CrediticiaConsulta>	beanList;
	private String						cip;

	public ConsultaAutorizacionesCrediticia() {
		super();
		try {
			beanList = new ArrayList<CrediticiaConsulta>();
			CrediticiaConsulta o = new CrediticiaConsulta();
			o.setCodCajaMilitar("001");
			o.setCodDscCaja("001");
			o.setCodentidad("001");
			o.setFechaAlta("12/3/2014");
			o.setFechaBaja("11/5/2014");
			o.setNroAutorizacion("001");
			o.setRazonSocial("Empresa SA");
			o.setSituacionAuto("001");
			beanList.add(o);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void newBean() {
		showMessage("Se encontro a la Persona con el CIP :  " + cip, SEVERITY_INFO);
		//cleanBean();
	}


	public List<CrediticiaConsulta> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<CrediticiaConsulta> beanList) {
		this.beanList = beanList;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

}