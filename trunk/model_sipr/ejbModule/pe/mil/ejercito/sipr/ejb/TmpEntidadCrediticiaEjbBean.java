package pe.mil.ejercito.sipr.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import pe.mil.ejercito.sipr.ejbremote.TmpEntidadCrediticiaEjbRemote;

import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticia;

public class TmpEntidadCrediticiaEjbBean extends GenericDAOImpl<SipreTmpEntidadCrediticia>implements TmpEntidadCrediticiaEjbRemote {

@PersistenceContext(name = "model_sipre")
EntityManager em;

}
