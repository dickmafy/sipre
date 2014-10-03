package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_TMP_GUARDIA database table.
 * 
 */
@Embeddable
public class SipreTmpGuardiaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CTG_MES_PROCESO")
	private String ctgMesProceso;

	@Column(name="CPERSONA_NRO_ADM", insertable=false, updatable=false)
	private String cpersonaNroAdm;

	@Column(name="CCI_CODIGO", insertable=false, updatable=false)
	private String cciCodigo;

	@Column(name="CTG_MES_GUARDIA")
	private String ctgMesGuardia;

	public SipreTmpGuardiaPK() {
	}
	public String getCtgMesProceso() {
		return this.ctgMesProceso;
	}
	public void setCtgMesProceso(String ctgMesProceso) {
		this.ctgMesProceso = ctgMesProceso;
	}
	public String getCpersonaNroAdm() {
		return this.cpersonaNroAdm;
	}
	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}
	public String getCciCodigo() {
		return this.cciCodigo;
	}
	public void setCciCodigo(String cciCodigo) {
		this.cciCodigo = cciCodigo;
	}
	public String getCtgMesGuardia() {
		return this.ctgMesGuardia;
	}
	public void setCtgMesGuardia(String ctgMesGuardia) {
		this.ctgMesGuardia = ctgMesGuardia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreTmpGuardiaPK)) {
			return false;
		}
		SipreTmpGuardiaPK castOther = (SipreTmpGuardiaPK)other;
		return 
			this.ctgMesProceso.equals(castOther.ctgMesProceso)
			&& this.cpersonaNroAdm.equals(castOther.cpersonaNroAdm)
			&& this.cciCodigo.equals(castOther.cciCodigo)
			&& this.ctgMesGuardia.equals(castOther.ctgMesGuardia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ctgMesProceso.hashCode();
		hash = hash * prime + this.cpersonaNroAdm.hashCode();
		hash = hash * prime + this.cciCodigo.hashCode();
		hash = hash * prime + this.ctgMesGuardia.hashCode();
		
		return hash;
	}
}