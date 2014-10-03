package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_TMP_ENTIDAD_CREDITICIA database table.
 * 
 */
@Embeddable
public class SipreTmpEntidadCrediticiaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CTEC_MES_PROCESO")
	private String ctecMesProceso;

	@Column(name="CPERSONA_NRO_ADM", insertable=false, updatable=false)
	private String cpersonaNroAdm;

	@Column(name="CEC_CODIGO", insertable=false, updatable=false)
	private String cecCodigo;

	public SipreTmpEntidadCrediticiaPK() {
	}
	public String getCtecMesProceso() {
		return this.ctecMesProceso;
	}
	public void setCtecMesProceso(String ctecMesProceso) {
		this.ctecMesProceso = ctecMesProceso;
	}
	public String getCpersonaNroAdm() {
		return this.cpersonaNroAdm;
	}
	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}
	public String getCecCodigo() {
		return this.cecCodigo;
	}
	public void setCecCodigo(String cecCodigo) {
		this.cecCodigo = cecCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreTmpEntidadCrediticiaPK)) {
			return false;
		}
		SipreTmpEntidadCrediticiaPK castOther = (SipreTmpEntidadCrediticiaPK)other;
		return 
			this.ctecMesProceso.equals(castOther.ctecMesProceso)
			&& this.cpersonaNroAdm.equals(castOther.cpersonaNroAdm)
			&& this.cecCodigo.equals(castOther.cecCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ctecMesProceso.hashCode();
		hash = hash * prime + this.cpersonaNroAdm.hashCode();
		hash = hash * prime + this.cecCodigo.hashCode();
		
		return hash;
	}
}