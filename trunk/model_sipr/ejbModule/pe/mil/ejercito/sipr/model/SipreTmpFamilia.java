package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SIPRE_TMP_FAMILIA database table.
 * 
 */
@Entity
@Table(name="SIPRE_TMP_FAMILIA")
@NamedQuery(name="SipreTmpFamilia.findAll", query="SELECT s FROM SipreTmpFamilia s")
public class SipreTmpFamilia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CTF_CIF")
	private String ctfCif;

	@Column(name="CPERSONA_NRO_ADM")
	private String cpersonaNroAdm;

	@Column(name="CTF_CAU_RENOVAC")
	private String ctfCauRenovac;

	@Column(name="CTF_DNI")
	private String ctfDni;

	@Temporal(TemporalType.DATE)
	@Column(name="CTF_FEC_RENOVAC")
	private Date ctfFecRenovac;

	@Column(name="CTF_SEXO")
	private String ctfSexo;

	@Column(name="CTF_SIT_FAMILIA")
	private String ctfSitFamilia;

	@Temporal(TemporalType.DATE)
	@Column(name="DTF_FEC_NAC")
	private Date dtfFecNac;

	@Column(name="VTF_APE_NOM")
	private String vtfApeNom;

	public SipreTmpFamilia() {
	}

	public String getCtfCif() {
		return this.ctfCif;
	}

	public void setCtfCif(String ctfCif) {
		this.ctfCif = ctfCif;
	}

	public String getCpersonaNroAdm() {
		return this.cpersonaNroAdm;
	}

	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}

	public String getCtfCauRenovac() {
		return this.ctfCauRenovac;
	}

	public void setCtfCauRenovac(String ctfCauRenovac) {
		this.ctfCauRenovac = ctfCauRenovac;
	}

	public String getCtfDni() {
		return this.ctfDni;
	}

	public void setCtfDni(String ctfDni) {
		this.ctfDni = ctfDni;
	}

	public Date getCtfFecRenovac() {
		return this.ctfFecRenovac;
	}

	public void setCtfFecRenovac(Date ctfFecRenovac) {
		this.ctfFecRenovac = ctfFecRenovac;
	}

	public String getCtfSexo() {
		return this.ctfSexo;
	}

	public void setCtfSexo(String ctfSexo) {
		this.ctfSexo = ctfSexo;
	}

	public String getCtfSitFamilia() {
		return this.ctfSitFamilia;
	}

	public void setCtfSitFamilia(String ctfSitFamilia) {
		this.ctfSitFamilia = ctfSitFamilia;
	}

	public Date getDtfFecNac() {
		return this.dtfFecNac;
	}

	public void setDtfFecNac(Date dtfFecNac) {
		this.dtfFecNac = dtfFecNac;
	}

	public String getVtfApeNom() {
		return this.vtfApeNom;
	}

	public void setVtfApeNom(String vtfApeNom) {
		this.vtfApeNom = vtfApeNom;
	}

}