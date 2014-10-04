package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SIPRE_TMP_BANCO database table.
 * 
 */
@Entity
@Table(name="SIPRE_TMP_BANCO")
@NamedQuery(name="SipreTmpBanco.findAll", query="SELECT s FROM SipreTmpBanco s")
public class SipreTmpBanco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CPERSONA_NRO_ADM")
	private String cpersonaNroAdm;

	@Column(name="CTB_COD_BANCO")
	private String ctbCodBanco;

	//bi-directional one-to-one association to SiprePersona
	@OneToOne
	@JoinColumn(name="CPERSONA_NRO_ADM",insertable = false, updatable = false)
	private SiprePersona siprePersona;

	public SipreTmpBanco() {
	}

	public String getCpersonaNroAdm() {
		return this.cpersonaNroAdm;
	}

	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}

	public String getCtbCodBanco() {
		return this.ctbCodBanco;
	}

	public void setCtbCodBanco(String ctbCodBanco) {
		this.ctbCodBanco = ctbCodBanco;
	}

	public SiprePersona getSiprePersona() {
		return this.siprePersona;
	}

	public void setSiprePersona(SiprePersona siprePersona) {
		this.siprePersona = siprePersona;
	}

}