package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_CONCEPTO_DESCUENTO_LEY database table.
 * 
 */
@Embeddable
public class SipreConceptoDescuentoLeyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CDLD_CODIGO", insertable=false, updatable=false)
	private String cdldCodigo;

	@Column(name="CDL_CODIGO", insertable=false, updatable=false)
	private String cdlCodigo;

	@Column(name="CCD_CODIGO", insertable=false, updatable=false)
	private String ccdCodigo;

	public SipreConceptoDescuentoLeyPK() {
	}
	public String getCdldCodigo() {
		return this.cdldCodigo;
	}
	public void setCdldCodigo(String cdldCodigo) {
		this.cdldCodigo = cdldCodigo;
	}
	public String getCdlCodigo() {
		return this.cdlCodigo;
	}
	public void setCdlCodigo(String cdlCodigo) {
		this.cdlCodigo = cdlCodigo;
	}
	public String getCcdCodigo() {
		return this.ccdCodigo;
	}
	public void setCcdCodigo(String ccdCodigo) {
		this.ccdCodigo = ccdCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreConceptoDescuentoLeyPK)) {
			return false;
		}
		SipreConceptoDescuentoLeyPK castOther = (SipreConceptoDescuentoLeyPK)other;
		return 
			this.cdldCodigo.equals(castOther.cdldCodigo)
			&& this.cdlCodigo.equals(castOther.cdlCodigo)
			&& this.ccdCodigo.equals(castOther.ccdCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdldCodigo.hashCode();
		hash = hash * prime + this.cdlCodigo.hashCode();
		hash = hash * prime + this.ccdCodigo.hashCode();
		
		return hash;
	}
}