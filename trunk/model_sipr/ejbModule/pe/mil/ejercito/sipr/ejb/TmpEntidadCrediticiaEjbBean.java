package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import pe.mil.ejercito.sipr.ejbremote.TmpEntidadCrediticiaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticia;

@Stateless
public class TmpEntidadCrediticiaEjbBean extends GenericDAOImpl<SipreTmpEntidadCrediticia>implements TmpEntidadCrediticiaEjbRemote {

@PersistenceContext(unitName = "model_sipre")
EntityManager em;

}