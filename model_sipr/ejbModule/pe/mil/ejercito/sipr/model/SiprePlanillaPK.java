package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_PLANILLA database table.
 * 
 */
@Embeddable
public class SiprePlanillaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CPLANILLA_MES_PROCESO")
	private String cplanillaMesProceso;

	@Column(name="CPERSONA_NRO_ADM", insertable=false, updatable=false)
	private String cpersonaNroAdm;

	@Column(name="NPLANILLA_NUM_PROCESO")
	private long nplanillaNumProceso;

	public SiprePlanillaPK() {
	}
	public String getCplanillaMesProceso() {
		return this.cplanillaMesProceso;
	}
	public void setCplanillaMesProceso(String cplanillaMesProceso) {
		this.cplanillaMesProceso = cplanillaMesProceso;
	}
	public String getCpersonaNroAdm() {
		return this.cpersonaNroAdm;
	}
	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}
	public long getNplanillaNumProceso() {
		return this.nplanillaNumProceso;
	}
	public void setNplanillaNumProceso(long nplanillaNumProceso) {
		this.nplanillaNumProceso = nplanillaNumProceso;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SiprePlanillaPK)) {
			return false;
		}
		SiprePlanillaPK castOther = (SiprePlanillaPK)other;
		return 
			this.cplanillaMesProceso.equals(castOther.cplanillaMesProceso)
			&& this.cpersonaNroAdm.equals(castOther.cpersonaNroAdm)
			&& (this.nplanillaNumProceso == castOther.nplanillaNumProceso);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cplanillaMesProceso.hashCode();
		hash = hash * prime + this.cpersonaNroAdm.hashCode();
		hash = hash * prime + ((int) (this.nplanillaNumProceso ^ (this.nplanillaNumProceso >>> 32)));
		
		return hash;
	}
}