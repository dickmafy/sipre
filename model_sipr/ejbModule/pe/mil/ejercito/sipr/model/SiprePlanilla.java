/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * 
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_PLANILLA")
@NamedQueries({ @NamedQuery(name = "SiprePlanilla.findAll", query = "SELECT s FROM SiprePlanilla s") })
public class SiprePlanilla implements Serializable {
	private static final long				serialVersionUID	= 1L;
	@EmbeddedId
	protected SiprePlanillaPK				id;
	@Size(max = 8)
	@Column(name = "CPLANILLA_DNI")
	private String							cplanillaDni;
	@Column(name = "CPLANILLA_IND_QUI")
	private String							cplanillaIndQui;
	@Column(name = "DPLANILLA_FEC_FAL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecFal;
	@Column(name = "DPLANILLA_FEC_ING")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecIng;
	@Size(max = 30)
	@Column(name = "VPLANILLA_DOC_ALTA")
	private String							vplanillaDocAlta;
	@Size(max = 80)
	@Column(name = "VPLANILLA_APE_NOM")
	private String							vplanillaApeNom;
	@Column(name = "CPLANILLA_SEXO")
	private String							cplanillaSexo;
	@Column(name = "DPLANILLA_FEC_NAC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecNac;
	@Column(name = "NPLANILLA_NRO_HIJO")
	private Integer							nplanillaNroHijo;
	@Size(max = 3)
	@Column(name = "CPLANILLA_COD_GRA_PEN")
	private String							cplanillaCodGraPen;
	@Column(name = "CPLANILLA_IND_ONP")
	private String							cplanillaIndOnp;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "NPLANILLA_POR_UNIF")
	private BigDecimal						nplanillaPorUnif;
	@Size(max = 20)
	@Column(name = "VPLANILLA_CAD_FUNC")
	private String							vplanillaCadFunc;
	@Size(max = 18)
	@Column(name = "VPLANILLA_COD_ESSALUD")
	private String							vplanillaCodEssalud;
	@Size(max = 16)
	@Column(name = "VPLANILLA_CUSPP")
	private String							vplanillaCuspp;
	@Column(name = "CPLANILLA_IND_AGUIN")
	private String							cplanillaIndAguin;
	@Column(name = "DPLANILLA_FEC_AFI_AFP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecAfiAfp;
	@Column(name = "DPLANILLA_FEC_FIN_CONTR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecFinContr;
	@Column(name = "DPLANILLA_FEC_PROMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecPromo;
	@Column(name = "NPLANILLA_RET_ASCENSO")
	private Integer							nplanillaRetAscenso;
	@Size(max = 2)
	@Column(name = "CPLANILLA_IND_LICENCIA")
	private String							cplanillaIndLicencia;
	@Column(name = "DPLANILLA_FEC_RETIRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecRetiro;
	@Size(max = 30)
	@Column(name = "VPLANILLA_DOC_RETIRO")
	private String							vplanillaDocRetiro;
	@Size(max = 6)
	@Column(name = "CPLANILLA_SER_RECON")
	private String							cplanillaSerRecon;
	@Size(max = 30)
	@Column(name = "VPLANILLA_DOC_RECON")
	private String							vplanillaDocRecon;
	@Column(name = "NPLANILLA_POR_PENSION")
	private BigDecimal						nplanillaPorPension;
	@Column(name = "CPLANILLA_SEX_PENSION")
	private String							cplanillaSexPension;
	@Size(max = 80)
	@Column(name = "VPLANILLA_NOM_CAUSANTE")
	private String							vplanillaNomCausante;
	@Column(name = "CPLANILLA_IND_ACT_PEN")
	private String							cplanillaIndActPen;
	@Column(name = "CPLANILLA_IND_CALCULO")
	private String							cplanillaIndCalculo;
	@Size(max = 18)
	@Column(name = "NPLANILLA_TIE_SERVICIO")
	private String							nplanillaTieServicio;
	@Size(max = 9)
	@Column(name = "CPLANILLA_USU_MOD")
	private String							cplanillaUsuMod;
	@Column(name = "DPLANILLA_FEC_REG")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecReg;
	@Column(name = "DPLANILLA_FEC_MOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date							dplanillaFecMod;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePlanilla")
	private List<SipreCalculoDescuentoLey>	sipreCalculoDescuentoLeyList;

	/*
	 * @OneToOne(cascade = CascadeType.ALL, mappedBy = "siprePlanilla") private
	 * SipreCalculoQuintaCategoria sipreCalculoQuintaCategoria;
	 */

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePlanilla")
	private List<SiprePlanillaDescuento>	siprePlanillaDescuentoList;
	@JoinColumn(name = "CUSUARIO_CODIGO", referencedColumnName = "CUSUARIO_CODIGO")
	@ManyToOne(optional = false)
	private SipreUsuario					sipreUsuario;
	@JoinColumn(name = "CAGRUPADOR_CODIGO", referencedColumnName = "CAGRUPADOR_CODIGO")
	@ManyToOne
	private SipreAgrupador					sipreAgrupador;
	@JoinColumn(name = "CARMA_CODIGO", referencedColumnName = "CARMA_CODIGO")
	@ManyToOne
	private SipreArma						sipreArma;
	@JoinColumn(name = "CBANCO_CODIGO", referencedColumnName = "CBANCO_CODIGO")
	@ManyToOne
	private SipreBanco						sipreBanco;
	@JoinColumn(name = "CCARGO_CODIGO", referencedColumnName = "CCARGO_CODIGO")
	@ManyToOne
	private SipreCargo						sipreCargo;
	@JoinColumn(name = "CCEDULA_CODIGO", referencedColumnName = "CCEDULA_CODIGO")
	@ManyToOne
	private SipreCedula						sipreCedula;
	@JoinColumn(name = "CEA_CODIGO", referencedColumnName = "CEA_CODIGO")
	@ManyToOne
	private SipreEspecialidadAlterna		sipreEspecialidadAlterna;
	@JoinColumn(name = "CEC_CODIGO", referencedColumnName = "CEC_CODIGO")
	@ManyToOne(optional = false)
	private SipreEstadoCivil				sipreEstadoCivil;
	@JoinColumn(name = "CGRADO_CODIGO", referencedColumnName = "CGRADO_CODIGO")
	@ManyToOne(optional = false)
	private SipreGrado						sipreGrado;
	@JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SiprePersona					siprePersona;
	@JoinColumn(name = "CSA_CODIGO", referencedColumnName = "CSA_CODIGO")
	@ManyToOne
	private SipreSituacionAdm				sipreSituacionAdm;
	@JoinColumn(name = "CSC_CODIGO", referencedColumnName = "CSC_CODIGO")
	@ManyToOne
	private SipreSituacionCausal			sipreSituacionCausal;
	@JoinColumn(name = "CUNIDAD_CODIGO", referencedColumnName = "CUNIDAD_CODIGO")
	@ManyToOne(optional = false)
	private SipreUnidad						sipreUnidad;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePlanilla")
	private List<SiprePlanillaDetalle>		siprePlanillaDetalleList;

	public SiprePlanilla() {
	}

	public SiprePlanilla(SiprePlanillaPK siprePlanillaPK) {
		this.id = siprePlanillaPK;
	}

	public SiprePlanilla(String cpersonaNroAdm, String cplanillaMesProceso, Integer nplanillaNumProceso) {
		this.id = new SiprePlanillaPK(cpersonaNroAdm, cplanillaMesProceso, nplanillaNumProceso);
	}

	public SiprePlanillaPK getId() {
		return id;
	}

	public void setId(SiprePlanillaPK id) {
		this.id = id;
	}

	public String getCplanillaDni() {
		return cplanillaDni;
	}

	public void setCplanillaDni(String cplanillaDni) {
		this.cplanillaDni = cplanillaDni;
	}

	public String getCplanillaIndQui() {
		return cplanillaIndQui;
	}

	public void setCplanillaIndQui(String cplanillaIndQui) {
		this.cplanillaIndQui = cplanillaIndQui;
	}

	public Date getDplanillaFecFal() {
		return dplanillaFecFal;
	}

	public void setDplanillaFecFal(Date dplanillaFecFal) {
		this.dplanillaFecFal = dplanillaFecFal;
	}

	public Date getDplanillaFecIng() {
		return dplanillaFecIng;
	}

	public void setDplanillaFecIng(Date dplanillaFecIng) {
		this.dplanillaFecIng = dplanillaFecIng;
	}

	public String getVplanillaDocAlta() {
		return vplanillaDocAlta;
	}

	public void setVplanillaDocAlta(String vplanillaDocAlta) {
		this.vplanillaDocAlta = vplanillaDocAlta;
	}

	public String getVplanillaApeNom() {
		return vplanillaApeNom;
	}

	public void setVplanillaApeNom(String vplanillaApeNom) {
		this.vplanillaApeNom = vplanillaApeNom;
	}

	public String getCplanillaSexo() {
		return cplanillaSexo;
	}

	public void setCplanillaSexo(String cplanillaSexo) {
		this.cplanillaSexo = cplanillaSexo;
	}

	public Date getDplanillaFecNac() {
		return dplanillaFecNac;
	}

	public void setDplanillaFecNac(Date dplanillaFecNac) {
		this.dplanillaFecNac = dplanillaFecNac;
	}

	public Integer getNplanillaNroHijo() {
		return nplanillaNroHijo;
	}

	public void setNplanillaNroHijo(Integer nplanillaNroHijo) {
		this.nplanillaNroHijo = nplanillaNroHijo;
	}

	public String getCplanillaCodGraPen() {
		return cplanillaCodGraPen;
	}

	public void setCplanillaCodGraPen(String cplanillaCodGraPen) {
		this.cplanillaCodGraPen = cplanillaCodGraPen;
	}

	public String getCplanillaIndOnp() {
		return cplanillaIndOnp;
	}

	public void setCplanillaIndOnp(String cplanillaIndOnp) {
		this.cplanillaIndOnp = cplanillaIndOnp;
	}

	public BigDecimal getNplanillaPorUnif() {
		return nplanillaPorUnif;
	}

	public void setNplanillaPorUnif(BigDecimal nplanillaPorUnif) {
		this.nplanillaPorUnif = nplanillaPorUnif;
	}

	public String getVplanillaCadFunc() {
		return vplanillaCadFunc;
	}

	public void setVplanillaCadFunc(String vplanillaCadFunc) {
		this.vplanillaCadFunc = vplanillaCadFunc;
	}

	public String getVplanillaCodEssalud() {
		return vplanillaCodEssalud;
	}

	public void setVplanillaCodEssalud(String vplanillaCodEssalud) {
		this.vplanillaCodEssalud = vplanillaCodEssalud;
	}

	public String getVplanillaCuspp() {
		return vplanillaCuspp;
	}

	public void setVplanillaCuspp(String vplanillaCuspp) {
		this.vplanillaCuspp = vplanillaCuspp;
	}

	public String getCplanillaIndAguin() {
		return cplanillaIndAguin;
	}

	public void setCplanillaIndAguin(String cplanillaIndAguin) {
		this.cplanillaIndAguin = cplanillaIndAguin;
	}

	public Date getDplanillaFecAfiAfp() {
		return dplanillaFecAfiAfp;
	}

	public void setDplanillaFecAfiAfp(Date dplanillaFecAfiAfp) {
		this.dplanillaFecAfiAfp = dplanillaFecAfiAfp;
	}

	public Date getDplanillaFecFinContr() {
		return dplanillaFecFinContr;
	}

	public void setDplanillaFecFinContr(Date dplanillaFecFinContr) {
		this.dplanillaFecFinContr = dplanillaFecFinContr;
	}

	public Date getDplanillaFecPromo() {
		return dplanillaFecPromo;
	}

	public void setDplanillaFecPromo(Date dplanillaFecPromo) {
		this.dplanillaFecPromo = dplanillaFecPromo;
	}

	public Integer getNplanillaRetAscenso() {
		return nplanillaRetAscenso;
	}

	public void setNplanillaRetAscenso(Integer nplanillaRetAscenso) {
		this.nplanillaRetAscenso = nplanillaRetAscenso;
	}

	public String getCplanillaIndLicencia() {
		return cplanillaIndLicencia;
	}

	public void setCplanillaIndLicencia(String cplanillaIndLicencia) {
		this.cplanillaIndLicencia = cplanillaIndLicencia;
	}

	public Date getDplanillaFecRetiro() {
		return dplanillaFecRetiro;
	}

	public void setDplanillaFecRetiro(Date dplanillaFecRetiro) {
		this.dplanillaFecRetiro = dplanillaFecRetiro;
	}

	public String getVplanillaDocRetiro() {
		return vplanillaDocRetiro;
	}

	public void setVplanillaDocRetiro(String vplanillaDocRetiro) {
		this.vplanillaDocRetiro = vplanillaDocRetiro;
	}

	public String getCplanillaSerRecon() {
		return cplanillaSerRecon;
	}

	public void setCplanillaSerRecon(String cplanillaSerRecon) {
		this.cplanillaSerRecon = cplanillaSerRecon;
	}

	public String getVplanillaDocRecon() {
		return vplanillaDocRecon;
	}

	public void setVplanillaDocRecon(String vplanillaDocRecon) {
		this.vplanillaDocRecon = vplanillaDocRecon;
	}

	public BigDecimal getNplanillaPorPension() {
		return nplanillaPorPension;
	}

	public void setNplanillaPorPension(BigDecimal nplanillaPorPension) {
		this.nplanillaPorPension = nplanillaPorPension;
	}

	public String getCplanillaSexPension() {
		return cplanillaSexPension;
	}

	public void setCplanillaSexPension(String cplanillaSexPension) {
		this.cplanillaSexPension = cplanillaSexPension;
	}

	public String getVplanillaNomCausante() {
		return vplanillaNomCausante;
	}

	public void setVplanillaNomCausante(String vplanillaNomCausante) {
		this.vplanillaNomCausante = vplanillaNomCausante;
	}

	public String getCplanillaIndActPen() {
		return cplanillaIndActPen;
	}

	public void setCplanillaIndActPen(String cplanillaIndActPen) {
		this.cplanillaIndActPen = cplanillaIndActPen;
	}

	public String getCplanillaIndCalculo() {
		return cplanillaIndCalculo;
	}

	public void setCplanillaIndCalculo(String cplanillaIndCalculo) {
		this.cplanillaIndCalculo = cplanillaIndCalculo;
	}

	public String getNplanillaTieServicio() {
		return nplanillaTieServicio;
	}

	public void setNplanillaTieServicio(String nplanillaTieServicio) {
		this.nplanillaTieServicio = nplanillaTieServicio;
	}

	public String getCplanillaUsuMod() {
		return cplanillaUsuMod;
	}

	public void setCplanillaUsuMod(String cplanillaUsuMod) {
		this.cplanillaUsuMod = cplanillaUsuMod;
	}

	public Date getDplanillaFecReg() {
		return dplanillaFecReg;
	}

	public void setDplanillaFecReg(Date dplanillaFecReg) {
		this.dplanillaFecReg = dplanillaFecReg;
	}

	public Date getDplanillaFecMod() {
		return dplanillaFecMod;
	}

	public void setDplanillaFecMod(Date dplanillaFecMod) {
		this.dplanillaFecMod = dplanillaFecMod;
	}

	public List<SipreCalculoDescuentoLey> getSipreCalculoDescuentoLeyList() {
		return sipreCalculoDescuentoLeyList;
	}

	public void setSipreCalculoDescuentoLeyList(List<SipreCalculoDescuentoLey> sipreCalculoDescuentoLeyList) {
		this.sipreCalculoDescuentoLeyList = sipreCalculoDescuentoLeyList;
	}

	/*
	 * public SipreCalculoQuintaCategoria getSipreCalculoQuintaCategoria() {
	 * return sipreCalculoQuintaCategoria; }
	 * 
	 * public void setSipreCalculoQuintaCategoria(SipreCalculoQuintaCategoria
	 * sipreCalculoQuintaCategoria) { this.sipreCalculoQuintaCategoria =
	 * sipreCalculoQuintaCategoria; }
	 */

	public List<SiprePlanillaDescuento> getSiprePlanillaDescuentoList() {
		return siprePlanillaDescuentoList;
	}

	public void setSiprePlanillaDescuentoList(List<SiprePlanillaDescuento> siprePlanillaDescuentoList) {
		this.siprePlanillaDescuentoList = siprePlanillaDescuentoList;
	}

	public SipreUsuario getSipreUsuario() {
		return sipreUsuario;
	}

	public void setSipreUsuario(SipreUsuario sipreUsuario) {
		this.sipreUsuario = sipreUsuario;
	}

	public SipreAgrupador getSipreAgrupador() {
		return sipreAgrupador;
	}

	public void setSipreAgrupador(SipreAgrupador sipreAgrupador) {
		this.sipreAgrupador = sipreAgrupador;
	}

	public SipreArma getSipreArma() {
		return sipreArma;
	}

	public void setSipreArma(SipreArma sipreArma) {
		this.sipreArma = sipreArma;
	}

	public SipreBanco getSipreBanco() {
		return sipreBanco;
	}

	public void setSipreBanco(SipreBanco sipreBanco) {
		this.sipreBanco = sipreBanco;
	}

	public SipreCargo getSipreCargo() {
		return sipreCargo;
	}

	public void setSipreCargo(SipreCargo sipreCargo) {
		this.sipreCargo = sipreCargo;
	}

	public SipreCedula getSipreCedula() {
		return sipreCedula;
	}

	public void setSipreCedula(SipreCedula sipreCedula) {
		this.sipreCedula = sipreCedula;
	}

	public SipreEspecialidadAlterna getSipreEspecialidadAlterna() {
		return sipreEspecialidadAlterna;
	}

	public void setSipreEspecialidadAlterna(SipreEspecialidadAlterna sipreEspecialidadAlterna) {
		this.sipreEspecialidadAlterna = sipreEspecialidadAlterna;
	}

	public SipreEstadoCivil getSipreEstadoCivil() {
		return sipreEstadoCivil;
	}

	public void setSipreEstadoCivil(SipreEstadoCivil sipreEstadoCivil) {
		this.sipreEstadoCivil = sipreEstadoCivil;
	}

	public SipreGrado getSipreGrado() {
		return sipreGrado;
	}

	public void setSipreGrado(SipreGrado sipreGrado) {
		this.sipreGrado = sipreGrado;
	}

	public SiprePersona getSiprePersona() {
		return siprePersona;
	}

	public void setSiprePersona(SiprePersona siprePersona) {
		this.siprePersona = siprePersona;
	}

	public SipreSituacionAdm getSipreSituacionAdm() {
		return sipreSituacionAdm;
	}

	public void setSipreSituacionAdm(SipreSituacionAdm sipreSituacionAdm) {
		this.sipreSituacionAdm = sipreSituacionAdm;
	}

	public SipreSituacionCausal getSipreSituacionCausal() {
		return sipreSituacionCausal;
	}

	public void setSipreSituacionCausal(SipreSituacionCausal sipreSituacionCausal) {
		this.sipreSituacionCausal = sipreSituacionCausal;
	}

	public SipreUnidad getSipreUnidad() {
		return sipreUnidad;
	}

	public void setSipreUnidad(SipreUnidad sipreUnidad) {
		this.sipreUnidad = sipreUnidad;
	}

	public List<SiprePlanillaDetalle> getSiprePlanillaDetalleList() {
		return siprePlanillaDetalleList;
	}

	public void setSiprePlanillaDetalleList(List<SiprePlanillaDetalle> siprePlanillaDetalleList) {
		this.siprePlanillaDetalleList = siprePlanillaDetalleList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SiprePlanilla)) {
			return false;
		}
		SiprePlanilla other = (SiprePlanilla) object;
		if (((this.id == null) && (other.id != null)) || ((this.id != null) && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.SiprePlanilla[ siprePlanillaPK=" + id + " ]";
	}

}
