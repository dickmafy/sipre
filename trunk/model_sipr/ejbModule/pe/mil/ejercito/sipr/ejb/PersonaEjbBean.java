package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.DescuentoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PersonaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoIngreso;
import pe.mil.ejercito.sipr.model.SiprePersona;

@Stateless
public class PersonaEjbBean extends GenericDAOImpl<SiprePersona> implements
		PersonaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	@Override
	public List<SiprePersona> findAllBySituacionAdministrativaYActividad() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o FROM SiprePersona o ");
		sb.append(" where o.sipreSituacionAdm.csaCodigo=:csaCodigo");
		Query q = em.createQuery(sb.toString());
		//situacion administrativa
		q.setParameter("csaCodigo", "70");
		return q.getResultList();
	}
}
