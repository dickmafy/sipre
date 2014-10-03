package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_INGRESO_GRADO database table.
 * 
 */
@Embeddable
public class SipreIngresoGradoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CGRADO_CODIGO", insertable=false, updatable=false)
	private String cgradoCodigo;

	@Column(name="CCI_CODIGO", insertable=false, updatable=false)
	private String cciCodigo;

	@Column(name="CIG_SITUACION")
	private String cigSituacion;

	public SipreIngresoGradoPK() {
	}
	public String getCgradoCodigo() {
		return this.cgradoCodigo;
	}
	public void setCgradoCodigo(String cgradoCodigo) {
		this.cgradoCodigo = cgradoCodigo;
	}
	public String getCciCodigo() {
		return this.cciCodigo;
	}
	public void setCciCodigo(String cciCodigo) {
		this.cciCodigo = cciCodigo;
	}
	public String getCigSituacion() {
		return this.cigSituacion;
	}
	public void setCigSituacion(String cigSituacion) {
		this.cigSituacion = cigSituacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreIngresoGradoPK)) {
			return false;
		}
		SipreIngresoGradoPK castOther = (SipreIngresoGradoPK)other;
		return 
			this.cgradoCodigo.equals(castOther.cgradoCodigo)
			&& this.cciCodigo.equals(castOther.cciCodigo)
			&& this.cigSituacion.equals(castOther.cigSituacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cgradoCodigo.hashCode();
		hash = hash * prime + this.cciCodigo.hashCode();
		hash = hash * prime + this.cigSituacion.hashCode();
		
		return hash;
	}
}