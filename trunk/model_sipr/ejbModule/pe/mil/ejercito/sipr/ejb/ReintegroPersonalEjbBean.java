package pe.mil.ejercito.sipr.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.ejbremote.GradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrado;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;

/**
 * Session Bean implementation class UsuarioEjbBean
 */
@Stateless
public class ReintegroPersonalEjbBean implements ReintegroPersonalEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	public ReintegroPersonalEjbBean() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreTmpBonificacion> listReintegroPersonal(
			SipreTmpBonificacion sipreTmpBonificacion) {
		List<SipreTmpBonificacion> list = new ArrayList<SipreTmpBonificacion>();
		try {
			list = (List<SipreTmpBonificacion>) em.createNamedQuery(
					"SipreTmpBonificacion.findAll").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SiprePlanillaAdicional> listReintegroMesAdicional(
			SiprePlanillaAdicional siprePlanillaAdicional) {
		List<SiprePlanillaAdicional> list = new ArrayList<SiprePlanillaAdicional>();
		try {
			list = (List<SiprePlanillaAdicional>) em.createNamedQuery("SipreTmpBonificacion.findAll")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SiprePlanillaAdicional> listReintegroRegistro(
			SiprePlanillaAdicional siprePlanillaAdicional) {
		List<SiprePlanillaAdicional> list = new ArrayList<SiprePlanillaAdicional>();
		try {
			list = (List<SiprePlanillaAdicional>) em.createNamedQuery("SipreTmpBonificacion.findAll")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public SiprePlanilla registrar(SiprePlanilla beanPlanilla) {
		try{
				//Ya es traido de la vista.
				//beanPlanilla.getId().setCpersonaNroAdm(); 
				Date fechaTempDate;
				try {
					fechaTempDate = UValidacion.fechaStringToDate(beanPlanilla.getId().getCplanillaMesProceso());
				} catch (Exception e) {
					fechaTempDate = UValidacion.StringToDate(beanPlanilla.getId().getCplanillaMesProceso());
					e.printStackTrace();
				}
				String fechaTempString = UValidacion.getDateToString(fechaTempDate);
				beanPlanilla.getId().setCplanillaMesProceso(fechaTempString);
				
				//Long nplanillaNumProceso= getMaxLong("nplanillaNumProceso", "SiprePlanilla");
				beanPlanilla.getId().setNplanillaNumProceso(1);
				
				SiprePersona siprePersona = new SiprePersona();
				siprePersona.setCpersonaNroAdm(beanPlanilla.getId().getCpersonaNroAdm());
				beanPlanilla.setSiprePersona(siprePersona);
				
				em.persist(beanPlanilla);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return beanPlanilla;
		
	}

	
	public String getMaxString(String codigo,String tabla) {
		String idretorno = null;
		try {
			String consulta = "select max(a." +codigo+ ") from "+tabla+" a";
			Query q = em.createQuery(consulta);
			idretorno = (String) q.getSingleResult();
			idretorno  = String.valueOf(Integer.valueOf(idretorno.trim())+1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idretorno;
	}
	
	public Long getMaxLong(String codigoDeTabla,String nombreTabla) {
		//Long nplanillaNumProceso= getMaxLong("nplanillaNumProceso", "SiprePlanilla");
		Long idretorno = null;
		try {
			String consulta = "select max(a." +codigoDeTabla+ ") from "+nombreTabla+" a";
			Query q = em.createQuery(consulta);
			idretorno = (Long) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idretorno;
	}
	
	
}
