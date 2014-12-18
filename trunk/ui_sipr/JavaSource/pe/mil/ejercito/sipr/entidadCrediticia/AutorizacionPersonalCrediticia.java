package pe.mil.ejercito.sipr.entidadCrediticia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.model.CrediticiaAutorizacion;

@ManagedBean(name = "autorizacionPersonalCrediticia")
@ViewScoped
public class AutorizacionPersonalCrediticia extends MainContext implements Serializable {


	private static final long			serialVersionUID	= 1L;
	private List<CrediticiaAutorizacion>	beanList;
	private String						cip;
	private String						anio;
	private String						numero;
	private String						entidad;


	public AutorizacionPersonalCrediticia() {
		super();
		try {
			beanList = new ArrayList<CrediticiaAutorizacion>();
			CrediticiaAutorizacion o = new CrediticiaAutorizacion();
			o.setCodAdmin("001");
			o.setCodCPMP("001");
			o.setCodRegimen("02");
			o.setEstado("1");
			o.setFechaRegistro("11/5/2014");
			o.setNombre("Mavel Otoma Rodrigez");
			o.setNroAutorizacion("00122");
			o.setPersonalCajaEjercito("EJERCITO");
			o.setCodregimenDescripcion("MONTEPIO");
			beanList.add(o);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void newBean() {
		showMessage("Se encontro a la entidad Crediticia con exito. Entidad :  " + entidad, SEVERITY_INFO);
		//cleanBean();
	}


	public List<CrediticiaAutorizacion> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<CrediticiaAutorizacion> beanList) {
		this.beanList = beanList;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}