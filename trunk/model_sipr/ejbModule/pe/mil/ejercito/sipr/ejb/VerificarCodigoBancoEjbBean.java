package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.VerificarCodigoBancoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class VerificarCodigoBancoEjbBean extends GenericDAOImpl<SipreTmpBanco>
		implements VerificarCodigoBancoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	/*
	@Override
	public Boolean registrarIngresoConceptoPersonal(SipreTmpBanco bean) {
		boolean bandera = false;
		try {
			if (null != bean.getCpersonaNroAdm()) {
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
*/
}
