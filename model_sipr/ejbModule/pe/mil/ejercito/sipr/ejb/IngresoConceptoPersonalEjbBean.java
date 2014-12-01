package pe.mil.ejercito.sipr.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.IngresoConceptoPersonalEjbRemote;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class IngresoConceptoPersonalEjbBean extends
		GenericDAOImpl<SipreIngresoOtro> implements
		IngresoConceptoPersonalEjbRemote, GenericDAO<SipreIngresoOtro> {

	

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	/**
	 * Default constructor.
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreIngresoOtro> listIngresoConceptoPersonal(
			SipreIngresoOtro bean) {
		List<SipreIngresoOtro> list = new ArrayList<>();
		try {
			list = (List<SipreIngresoOtro>) em
					.createNamedQuery("SipreIngresoOtro.findAll")
					.setMaxResults(50).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Boolean registrarIngresoConceptoPersonal(SipreIngresoOtro bean) {
		boolean bandera = false;
		try {
			if (null != bean.getId().getCciCodigo()
					|| null != bean.getId().getCpersonaNroAdm()) {
				em.merge(bean);
				bandera = true;
			} else {
				em.persist(bean);
				bandera = true;
			}
		} catch (Exception e) {
			bandera = false;
			e.printStackTrace();
		}
		return bandera;
	}

	@Override
	public Boolean deshabilitarIngresoConceptoPersonal(SipreIngresoOtro bean) {
		boolean bandera = false;
		try {
			if (null != bean.getId().getCciCodigo()
					|| null != bean.getId().getCpersonaNroAdm()) {
				em.merge(bean);
				bandera = true;
			}
		} catch (Exception e) {
			bandera = false;
			e.printStackTrace();
		}
		return bandera;
	}

	@Override
	public SipreIngresoOtro getBean(SipreIngresoOtro bean) {
		return bean;

	}

}
