package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_PLANILLA_ADICIONAL database table.
 * 
 */
@Embeddable
public class SiprePlanillaAdicionalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CPLANILLA_MES_PROCESO", insertable=false, updatable=false)
	private String cplanillaMesProceso;

	@Column(name="CPERSONA_NRO_ADM", insertable=false, updatable=false)
	private String cpersonaNroAdm;

	@Column(name="CTP_CODIGO", insertable=false, updatable=false)
	private String ctpCodigo;

	@Column(name="NPLANILLA_NUM_PROCESO", insertable=false, updatable=false)
	private long nplanillaNumProceso;

	@Column(name="CCI_CODIGO", insertable=false, updatable=false)
	private String cciCodigo;

	@Column(name="CPA_MES_ADICIONAL")
	private String cpaMesAdicional;

	public SiprePlanillaAdicionalPK() {
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
	public String getCtpCodigo() {
		return this.ctpCodigo;
	}
	public void setCtpCodigo(String ctpCodigo) {
		this.ctpCodigo = ctpCodigo;
	}
	public long getNplanillaNumProceso() {
		return this.nplanillaNumProceso;
	}
	public void setNplanillaNumProceso(long nplanillaNumProceso) {
		this.nplanillaNumProceso = nplanillaNumProceso;
	}
	public String getCciCodigo() {
		return this.cciCodigo;
	}
	public void setCciCodigo(String cciCodigo) {
		this.cciCodigo = cciCodigo;
	}
	public String getCpaMesAdicional() {
		return this.cpaMesAdicional;
	}
	public void setCpaMesAdicional(String cpaMesAdicional) {
		this.cpaMesAdicional = cpaMesAdicional;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SiprePlanillaAdicionalPK)) {
			return false;
		}
		SiprePlanillaAdicionalPK castOther = (SiprePlanillaAdicionalPK)other;
		return 
			this.cplanillaMesProceso.equals(castOther.cplanillaMesProceso)
			&& this.cpersonaNroAdm.equals(castOther.cpersonaNroAdm)
			&& this.ctpCodigo.equals(castOther.ctpCodigo)
			&& (this.nplanillaNumProceso == castOther.nplanillaNumProceso)
			&& this.cciCodigo.equals(castOther.cciCodigo)
			&& this.cpaMesAdicional.equals(castOther.cpaMesAdicional);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cplanillaMesProceso.hashCode();
		hash = hash * prime + this.cpersonaNroAdm.hashCode();
		hash = hash * prime + this.ctpCodigo.hashCode();
		hash = hash * prime + ((int) (this.nplanillaNumProceso ^ (this.nplanillaNumProceso >>> 32)));
		hash = hash * prime + this.cciCodigo.hashCode();
		hash = hash * prime + this.cpaMesAdicional.hashCode();
		
		return hash;
	}
}