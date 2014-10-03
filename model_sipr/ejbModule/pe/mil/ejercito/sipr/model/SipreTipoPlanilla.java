package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_TIPO_PLANILLA database table.
 * 
 */
@Entity
@Table(name="SIPRE_TIPO_PLANILLA")
@NamedQuery(name="SipreTipoPlanilla.findAll", query="SELECT s FROM SipreTipoPlanilla s")
public class SipreTipoPlanilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CTP_CODIGO")
	private String ctpCodigo;

	@Column(name="CTP_IND_AFE_IRENTA")
	private String ctpIndAfeIrenta;

	@Column(name="CTP_IND_AFE_NETO")
	private String ctpIndAfeNeto;

	@Column(name="VTP_DSC")
	private String vtpDsc;

	//bi-directional many-to-one association to SipreConceptoIngreso
	@OneToMany(mappedBy="sipreTipoPlanilla")
	private List<SipreConceptoIngreso> sipreConceptoIngresos;

	//bi-directional many-to-one association to SiprePlanillaDetalle
	@OneToMany(mappedBy="sipreTipoPlanilla")
	private List<SiprePlanillaDetalle> siprePlanillaDetalles;

	public SipreTipoPlanilla() {
	}

	public String getCtpCodigo() {
		return this.ctpCodigo;
	}

	public void setCtpCodigo(String ctpCodigo) {
		this.ctpCodigo = ctpCodigo;
	}

	public String getCtpIndAfeIrenta() {
		return this.ctpIndAfeIrenta;
	}

	public void setCtpIndAfeIrenta(String ctpIndAfeIrenta) {
		this.ctpIndAfeIrenta = ctpIndAfeIrenta;
	}

	public String getCtpIndAfeNeto() {
		return this.ctpIndAfeNeto;
	}

	public void setCtpIndAfeNeto(String ctpIndAfeNeto) {
		this.ctpIndAfeNeto = ctpIndAfeNeto;
	}

	public String getVtpDsc() {
		return this.vtpDsc;
	}

	public void setVtpDsc(String vtpDsc) {
		this.vtpDsc = vtpDsc;
	}

	public List<SipreConceptoIngreso> getSipreConceptoIngresos() {
		return this.sipreConceptoIngresos;
	}

	public void setSipreConceptoIngresos(List<SipreConceptoIngreso> sipreConceptoIngresos) {
		this.sipreConceptoIngresos = sipreConceptoIngresos;
	}

	public SipreConceptoIngreso addSipreConceptoIngreso(SipreConceptoIngreso sipreConceptoIngreso) {
		getSipreConceptoIngresos().add(sipreConceptoIngreso);
		sipreConceptoIngreso.setSipreTipoPlanilla(this);

		return sipreConceptoIngreso;
	}

	public SipreConceptoIngreso removeSipreConceptoIngreso(SipreConceptoIngreso sipreConceptoIngreso) {
		getSipreConceptoIngresos().remove(sipreConceptoIngreso);
		sipreConceptoIngreso.setSipreTipoPlanilla(null);

		return sipreConceptoIngreso;
	}

	public List<SiprePlanillaDetalle> getSiprePlanillaDetalles() {
		return this.siprePlanillaDetalles;
	}

	public void setSiprePlanillaDetalles(List<SiprePlanillaDetalle> siprePlanillaDetalles) {
		this.siprePlanillaDetalles = siprePlanillaDetalles;
	}

	public SiprePlanillaDetalle addSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
		getSiprePlanillaDetalles().add(siprePlanillaDetalle);
		siprePlanillaDetalle.setSipreTipoPlanilla(this);

		return siprePlanillaDetalle;
	}

	public SiprePlanillaDetalle removeSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
		getSiprePlanillaDetalles().remove(siprePlanillaDetalle);
		siprePlanillaDetalle.setSipreTipoPlanilla(null);

		return siprePlanillaDetalle;
	}

}