package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SIPRE_PLANILLA database table.
 * 
 */
@Entity
@Table(name="SIPRE_PLANILLA")
@NamedQuery(name="SiprePlanilla.findAll", query="SELECT s FROM SiprePlanilla s")
public class SiprePlanilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiprePlanillaPK id;

	@Column(name="CPLANILLA_DNI")
	private String cplanillaDni;

	@Column(name="CPLANILLA_GRA_EFEC")
	private String cplanillaGraEfec;

	@Column(name="CPLANILLA_GRA_PENSION")
	private String cplanillaGraPension;

	@Column(name="CPLANILLA_IND_ACT_PEN")
	private String cplanillaIndActPen;

	@Column(name="CPLANILLA_IND_CALCULO")
	private String cplanillaIndCalculo;

	@Column(name="CPLANILLA_IND_SEXO")
	private String cplanillaIndSexo;

	@Column(name="CPLANILLA_TIP_CEDULA")
	private String cplanillaTipCedula;

	@Column(name="CPLANILLA_UNIDAD")
	private String cplanillaUnidad;

	@Temporal(TemporalType.DATE)
	@Column(name="DPLANILLA_FEC_INGRESO")
	private Date dplanillaFecIngreso;

	@Temporal(TemporalType.DATE)
	@Column(name="DPLANILLA_FEC_NAC")
	private Date dplanillaFecNac;

	@Temporal(TemporalType.DATE)
	@Column(name="DPLANILLA_FEC_RETIRO")
	private Date dplanillaFecRetiro;

	@Column(name="NPLANILLA_TIE_SERVICIO")
	private BigDecimal nplanillaTieServicio;

	@Column(name="VPLANILLA_APE_NOM")
	private String vplanillaApeNom;

	@Column(name="VPLANILLA_NRO_DOC_ALTA")
	private String vplanillaNroDocAlta;

	//bi-directional many-to-one association to SiprePersona
	@ManyToOne
	@JoinColumn(name="CPERSONA_NRO_ADM")
	private SiprePersona siprePersona;

	//bi-directional many-to-one association to SiprePlanillaDetalle
	@OneToMany(mappedBy="siprePlanilla")
	private List<SiprePlanillaDetalle> siprePlanillaDetalles;

	public SiprePlanilla() {
	}

	public SiprePlanillaPK getId() {
		return this.id;
	}

	public void setId(SiprePlanillaPK id) {
		this.id = id;
	}

	public String getCplanillaDni() {
		return this.cplanillaDni;
	}

	public void setCplanillaDni(String cplanillaDni) {
		this.cplanillaDni = cplanillaDni;
	}

	public String getCplanillaGraEfec() {
		return this.cplanillaGraEfec;
	}

	public void setCplanillaGraEfec(String cplanillaGraEfec) {
		this.cplanillaGraEfec = cplanillaGraEfec;
	}

	public String getCplanillaGraPension() {
		return this.cplanillaGraPension;
	}

	public void setCplanillaGraPension(String cplanillaGraPension) {
		this.cplanillaGraPension = cplanillaGraPension;
	}

	public String getCplanillaIndActPen() {
		return this.cplanillaIndActPen;
	}

	public void setCplanillaIndActPen(String cplanillaIndActPen) {
		this.cplanillaIndActPen = cplanillaIndActPen;
	}

	public String getCplanillaIndCalculo() {
		return this.cplanillaIndCalculo;
	}

	public void setCplanillaIndCalculo(String cplanillaIndCalculo) {
		this.cplanillaIndCalculo = cplanillaIndCalculo;
	}

	public String getCplanillaIndSexo() {
		return this.cplanillaIndSexo;
	}

	public void setCplanillaIndSexo(String cplanillaIndSexo) {
		this.cplanillaIndSexo = cplanillaIndSexo;
	}

	public String getCplanillaTipCedula() {
		return this.cplanillaTipCedula;
	}

	public void setCplanillaTipCedula(String cplanillaTipCedula) {
		this.cplanillaTipCedula = cplanillaTipCedula;
	}

	public String getCplanillaUnidad() {
		return this.cplanillaUnidad;
	}

	public void setCplanillaUnidad(String cplanillaUnidad) {
		this.cplanillaUnidad = cplanillaUnidad;
	}

	public Date getDplanillaFecIngreso() {
		return this.dplanillaFecIngreso;
	}

	public void setDplanillaFecIngreso(Date dplanillaFecIngreso) {
		this.dplanillaFecIngreso = dplanillaFecIngreso;
	}

	public Date getDplanillaFecNac() {
		return this.dplanillaFecNac;
	}

	public void setDplanillaFecNac(Date dplanillaFecNac) {
		this.dplanillaFecNac = dplanillaFecNac;
	}

	public Date getDplanillaFecRetiro() {
		return this.dplanillaFecRetiro;
	}

	public void setDplanillaFecRetiro(Date dplanillaFecRetiro) {
		this.dplanillaFecRetiro = dplanillaFecRetiro;
	}

	public BigDecimal getNplanillaTieServicio() {
		return this.nplanillaTieServicio;
	}

	public void setNplanillaTieServicio(BigDecimal nplanillaTieServicio) {
		this.nplanillaTieServicio = nplanillaTieServicio;
	}

	public String getVplanillaApeNom() {
		return this.vplanillaApeNom;
	}

	public void setVplanillaApeNom(String vplanillaApeNom) {
		this.vplanillaApeNom = vplanillaApeNom;
	}

	public String getVplanillaNroDocAlta() {
		return this.vplanillaNroDocAlta;
	}

	public void setVplanillaNroDocAlta(String vplanillaNroDocAlta) {
		this.vplanillaNroDocAlta = vplanillaNroDocAlta;
	}

	public SiprePersona getSiprePersona() {
		return this.siprePersona;
	}

	public void setSiprePersona(SiprePersona siprePersona) {
		this.siprePersona = siprePersona;
	}

	public List<SiprePlanillaDetalle> getSiprePlanillaDetalles() {
		return this.siprePlanillaDetalles;
	}

	public void setSiprePlanillaDetalles(List<SiprePlanillaDetalle> siprePlanillaDetalles) {
		this.siprePlanillaDetalles = siprePlanillaDetalles;
	}

	public SiprePlanillaDetalle addSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
		getSiprePlanillaDetalles().add(siprePlanillaDetalle);
		siprePlanillaDetalle.setSiprePlanilla(this);

		return siprePlanillaDetalle;
	}

	public SiprePlanillaDetalle removeSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
		getSiprePlanillaDetalles().remove(siprePlanillaDetalle);
		siprePlanillaDetalle.setSiprePlanilla(null);

		return siprePlanillaDetalle;
	}

}