package pe.mil.ejercito.sipr.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.GradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrado;
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

}
