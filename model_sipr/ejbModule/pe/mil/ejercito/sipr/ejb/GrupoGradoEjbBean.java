package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;

/**
 * Session Bean implementation class UsuarioEjbBean
 */
@Stateless
public class GrupoGradoEjbBean extends GenericDAOImpl<SipreGrupoGrado> implements GrupoGradoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	

	

}
