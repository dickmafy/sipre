package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.model.SipreAuditoria;



@Remote
public interface AuditoriaEjbRemote {
	List<SipreAuditoria> getAuditoriaList();
	List<SipreAuditoria> getAuditoriaList(String accion, String tabla, String resultado);
	SipreAuditoria persist(SipreAuditoria s);
}
