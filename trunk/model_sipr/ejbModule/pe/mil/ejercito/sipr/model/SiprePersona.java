/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "SIPRE_PERSONA")
@NamedQueries({ @NamedQuery(name = "SiprePersona.findAll", query = "SELECT s FROM SiprePersona s") })
public class SiprePersona implements Serializable {
	private static final long			serialVersionUID	= 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 9)
	@Column(name = "CPERSONA_NRO_ADM")
	private String						cpersonaNroAdm;
	@Size(max = 8)
	@Column(name = "CPERSONA_DNI")
	private String						cpersonaDni;
	@Column(name = "CPERSONA_IND_QUI")
	private String						cpersonaIndQui;
	@Column(name = "DPERSONA_FEC_FAL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date						dpersonaFecFal;
	@Column(name = "DPERSONA_FEC_ING")
	@Temporal(TemporalType.TIMESTAMP)
	private Date						dpersonaFecIng;
	@Size(max = 30)
	@Column(name = "VPERSONA_DOC_ALTA")
	private String						vpersonaDocAlta;
	@Size(max = 80)
	@Column(name = "VPERSONA_APE_NOM")
	private String						vpersonaApeNom;
	@Column(name = "CPERSONA_SEXO")
	private String						cpersonaSexo;
	@Column(name = "DPERSONA_FEC_NAC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date						dpersonaFecNac;
	@Column(name = "NPERSONA_NRO_HIJO")
	private String						npersonaNroHijo;
	@Size(max = 3)
	@Column(name = "CPERSONA_COD_GRA_PEN")
	private String						cpersonaCodGraPen;
	@Column(name = "CPERSONA_IND_ONP")
	private String						cpersonaIndOnp;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "NPERSONA_POR_UNIF")
	private BigDecimal					npersonaPorUnif;
	@Size(max = 20)
	@Column(name = "VPERSONA_CAD_FUNC")
	private String						vpersonaCadFunc;
	@Size(max = 18)
	@Column(name = "VPERSONA_COD_ESSALUD")
	private String						vpersonaCodEssalud;
	@Size(max = 16)
	@Column(name = "VPERSONA_CUSPP")
	private String						vpersonaCuspp;
	@Column(name = "CPERSONA_IND_AGUIN")
	private String						cpersonaIndAguin;
	@Column(name = "DPERSONA_FEC_AFI_AFP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date						dpersonaFecAfiAfp;
	@Column(name = "DPERSONA_FEC_FIN_CONTR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date						dpersonaFecFinContr;
	@Column(name = "DPERSONA_FEC_PROMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date						dpersonaFecPromo;
	@Column(name = "NPERSONA_RET_ASCENSO")
	private String						npersonaRetAscenso;
	@Size(max = 2)
	@Column(name = "CPERSONA_IND_LICENCIA")
	private String						cpersonaIndLicencia;
	@Column(name = "DPERSONA_FEC_RETIRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date						dpersonaFecRetiro;
	@Size(max = 30)
	@Column(name = "VPERSONA_DOC_RETIRO")
	private String						vpersonaDocRetiro;
	@Size(max = 6)
	@Column(name = "CPERSONA_SER_RECON")
	private String						cpersonaSerRecon;
	@Size(max = 30)
	@Column(name = "VPERSONA_DOC_RECON")
	private String						vpersonaDocRecon;
	@Column(name = "NPERSONA_POR_PENSION")
	private BigDecimal					npersonaPorPension;
	@Column(name = "CPERSONA_SEX_PENSION")
	private String						cpersonaSexPension;
	@Size(max = 80)
	@Column(name = "VPERSONA_NOM_CAUSANTE")
	private String						vpersonaNomCausante;
	@JoinTable(name = "SIPRE_DESCUENTO_LEY_PERSONA", joinColumns = { @JoinColumn(name = "CPERSONA_NRO_ADM",
			referencedColumnName = "CPERSONA_NRO_ADM") }, inverseJoinColumns = {
			@JoinColumn(name = "CDL_CODIGO", referencedColumnName = "CDL_CODIGO"),
			@JoinColumn(name = "CDLD_CODIGO", referencedColumnName = "CDLD_CODIGO") })
	@ManyToMany
	private List<SipreDescuentoLeyDet>	sipreDescuentoLeyDetList;
	@JoinTable(name = "SIPRE_DOCENTE", joinColumns = { @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM") },
			inverseJoinColumns = {
			@JoinColumn(name = "CESCALA_CODIGO", referencedColumnName = "CESCALA_CODIGO"),
			@JoinColumn(name = "CESCALA_HORA", referencedColumnName = "CESCALA_HORA") })
	@ManyToMany
	private List<SipreEscala>			sipreEscalaList;
	@JoinColumn(name = "CUNIDAD_CODIGO", referencedColumnName = "CUNIDAD_CODIGO")
	@ManyToOne(optional = false)
	private SipreUnidad					sipreUnidad;
	@JoinColumn(name = "CSC_CODIGO", referencedColumnName = "CSC_CODIGO")
	@ManyToOne
	private SipreSituacionCausal		sipreSituacionCausal;
	@JoinColumn(name = "CSA_CODIGO", referencedColumnName = "CSA_CODIGO")
	@ManyToOne
	private SipreSituacionAdm			sipreSituacionAdm;
	@JoinColumn(name = "CGRADO_CODIGO", referencedColumnName = "CGRADO_CODIGO")
	@ManyToOne(optional = false)
	private SipreGrado					sipreGrado;
	@JoinColumn(name = "CEC_CODIGO", referencedColumnName = "CEC_CODIGO")
	@ManyToOne
	private SipreEstadoCivil			sipreEstadoCivil;
	@JoinColumn(name = "CEA_CODIGO", referencedColumnName = "CEA_CODIGO")
	@ManyToOne
	private SipreEspecialidadAlterna	sipreEspecialidadAlterna;
	@JoinColumn(name = "CCEDULA_CODIGO", referencedColumnName = "CCEDULA_CODIGO")
	@ManyToOne
	private SipreCedula					sipreCedula;
	@JoinColumn(name = "CCARGO_CODIGO", referencedColumnName = "CCARGO_CODIGO")
	@ManyToOne
	private SipreCargo					sipreCargo;
	@JoinColumn(name = "CBANCO_CODIGO", referencedColumnName = "CBANCO_CODIGO")
	@ManyToOne
	private SipreBanco					sipreBanco;
	@JoinColumn(name = "CARMA_CODIGO", referencedColumnName = "CARMA_CODIGO")
	@ManyToOne
	private SipreArma					sipreArma;
	@JoinColumn(name = "CAGRUPADOR_CODIGO", referencedColumnName = "CAGRUPADOR_CODIGO")
	@ManyToOne
	private SipreAgrupador				sipreAgrupador;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreTmpBonificacion> sipreTmpBonificacionList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreDescuentoNoprocesado> sipreDescuentoNoprocesadoList; /*
	 * 
	 * @OneToOne(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * SipreTmpBanco sipreTmpBanco;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreIngresoOtro> sipreIngresoOtroList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreTmpGuardia> sipreTmpGuardiaList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SiprePlanillaOtro> siprePlanillaOtroList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreDiferenciaDetalle> sipreDiferenciaDetalleList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreTmpJudicial> sipreTmpJudicialList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreTmpFamilia> sipreTmpFamiliaList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreExcepcion> sipreExcepcionList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SiprePlanilla> siprePlanillaList;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePersona") private
	 * List<SipreTmpEntidadCrediticia> sipreTmpEntidadCrediticiaList;
	 */
	public SiprePersona() {
	}

	public SiprePersona(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}

	public String getCpersonaNroAdm() {
		return cpersonaNroAdm;
	}

	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}

	public String getCpersonaDni() {
		return cpersonaDni;
	}

	public void setCpersonaDni(String cpersonaDni) {
		this.cpersonaDni = cpersonaDni;
	}

	public String getCpersonaIndQui() {
		return cpersonaIndQui;
	}

	public void setCpersonaIndQui(String cpersonaIndQui) {
		this.cpersonaIndQui = cpersonaIndQui;
	}

	public Date getDpersonaFecFal() {
		return dpersonaFecFal;
	}

	public void setDpersonaFecFal(Date dpersonaFecFal) {
		this.dpersonaFecFal = dpersonaFecFal;
	}

	public Date getDpersonaFecIng() {
		return dpersonaFecIng;
	}

	public void setDpersonaFecIng(Date dpersonaFecIng) {
		this.dpersonaFecIng = dpersonaFecIng;
	}

	public String getVpersonaDocAlta() {
		return vpersonaDocAlta;
	}

	public void setVpersonaDocAlta(String vpersonaDocAlta) {
		this.vpersonaDocAlta = vpersonaDocAlta;
	}

	public String getVpersonaApeNom() {
		return vpersonaApeNom;
	}

	public void setVpersonaApeNom(String vpersonaApeNom) {
		this.vpersonaApeNom = vpersonaApeNom;
	}

	public String getCpersonaSexo() {
		return cpersonaSexo;
	}

	public void setCpersonaSexo(String cpersonaSexo) {
		this.cpersonaSexo = cpersonaSexo;
	}

	public Date getDpersonaFecNac() {
		return dpersonaFecNac;
	}

	public void setDpersonaFecNac(Date dpersonaFecNac) {
		this.dpersonaFecNac = dpersonaFecNac;
	}

	public String getNpersonaNroHijo() {
		return npersonaNroHijo;
	}

	public void setNpersonaNroHijo(String npersonaNroHijo) {
		this.npersonaNroHijo = npersonaNroHijo;
	}

	public String getCpersonaCodGraPen() {
		return cpersonaCodGraPen;
	}

	public void setCpersonaCodGraPen(String cpersonaCodGraPen) {
		this.cpersonaCodGraPen = cpersonaCodGraPen;
	}

	public String getCpersonaIndOnp() {
		return cpersonaIndOnp;
	}

	public void setCpersonaIndOnp(String cpersonaIndOnp) {
		this.cpersonaIndOnp = cpersonaIndOnp;
	}

	public BigDecimal getNpersonaPorUnif() {
		return npersonaPorUnif;
	}

	public void setNpersonaPorUnif(BigDecimal npersonaPorUnif) {
		this.npersonaPorUnif = npersonaPorUnif;
	}

	public String getVpersonaCadFunc() {
		return vpersonaCadFunc;
	}

	public void setVpersonaCadFunc(String vpersonaCadFunc) {
		this.vpersonaCadFunc = vpersonaCadFunc;
	}

	public String getVpersonaCodEssalud() {
		return vpersonaCodEssalud;
	}

	public void setVpersonaCodEssalud(String vpersonaCodEssalud) {
		this.vpersonaCodEssalud = vpersonaCodEssalud;
	}

	public String getVpersonaCuspp() {
		return vpersonaCuspp;
	}

	public void setVpersonaCuspp(String vpersonaCuspp) {
		this.vpersonaCuspp = vpersonaCuspp;
	}

	public String getCpersonaIndAguin() {
		return cpersonaIndAguin;
	}

	public void setCpersonaIndAguin(String cpersonaIndAguin) {
		this.cpersonaIndAguin = cpersonaIndAguin;
	}

	public Date getDpersonaFecAfiAfp() {
		return dpersonaFecAfiAfp;
	}

	public void setDpersonaFecAfiAfp(Date dpersonaFecAfiAfp) {
		this.dpersonaFecAfiAfp = dpersonaFecAfiAfp;
	}

	public Date getDpersonaFecFinContr() {
		return dpersonaFecFinContr;
	}

	public void setDpersonaFecFinContr(Date dpersonaFecFinContr) {
		this.dpersonaFecFinContr = dpersonaFecFinContr;
	}

	public Date getDpersonaFecPromo() {
		return dpersonaFecPromo;
	}

	public void setDpersonaFecPromo(Date dpersonaFecPromo) {
		this.dpersonaFecPromo = dpersonaFecPromo;
	}

	public String getNpersonaRetAscenso() {
		return npersonaRetAscenso;
	}

	public void setNpersonaRetAscenso(String npersonaRetAscenso) {
		this.npersonaRetAscenso = npersonaRetAscenso;
	}

	public String getCpersonaIndLicencia() {
		return cpersonaIndLicencia;
	}

	public void setCpersonaIndLicencia(String cpersonaIndLicencia) {
		this.cpersonaIndLicencia = cpersonaIndLicencia;
	}

	public Date getDpersonaFecRetiro() {
		return dpersonaFecRetiro;
	}

	public void setDpersonaFecRetiro(Date dpersonaFecRetiro) {
		this.dpersonaFecRetiro = dpersonaFecRetiro;
	}

	public String getVpersonaDocRetiro() {
		return vpersonaDocRetiro;
	}

	public void setVpersonaDocRetiro(String vpersonaDocRetiro) {
		this.vpersonaDocRetiro = vpersonaDocRetiro;
	}

	public String getCpersonaSerRecon() {
		return cpersonaSerRecon;
	}

	public void setCpersonaSerRecon(String cpersonaSerRecon) {
		this.cpersonaSerRecon = cpersonaSerRecon;
	}

	public String getVpersonaDocRecon() {
		return vpersonaDocRecon;
	}

	public void setVpersonaDocRecon(String vpersonaDocRecon) {
		this.vpersonaDocRecon = vpersonaDocRecon;
	}

	public BigDecimal getNpersonaPorPension() {
		return npersonaPorPension;
	}

	public void setNpersonaPorPension(BigDecimal npersonaPorPension) {
		this.npersonaPorPension = npersonaPorPension;
	}

	public String getCpersonaSexPension() {
		return cpersonaSexPension;
	}

	public void setCpersonaSexPension(String cpersonaSexPension) {
		this.cpersonaSexPension = cpersonaSexPension;
	}

	public String getVpersonaNomCausante() {
		return vpersonaNomCausante;
	}

	public void setVpersonaNomCausante(String vpersonaNomCausante) {
		this.vpersonaNomCausante = vpersonaNomCausante;
	}

	public List<SipreDescuentoLeyDet> getSipreDescuentoLeyDetList() {
		return sipreDescuentoLeyDetList;
	}

	public void setSipreDescuentoLeyDetList(List<SipreDescuentoLeyDet> sipreDescuentoLeyDetList) {
		this.sipreDescuentoLeyDetList = sipreDescuentoLeyDetList;
	}

	public List<SipreEscala> getSipreEscalaList() {
		return sipreEscalaList;
	}

	public void setSipreEscalaList(List<SipreEscala> sipreEscalaList) {
		this.sipreEscalaList = sipreEscalaList;
	}

	public SipreUnidad getSipreUnidad() {
		return sipreUnidad;
	}

	public void setSipreUnidad(SipreUnidad sipreUnidad) {
		this.sipreUnidad = sipreUnidad;
	}

	public SipreSituacionCausal getSipreSituacionCausal() {
		return sipreSituacionCausal;
	}

	public void setSipreSituacionCausal(SipreSituacionCausal sipreSituacionCausal) {
		this.sipreSituacionCausal = sipreSituacionCausal;
	}

	public SipreSituacionAdm getSipreSituacionAdm() {
		return sipreSituacionAdm;
	}

	public void setSipreSituacionAdm(SipreSituacionAdm sipreSituacionAdm) {
		this.sipreSituacionAdm = sipreSituacionAdm;
	}

	public SipreGrado getSipreGrado() {
		return sipreGrado;
	}

	public void setSipreGrado(SipreGrado sipreGrado) {
		this.sipreGrado = sipreGrado;
	}

	public SipreEstadoCivil getSipreEstadoCivil() {
		return sipreEstadoCivil;
	}

	public void setSipreEstadoCivil(SipreEstadoCivil sipreEstadoCivil) {
		this.sipreEstadoCivil = sipreEstadoCivil;
	}

	public SipreEspecialidadAlterna getSipreEspecialidadAlterna() {
		return sipreEspecialidadAlterna;
	}

	public void setSipreEspecialidadAlterna(SipreEspecialidadAlterna sipreEspecialidadAlterna) {
		this.sipreEspecialidadAlterna = sipreEspecialidadAlterna;
	}

	public SipreCedula getSipreCedula() {
		return sipreCedula;
	}

	public void setSipreCedula(SipreCedula sipreCedula) {
		this.sipreCedula = sipreCedula;
	}

	public SipreCargo getSipreCargo() {
		return sipreCargo;
	}

	public void setSipreCargo(SipreCargo sipreCargo) {
		this.sipreCargo = sipreCargo;
	}

	public SipreBanco getSipreBanco() {
		return sipreBanco;
	}

	public void setSipreBanco(SipreBanco sipreBanco) {
		this.sipreBanco = sipreBanco;
	}

	public SipreArma getSipreArma() {
		return sipreArma;
	}

	public void setSipreArma(SipreArma sipreArma) {
		this.sipreArma = sipreArma;
	}

	public SipreAgrupador getSipreAgrupador() {
		return sipreAgrupador;
	}

	public void setSipreAgrupador(SipreAgrupador sipreAgrupador) {
		this.sipreAgrupador = sipreAgrupador;
	}

	/*
	 * public List<SipreTmpBonificacion> getSipreTmpBonificacionList() { return
	 * sipreTmpBonificacionList; }
	 * 
	 * public void setSipreTmpBonificacionList(List<SipreTmpBonificacion>
	 * sipreTmpBonificacionList) { this.sipreTmpBonificacionList =
	 * sipreTmpBonificacionList; }
	 * 
	 * public List<SipreDescuentoNoprocesado> getSipreDescuentoNoprocesadoList()
	 * { return sipreDescuentoNoprocesadoList; }
	 * 
	 * public void
	 * setSipreDescuentoNoprocesadoList(List<SipreDescuentoNoprocesado>
	 * sipreDescuentoNoprocesadoList) { this.sipreDescuentoNoprocesadoList =
	 * sipreDescuentoNoprocesadoList; }
	 * 
	 * public SipreTmpBanco getSipreTmpBanco() { return sipreTmpBanco; }
	 * 
	 * public void setSipreTmpBanco(SipreTmpBanco sipreTmpBanco) {
	 * this.sipreTmpBanco = sipreTmpBanco; }
	 * 
	 * public List<SipreIngresoOtro> getSipreIngresoOtroList() { return
	 * sipreIngresoOtroList; }
	 * 
	 * public void setSipreIngresoOtroList(List<SipreIngresoOtro>
	 * sipreIngresoOtroList) { this.sipreIngresoOtroList = sipreIngresoOtroList;
	 * }
	 * 
	 * public List<SipreTmpGuardia> getSipreTmpGuardiaList() { return
	 * sipreTmpGuardiaList; }
	 * 
	 * public void setSipreTmpGuardiaList(List<SipreTmpGuardia>
	 * sipreTmpGuardiaList) { this.sipreTmpGuardiaList = sipreTmpGuardiaList; }
	 * 
	 * public List<SiprePlanillaOtro> getSiprePlanillaOtroList() { return
	 * siprePlanillaOtroList; }
	 * 
	 * public void setSiprePlanillaOtroList(List<SiprePlanillaOtro>
	 * siprePlanillaOtroList) { this.siprePlanillaOtroList =
	 * siprePlanillaOtroList; }
	 * 
	 * public List<SipreDiferenciaDetalle> getSipreDiferenciaDetalleList() {
	 * return sipreDiferenciaDetalleList; }
	 * 
	 * public void setSipreDiferenciaDetalleList(List<SipreDiferenciaDetalle>
	 * sipreDiferenciaDetalleList) { this.sipreDiferenciaDetalleList =
	 * sipreDiferenciaDetalleList; }
	 * 
	 * public List<SipreTmpJudicial> getSipreTmpJudicialList() { return
	 * sipreTmpJudicialList; }
	 * 
	 * public void setSipreTmpJudicialList(List<SipreTmpJudicial>
	 * sipreTmpJudicialList) { this.sipreTmpJudicialList = sipreTmpJudicialList;
	 * }
	 * 
	 * 
	 * public List<SipreTmpFamilia> getSipreTmpFamiliaList() { return
	 * sipreTmpFamiliaList; }
	 * 
	 * public void setSipreTmpFamiliaList(List<SipreTmpFamilia>
	 * sipreTmpFamiliaList) { this.sipreTmpFamiliaList = sipreTmpFamiliaList; }
	 * 
	 * public List<SipreExcepcion> getSipreExcepcionList() { return
	 * sipreExcepcionList; }
	 * 
	 * public void setSipreExcepcionList(List<SipreExcepcion>
	 * sipreExcepcionList) { this.sipreExcepcionList = sipreExcepcionList; }
	 * 
	 * public List<SiprePlanilla> getSiprePlanillaList() { return
	 * siprePlanillaList; }
	 * 
	 * public void setSiprePlanillaList(List<SiprePlanilla> siprePlanillaList) {
	 * this.siprePlanillaList = siprePlanillaList; }
	 * 
	 * public List<SipreTmpEntidadCrediticia> getSipreTmpEntidadCrediticiaList()
	 * { return sipreTmpEntidadCrediticiaList; }
	 * 
	 * public void
	 * setSipreTmpEntidadCrediticiaList(List<SipreTmpEntidadCrediticia>
	 * sipreTmpEntidadCrediticiaList) { this.sipreTmpEntidadCrediticiaList =
	 * sipreTmpEntidadCrediticiaList; }
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		
		// not set
		if (!(object instanceof SiprePersona)) {
			return false;
		}
		SiprePersona other = (SiprePersona) object;
		if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null)
				|| (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.SiprePersona[ cpersonaNroAdm=" + cpersonaNroAdm + " ]";
	}

}
