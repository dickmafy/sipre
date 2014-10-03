package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_BOLETA_CABECERA database table.
 * 
 */
@Embeddable
public class SipreBoletaCabeceraPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CBC_MES_PROCESO")
	private String cbcMesProceso;

	@Column(name="NBC_NUM_PROCESO")
	private long nbcNumProceso;

	@Column(name="CBC_NRO_ADM")
	private String cbcNroAdm;

	public SipreBoletaCabeceraPK() {
	}
	public String getCbcMesProceso() {
		return this.cbcMesProceso;
	}
	public void setCbcMesProceso(String cbcMesProceso) {
		this.cbcMesProceso = cbcMesProceso;
	}
	public long getNbcNumProceso() {
		return this.nbcNumProceso;
	}
	public void setNbcNumProceso(long nbcNumProceso) {
		this.nbcNumProceso = nbcNumProceso;
	}
	public String getCbcNroAdm() {
		return this.cbcNroAdm;
	}
	public void setCbcNroAdm(String cbcNroAdm) {
		this.cbcNroAdm = cbcNroAdm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreBoletaCabeceraPK)) {
			return false;
		}
		SipreBoletaCabeceraPK castOther = (SipreBoletaCabeceraPK)other;
		return 
			this.cbcMesProceso.equals(castOther.cbcMesProceso)
			&& (this.nbcNumProceso == castOther.nbcNumProceso)
			&& this.cbcNroAdm.equals(castOther.cbcNroAdm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cbcMesProceso.hashCode();
		hash = hash * prime + ((int) (this.nbcNumProceso ^ (this.nbcNumProceso >>> 32)));
		hash = hash * prime + this.cbcNroAdm.hashCode();
		
		return hash;
	}
}