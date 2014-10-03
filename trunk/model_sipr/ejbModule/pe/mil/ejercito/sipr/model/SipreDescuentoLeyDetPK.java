package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_DESCUENTO_LEY_DET database table.
 * 
 */
@Embeddable
public class SipreDescuentoLeyDetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CDLD_CODIGO")
	private String cdldCodigo;

	@Column(name="CDL_CODIGO", insertable=false, updatable=false)
	private String cdlCodigo;

	public SipreDescuentoLeyDetPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreDescuentoLeyDetPK)) {
			return false;
		}
		SipreDescuentoLeyDetPK castOther = (SipreDescuentoLeyDetPK)other;
		return 
			this.cdldCodigo.equals(castOther.cdldCodigo)
			&& this.cdlCodigo.equals(castOther.cdlCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdldCodigo.hashCode();
		hash = hash * prime + this.cdlCodigo.hashCode();
		
		return hash;
	}
}