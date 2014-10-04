package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SIPRE_PERSONA database table.
 * 
 */
@Entity
@Table(name="SIPRE_PERSONA")
@NamedQuery(name="SiprePersona.findAll", query="SELECT s FROM SiprePersona s")
public class SiprePersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CPERSONA_NRO_ADM")
	private String cpersonaNroAdm;

	@Column(name="CPERSONA_COD_GRA_PEN")
	private String cpersonaCodGraPen;

	@Column(name="CPERSONA_DNI")
	private String cpersonaDni;

	@Column(name="CPERSONA_IND_AGUIN")
	private String cpersonaIndAguin;

	@Column(name="CPERSONA_IND_LICENCIA")
	private String cpersonaIndLicencia;

	@Column(name="CPERSONA_IND_ONP")
	private String cpersonaIndOnp;

	@Column(name="CPERSONA_IND_QUI")
	private String cpersonaIndQui;

	@Column(name="CPERSONA_SER_RECON")
	private String cpersonaSerRecon;

	@Column(name="CPERSONA_SEX_PENSION")
	private String cpersonaSexPension;

	@Column(name="CPERSONA_SEXO")
	private String cpersonaSexo;

	@Temporal(TemporalType.DATE)
	@Column(name="DPERSONA_FEC_AFI_AFP")
	private Date dpersonaFecAfiAfp;

	@Temporal(TemporalType.DATE)
	@Column(name="DPERSONA_FEC_FAL")
	private Date dpersonaFecFal;

	@Temporal(TemporalType.DATE)
	@Column(name="DPERSONA_FEC_FIN_CONTR")
	private Date dpersonaFecFinContr;

	@Temporal(TemporalType.DATE)
	@Column(name="DPERSONA_FEC_ING")
	private Date dpersonaFecIng;

	@Temporal(TemporalType.DATE)
	@Column(name="DPERSONA_FEC_NAC")
	private Date dpersonaFecNac;

	@Temporal(TemporalType.DATE)
	@Column(name="DPERSONA_FEC_PROMO")
	private Date dpersonaFecPromo;

	@Temporal(TemporalType.DATE)
	@Column(name="DPERSONA_FEC_RETIRO")
	private Date dpersonaFecRetiro;

	@Column(name="NPERSONA_NRO_HIJO")
	private BigDecimal npersonaNroHijo;

	@Column(name="NPERSONA_POR_PENSION")
	private BigDecimal npersonaPorPension;

	@Column(name="NPERSONA_POR_UNIF")
	private BigDecimal npersonaPorUnif;

	@Column(name="NPERSONA_RET_ASCENSO")
	private BigDecimal npersonaRetAscenso;

	@Column(name="VPERSONA_APE_NOM")
	private String vpersonaApeNom;

	@Column(name="VPERSONA_CAD_FUNC")
	private String vpersonaCadFunc;

	@Column(name="VPERSONA_COD_ESSALUD")
	private String vpersonaCodEssalud;

	@Column(name="VPERSONA_CUSPP")
	private String vpersonaCuspp;

	@Column(name="VPERSONA_DOC_ALTA")
	private String vpersonaDocAlta;

	@Column(name="VPERSONA_DOC_RECON")
	private String vpersonaDocRecon;

	@Column(name="VPERSONA_DOC_RETIRO")
	private String vpersonaDocRetiro;

	@Column(name="VPERSONA_NOM_CAUSANTE")
	private String vpersonaNomCausante;

	//bi-directional many-to-one association to SipreGrado
	@ManyToOne
	@JoinColumn(name="CGRADO_CODIGO",insertable = false, updatable = false)
	private SipreGrado sipreGrado;

	//bi-directional many-to-one association to SipreUnidad
	@ManyToOne
	@JoinColumn(name="CUNIDAD_CODIGO",insertable = false, updatable = false)
	private SipreUnidad sipreUnidad;

	//bi-directional many-to-one association to SiprePlanilla
	@OneToMany(mappedBy="siprePersona")
	private List<SiprePlanilla> siprePlanillas;

	//bi-directional one-to-one association to SipreTmpBanco
	@OneToOne(mappedBy="siprePersona")
	private SipreTmpBanco sipreTmpBanco;

	//bi-directional many-to-one association to SipreTmpBonificacion
	@OneToMany(mappedBy="siprePersona")
	private List<SipreTmpBonificacion> sipreTmpBonificacions;

	//bi-directional many-to-one association to SipreTmpEntidadCrediticia
	@OneToMany(mappedBy="siprePersona")
	private List<SipreTmpEntidadCrediticia> sipreTmpEntidadCrediticias;

	//bi-directional many-to-one association to SipreTmpGuardia
	@OneToMany(mappedBy="siprePersona")
	private List<SipreTmpGuardia> sipreTmpGuardias;

	public SiprePersona() {
	}

	public String getCpersonaNroAdm() {
		return this.cpersonaNroAdm;
	}

	public void setCpersonaNroAdm(String cpersonaNroAdm) {
		this.cpersonaNroAdm = cpersonaNroAdm;
	}

	public String getCpersonaCodGraPen() {
		return this.cpersonaCodGraPen;
	}

	public void setCpersonaCodGraPen(String cpersonaCodGraPen) {
		this.cpersonaCodGraPen = cpersonaCodGraPen;
	}

	public String getCpersonaDni() {
		return this.cpersonaDni;
	}

	public void setCpersonaDni(String cpersonaDni) {
		this.cpersonaDni = cpersonaDni;
	}

	public String getCpersonaIndAguin() {
		return this.cpersonaIndAguin;
	}

	public void setCpersonaIndAguin(String cpersonaIndAguin) {
		this.cpersonaIndAguin = cpersonaIndAguin;
	}

	public String getCpersonaIndLicencia() {
		return this.cpersonaIndLicencia;
	}

	public void setCpersonaIndLicencia(String cpersonaIndLicencia) {
		this.cpersonaIndLicencia = cpersonaIndLicencia;
	}

	public String getCpersonaIndOnp() {
		return this.cpersonaIndOnp;
	}

	public void setCpersonaIndOnp(String cpersonaIndOnp) {
		this.cpersonaIndOnp = cpersonaIndOnp;
	}

	public String getCpersonaIndQui() {
		return this.cpersonaIndQui;
	}

	public void setCpersonaIndQui(String cpersonaIndQui) {
		this.cpersonaIndQui = cpersonaIndQui;
	}

	public String getCpersonaSerRecon() {
		return this.cpersonaSerRecon;
	}

	public void setCpersonaSerRecon(String cpersonaSerRecon) {
		this.cpersonaSerRecon = cpersonaSerRecon;
	}

	public String getCpersonaSexPension() {
		return this.cpersonaSexPension;
	}

	public void setCpersonaSexPension(String cpersonaSexPension) {
		this.cpersonaSexPension = cpersonaSexPension;
	}

	public String getCpersonaSexo() {
		return this.cpersonaSexo;
	}

	public void setCpersonaSexo(String cpersonaSexo) {
		this.cpersonaSexo = cpersonaSexo;
	}

	public Date getDpersonaFecAfiAfp() {
		return this.dpersonaFecAfiAfp;
	}

	public void setDpersonaFecAfiAfp(Date dpersonaFecAfiAfp) {
		this.dpersonaFecAfiAfp = dpersonaFecAfiAfp;
	}

	public Date getDpersonaFecFal() {
		return this.dpersonaFecFal;
	}

	public void setDpersonaFecFal(Date dpersonaFecFal) {
		this.dpersonaFecFal = dpersonaFecFal;
	}

	public Date getDpersonaFecFinContr() {
		return this.dpersonaFecFinContr;
	}

	public void setDpersonaFecFinContr(Date dpersonaFecFinContr) {
		this.dpersonaFecFinContr = dpersonaFecFinContr;
	}

	public Date getDpersonaFecIng() {
		return this.dpersonaFecIng;
	}

	public void setDpersonaFecIng(Date dpersonaFecIng) {
		this.dpersonaFecIng = dpersonaFecIng;
	}

	public Date getDpersonaFecNac() {
		return this.dpersonaFecNac;
	}

	public void setDpersonaFecNac(Date dpersonaFecNac) {
		this.dpersonaFecNac = dpersonaFecNac;
	}

	public Date getDpersonaFecPromo() {
		return this.dpersonaFecPromo;
	}

	public void setDpersonaFecPromo(Date dpersonaFecPromo) {
		this.dpersonaFecPromo = dpersonaFecPromo;
	}

	public Date getDpersonaFecRetiro() {
		return this.dpersonaFecRetiro;
	}

	public void setDpersonaFecRetiro(Date dpersonaFecRetiro) {
		this.dpersonaFecRetiro = dpersonaFecRetiro;
	}

	public BigDecimal getNpersonaNroHijo() {
		return this.npersonaNroHijo;
	}

	public void setNpersonaNroHijo(BigDecimal npersonaNroHijo) {
		this.npersonaNroHijo = npersonaNroHijo;
	}

	public BigDecimal getNpersonaPorPension() {
		return this.npersonaPorPension;
	}

	public void setNpersonaPorPension(BigDecimal npersonaPorPension) {
		this.npersonaPorPension = npersonaPorPension;
	}

	public BigDecimal getNpersonaPorUnif() {
		return this.npersonaPorUnif;
	}

	public void setNpersonaPorUnif(BigDecimal npersonaPorUnif) {
		this.npersonaPorUnif = npersonaPorUnif;
	}

	public BigDecimal getNpersonaRetAscenso() {
		return this.npersonaRetAscenso;
	}

	public void setNpersonaRetAscenso(BigDecimal npersonaRetAscenso) {
		this.npersonaRetAscenso = npersonaRetAscenso;
	}

	public String getVpersonaApeNom() {
		return this.vpersonaApeNom;
	}

	public void setVpersonaApeNom(String vpersonaApeNom) {
		this.vpersonaApeNom = vpersonaApeNom;
	}

	public String getVpersonaCadFunc() {
		return this.vpersonaCadFunc;
	}

	public void setVpersonaCadFunc(String vpersonaCadFunc) {
		this.vpersonaCadFunc = vpersonaCadFunc;
	}

	public String getVpersonaCodEssalud() {
		return this.vpersonaCodEssalud;
	}

	public void setVpersonaCodEssalud(String vpersonaCodEssalud) {
		this.vpersonaCodEssalud = vpersonaCodEssalud;
	}

	public String getVpersonaCuspp() {
		return this.vpersonaCuspp;
	}

	public void setVpersonaCuspp(String vpersonaCuspp) {
		this.vpersonaCuspp = vpersonaCuspp;
	}

	public String getVpersonaDocAlta() {
		return this.vpersonaDocAlta;
	}

	public void setVpersonaDocAlta(String vpersonaDocAlta) {
		this.vpersonaDocAlta = vpersonaDocAlta;
	}

	public String getVpersonaDocRecon() {
		return this.vpersonaDocRecon;
	}

	public void setVpersonaDocRecon(String vpersonaDocRecon) {
		this.vpersonaDocRecon = vpersonaDocRecon;
	}

	public String getVpersonaDocRetiro() {
		return this.vpersonaDocRetiro;
	}

	public void setVpersonaDocRetiro(String vpersonaDocRetiro) {
		this.vpersonaDocRetiro = vpersonaDocRetiro;
	}

	public String getVpersonaNomCausante() {
		return this.vpersonaNomCausante;
	}

	public void setVpersonaNomCausante(String vpersonaNomCausante) {
		this.vpersonaNomCausante = vpersonaNomCausante;
	}

	public SipreGrado getSipreGrado() {
		return this.sipreGrado;
	}

	public void setSipreGrado(SipreGrado sipreGrado) {
		this.sipreGrado = sipreGrado;
	}

	public SipreUnidad getSipreUnidad() {
		return this.sipreUnidad;
	}

	public void setSipreUnidad(SipreUnidad sipreUnidad) {
		this.sipreUnidad = sipreUnidad;
	}

	public List<SiprePlanilla> getSiprePlanillas() {
		return this.siprePlanillas;
	}

	public void setSiprePlanillas(List<SiprePlanilla> siprePlanillas) {
		this.siprePlanillas = siprePlanillas;
	}

	public SiprePlanilla addSiprePlanilla(SiprePlanilla siprePlanilla) {
		getSiprePlanillas().add(siprePlanilla);
		siprePlanilla.setSiprePersona(this);

		return siprePlanilla;
	}

	public SiprePlanilla removeSiprePlanilla(SiprePlanilla siprePlanilla) {
		getSiprePlanillas().remove(siprePlanilla);
		siprePlanilla.setSiprePersona(null);

		return siprePlanilla;
	}

	public SipreTmpBanco getSipreTmpBanco() {
		return this.sipreTmpBanco;
	}

	public void setSipreTmpBanco(SipreTmpBanco sipreTmpBanco) {
		this.sipreTmpBanco = sipreTmpBanco;
	}

	public List<SipreTmpBonificacion> getSipreTmpBonificacions() {
		return this.sipreTmpBonificacions;
	}

	public void setSipreTmpBonificacions(List<SipreTmpBonificacion> sipreTmpBonificacions) {
		this.sipreTmpBonificacions = sipreTmpBonificacions;
	}

	public SipreTmpBonificacion addSipreTmpBonificacion(SipreTmpBonificacion sipreTmpBonificacion) {
		getSipreTmpBonificacions().add(sipreTmpBonificacion);
		sipreTmpBonificacion.setSiprePersona(this);

		return sipreTmpBonificacion;
	}

	public SipreTmpBonificacion removeSipreTmpBonificacion(SipreTmpBonificacion sipreTmpBonificacion) {
		getSipreTmpBonificacions().remove(sipreTmpBonificacion);
		sipreTmpBonificacion.setSiprePersona(null);

		return sipreTmpBonificacion;
	}

	public List<SipreTmpEntidadCrediticia> getSipreTmpEntidadCrediticias() {
		return this.sipreTmpEntidadCrediticias;
	}

	public void setSipreTmpEntidadCrediticias(List<SipreTmpEntidadCrediticia> sipreTmpEntidadCrediticias) {
		this.sipreTmpEntidadCrediticias = sipreTmpEntidadCrediticias;
	}

	public SipreTmpEntidadCrediticia addSipreTmpEntidadCrediticia(SipreTmpEntidadCrediticia sipreTmpEntidadCrediticia) {
		getSipreTmpEntidadCrediticias().add(sipreTmpEntidadCrediticia);
		sipreTmpEntidadCrediticia.setSiprePersona(this);

		return sipreTmpEntidadCrediticia;
	}

	public SipreTmpEntidadCrediticia removeSipreTmpEntidadCrediticia(SipreTmpEntidadCrediticia sipreTmpEntidadCrediticia) {
		getSipreTmpEntidadCrediticias().remove(sipreTmpEntidadCrediticia);
		sipreTmpEntidadCrediticia.setSiprePersona(null);

		return sipreTmpEntidadCrediticia;
	}

	public List<SipreTmpGuardia> getSipreTmpGuardias() {
		return this.sipreTmpGuardias;
	}

	public void setSipreTmpGuardias(List<SipreTmpGuardia> sipreTmpGuardias) {
		this.sipreTmpGuardias = sipreTmpGuardias;
	}

	public SipreTmpGuardia addSipreTmpGuardia(SipreTmpGuardia sipreTmpGuardia) {
		getSipreTmpGuardias().add(sipreTmpGuardia);
		sipreTmpGuardia.setSiprePersona(this);

		return sipreTmpGuardia;
	}

	public SipreTmpGuardia removeSipreTmpGuardia(SipreTmpGuardia sipreTmpGuardia) {
		getSipreTmpGuardias().remove(sipreTmpGuardia);
		sipreTmpGuardia.setSiprePersona(null);

		return sipreTmpGuardia;
	}

}