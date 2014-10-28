package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.EntidadCrediticiaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreEntidadCrediticia;

@Stateless
public class EntidadCrediticiaEjbBean extends
		GenericDAOImpl<SipreEntidadCrediticia> implements
		EntidadCrediticiaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;
}
