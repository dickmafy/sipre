package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_TMP_BONIFICACION database table.
 * 
 */
@Embeddable
public class SipreTmpBonificacionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CPERSONA_NRO_ADM", insertable=false, updatable=false)
	private String cpersonaNroAdm;

	@Column(name="CCI_CODIGO", insertable=false, updatable=false)
	private String cciCodigo;

	@Column(name="CTB_MES_PROCESO")
	private String ctbMesProceso;

	@Column(name="CTB_MES_BONIFICACION")
	private String ctbMesBonificacion;

	public SipreTmpBonificacionPK() {
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
	public String getCtbMesProceso() {
		return this.ctbMesProceso;
	}
	public void setCtbMesProceso(String ctbMesProceso) {
		this.ctbMesProceso = ctbMesProceso;
	}
	public String getCtbMesBonificacion() {
		return this.ctbMesBonificacion;
	}
	public void setCtbMesBonificacion(String ctbMesBonificacion) {
		this.ctbMesBonificacion = ctbMesBonificacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreTmpBonificacionPK)) {
			return false;
		}
		SipreTmpBonificacionPK castOther = (SipreTmpBonificacionPK)other;
		return 
			this.cpersonaNroAdm.equals(castOther.cpersonaNroAdm)
			&& this.cciCodigo.equals(castOther.cciCodigo)
			&& this.ctbMesProceso.equals(castOther.ctbMesProceso)
			&& this.ctbMesBonificacion.equals(castOther.ctbMesBonificacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cpersonaNroAdm.hashCode();
		hash = hash * prime + this.cciCodigo.hashCode();
		hash = hash * prime + this.ctbMesProceso.hashCode();
		hash = hash * prime + this.ctbMesBonificacion.hashCode();
		
		return hash;
	}
}