package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SIPRE_CONCEPTO_DESCUENTO_LEY database table.
 * 
 */
@Entity
@Table(name="SIPRE_CONCEPTO_DESCUENTO_LEY")
@NamedQuery(name="SipreConceptoDescuentoLey.findAll", query="SELECT s FROM SipreConceptoDescuentoLey s")
public class SipreConceptoDescuentoLey implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreConceptoDescuentoLeyPK id;

	@Column(name="CCDL_ESTADO")
	private String ccdlEstado;

	@Column(name="CCDL_IND_POR_MONTO")
	private String ccdlIndPorMonto;

	@Column(name="CCDL_SITUACION")
	private String ccdlSituacion;

	@Column(name="NCDL_MAX_APLICABLE")
	private BigDecimal ncdlMaxAplicable;

	@Column(name="NCDL_MIN_APLICABLE")
	private BigDecimal ncdlMinAplicable;

	@Column(name="NCDL_POR_EMPLEADO")
	private BigDecimal ncdlPorEmpleado;

	@Column(name="NCDL_POR_EMPLEADOR")
	private BigDecimal ncdlPorEmpleador;

	//bi-directional many-to-one association to SipreConceptoDescuento
	@ManyToOne
	@JoinColumn(name="CCD_CODIGO")
	private SipreConceptoDescuento sipreConceptoDescuento;

	//bi-directional many-to-one association to SipreDescuentoLeyDet
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CDL_CODIGO", referencedColumnName="CDL_CODIGO"),
		@JoinColumn(name="CDLD_CODIGO", referencedColumnName="CDLD_CODIGO")
		})
	private SipreDescuentoLeyDet sipreDescuentoLeyDet;

	//bi-directional many-to-one association to SipreEntidadCrediticia
	@ManyToOne
	@JoinColumn(name="CEC_CODIGO")
	private SipreEntidadCrediticia sipreEntidadCrediticia;

	public SipreConceptoDescuentoLey() {
	}

	public SipreConceptoDescuentoLeyPK getId() {
		return this.id;
	}

	public void setId(SipreConceptoDescuentoLeyPK id) {
		this.id = id;
	}

	public String getCcdlEstado() {
		return this.ccdlEstado;
	}

	public void setCcdlEstado(String ccdlEstado) {
		this.ccdlEstado = ccdlEstado;
	}

	public String getCcdlIndPorMonto() {
		return this.ccdlIndPorMonto;
	}

	public void setCcdlIndPorMonto(String ccdlIndPorMonto) {
		this.ccdlIndPorMonto = ccdlIndPorMonto;
	}

	public String getCcdlSituacion() {
		return this.ccdlSituacion;
	}

	public void setCcdlSituacion(String ccdlSituacion) {
		this.ccdlSituacion = ccdlSituacion;
	}

	public BigDecimal getNcdlMaxAplicable() {
		return this.ncdlMaxAplicable;
	}

	public void setNcdlMaxAplicable(BigDecimal ncdlMaxAplicable) {
		this.ncdlMaxAplicable = ncdlMaxAplicable;
	}

	public BigDecimal getNcdlMinAplicable() {
		return this.ncdlMinAplicable;
	}

	public void setNcdlMinAplicable(BigDecimal ncdlMinAplicable) {
		this.ncdlMinAplicable = ncdlMinAplicable;
	}

	public BigDecimal getNcdlPorEmpleado() {
		return this.ncdlPorEmpleado;
	}

	public void setNcdlPorEmpleado(BigDecimal ncdlPorEmpleado) {
		this.ncdlPorEmpleado = ncdlPorEmpleado;
	}

	public BigDecimal getNcdlPorEmpleador() {
		return this.ncdlPorEmpleador;
	}

	public void setNcdlPorEmpleador(BigDecimal ncdlPorEmpleador) {
		this.ncdlPorEmpleador = ncdlPorEmpleador;
	}

	public SipreConceptoDescuento getSipreConceptoDescuento() {
		return this.sipreConceptoDescuento;
	}

	public void setSipreConceptoDescuento(SipreConceptoDescuento sipreConceptoDescuento) {
		this.sipreConceptoDescuento = sipreConceptoDescuento;
	}

	public SipreDescuentoLeyDet getSipreDescuentoLeyDet() {
		return this.sipreDescuentoLeyDet;
	}

	public void setSipreDescuentoLeyDet(SipreDescuentoLeyDet sipreDescuentoLeyDet) {
		this.sipreDescuentoLeyDet = sipreDescuentoLeyDet;
	}

	public SipreEntidadCrediticia getSipreEntidadCrediticia() {
		return this.sipreEntidadCrediticia;
	}

	public void setSipreEntidadCrediticia(SipreEntidadCrediticia sipreEntidadCrediticia) {
		this.sipreEntidadCrediticia = sipreEntidadCrediticia;
	}

}