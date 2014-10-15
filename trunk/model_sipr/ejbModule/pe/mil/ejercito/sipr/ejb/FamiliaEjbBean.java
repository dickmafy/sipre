package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.FamiliaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TipoPlanillaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.VerificarCodigoBancoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class FamiliaEjbBean extends GenericDAOImpl<SipreTmpFamilia>
		implements FamiliaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	
}
