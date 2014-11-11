/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_TMP_FAMILIA")
@NamedQueries({ @NamedQuery(name = "SipreTmpFamilia.findAll", query = "SELECT s FROM SipreTmpFamilia s") })
public class SipreTmpFamilia implements Serializable {
	private static final long	serialVersionUID	= 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 9)
	@Column(name = "CTF_CIF")
	private String				ctfCif;
	@Size(max = 80)
	@Column(name = "VTF_APE_NOM")
	private String				vtfApeNom;
	@Column(name = "DTF_FEC_NAC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dtfFecNac;
	@Column(name = "CTF_FEC_RENOVAC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				ctfFecRenovac;
	@Column(name = "CTF_SEXO")
	private String				ctfSexo;
	@Column(name = "CTF_CAU_RENOVAC")
	private String				ctfCauRenovac;
	@Column(name = "CTF_SIT_FAMILIA")
	private String				ctfSitFamilia;
	@Size(max = 8)
	@Column(name = "CTF_DNI")
	private String				ctfDni;

	@Column(name = "CPERSONA_NRO_ADM")
	private String				cpersonaNroAdm;

	/*
	 * @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName =
	 * "CPERSONA_NRO_ADM",insertable=false,updatable=false)
	 * 
	 * @ManyToOne(optional = false) private SiprePersona siprePersona;
	 */

	public SipreTmpFamilia() {
	}

	public SipreTmpFamilia(String ctfCif) {
		this.ctfCif = ctfCif;
	}

	public String getCtfCif() {
		return ctfCif;
	}

	public void setCtfCif(String ctfCif) {
		this.ctfCif = ctfCif;
	}

	public String getVtfApeNom() {
		return vtfApeNom;
	}

	public void setVtfApeNom(String vtfApeNom) {
		this.vtfApeNom = vtfApeNom;
	}

	public Date getDtfFecNac() {
		return dtfFecNac;
	}

	public void setDtfFecNac(Date dtfFecNac) {
		this.dtfFecNac = dtfFecNac;
	}

	public Date getCtfFecRenovac() {
		return ctfFecRenovac;
	}

	public void setCtfFecRenovac(Date ctfFecRenovac) {
		this.ctfFecRenovac = ctfFecRenovac;
	}

	public String getCtfSexo() {
		return ctfSexo;
	}

	public void setCtfSexo(String ctfSexo) {
		this.ctfSexo = ctfSexo;
	}

	public String getCtfCauRenovac() {
		return ctfCauRenovac;
	}

	public void setCtfCauRenovac(String ctfCauRenovac) {
		this.ctfCauRenovac = ctfCauRenovac;
	}

	public String getCtfSitFamilia() {
		return ctfSitFamilia;
	}

	public void setCtfSitFamilia(String ctfSitFamilia) {
		this.ctfSitFamilia = ctfSitFamilia;
	}

	public String getCtfDni() {
		return ctfDni;
	}

	public void setCtfDni(String ctfDni) {
		this.ctfDni = ctfDni;
	}

	/*
	 * public SiprePersona getSiprePersona() { return siprePersona; }
	 * 
	 * public void setSiprePersona(SiprePersona siprePersona) {
	 * this.siprePersona = siprePersona; }
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (ctfCif != null ? ctfCif.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SipreTmpFamilia)) {
			return false;
		}
		SipreTmpFamilia other = (SipreTmpFamilia) object;
		if ((this.ctfCif == null && other.ctfCif != null) || (this.ctfCif != null && !this.ctfCif.equals(other.ctfCif))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.SipreTmpFamilia[ ctfCif=" + ctfCif + " ]";
	}

	public String getCpersonaNroAdm() {
		return cpersonaNroAdm;
	}

	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}

}
