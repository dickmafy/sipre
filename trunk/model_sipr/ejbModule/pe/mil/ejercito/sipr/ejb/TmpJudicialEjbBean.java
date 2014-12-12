package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.TmpJudicialEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpJudicial;

@Stateless
public class TmpJudicialEjbBean extends GenericDAOImpl<SipreTmpJudicial>implements TmpJudicialEjbRemote {

@PersistenceContext(unitName = "model_sipre")
EntityManager em;

}
