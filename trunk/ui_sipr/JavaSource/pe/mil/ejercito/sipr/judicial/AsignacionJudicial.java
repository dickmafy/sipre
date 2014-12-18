package pe.mil.ejercito.sipr.judicial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.model.JudicialAsignacion;


@ManagedBean(name = "asignacionJudicial")
@ViewScoped
public class AsignacionJudicial extends MainContext implements Serializable{


	private static final long			serialVersionUID	= 1L;
	private List<JudicialAsignacion>	beanList;
	
	public AsignacionJudicial(){

		super();
		System.out.println("AsignacionJudicial");
		try {
			beanList = new ArrayList<JudicialAsignacion>();
			JudicialAsignacion o = new JudicialAsignacion();
			o.setBanco("002");
			o.setCodAsig("004");
			o.setCodLugar("003");
			o.setDni("12/3/2014");
			o.setFecProces("Coperativa Interna FDE");
			o.setLugar("Juan Carlos Rodrigez");
			o.setNombreApellido("256595995");
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<JudicialAsignacion> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<JudicialAsignacion> beanList) {
		this.beanList = beanList;
	}

}
