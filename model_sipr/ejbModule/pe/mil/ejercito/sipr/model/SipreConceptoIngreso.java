package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_CONCEPTO_INGRESO database table.
 * 
 */
@Entity
@Table(name="SIPRE_CONCEPTO_INGRESO")
@NamedQuery(name="SipreConceptoIngreso.findAll", query="SELECT s FROM SipreConceptoIngreso s")
public class SipreConceptoIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CCI_CODIGO")
	private String cciCodigo;

	@Column(name="CCI_COD_DESTINO")
	private String cciCodDestino;

	@Column(name="CCI_COD_MEF")
	private String cciCodMef;

	@Column(name="VCI_DSC_CORTA")
	private String vciDscCorta;

	@Column(name="VCI_DSC_LARGA")
	private String vciDscLarga;

	//bi-directional many-to-one association to SipreTipoPlanilla
	@ManyToOne
	@JoinColumn(name="CTP_CODIGO")
	private SipreTipoPlanilla sipreTipoPlanilla;

	//bi-directional many-to-many association to SipreConceptoDescuento
	@ManyToMany
	@JoinTable(
		name="SIPRE_DESCUENTO_INGRESO"
		, joinColumns={
			@JoinColumn(name="CCI_CODIGO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="CCD_CODIGO")
			}
		)
	private List<SipreConceptoDescuento> sipreConceptoDescuentos;

	//bi-directional many-to-one association to SipreIngresoGrado
	@OneToMany(mappedBy="sipreConceptoIngreso")
	private List<SipreIngresoGrado> sipreIngresoGrados;

	//bi-directional many-to-one association to SiprePlanillaDetalle
	@OneToMany(mappedBy="sipreConceptoIngreso")
	private List<SiprePlanillaDetalle> siprePlanillaDetalles;

	//bi-directional many-to-one association to SipreTmpBonificacion
	@OneToMany(mappedBy="sipreConceptoIngreso")
	private List<SipreTmpBonificacion> sipreTmpBonificacions;

	//bi-directional many-to-one association to SipreTmpGuardia
	@OneToMany(mappedBy="sipreConceptoIngreso")
	private List<SipreTmpGuardia> sipreTmpGuardias;

	public SipreConceptoIngreso() {
	}

	public String getCciCodigo() {
		return this.cciCodigo;
	}

	public void setCciCodigo(String cciCodigo) {
		this.cciCodigo = cciCodigo;
	}

	public String getCciCodDestino() {
		return this.cciCodDestino;
	}

	public void setCciCodDestino(String cciCodDestino) {
		this.cciCodDestino = cciCodDestino;
	}

	public String getCciCodMef() {
		return this.cciCodMef;
	}

	public void setCciCodMef(String cciCodMef) {
		this.cciCodMef = cciCodMef;
	}

	public String getVciDscCorta() {
		return this.vciDscCorta;
	}

	public void setVciDscCorta(String vciDscCorta) {
		this.vciDscCorta = vciDscCorta;
	}

	public String getVciDscLarga() {
		return this.vciDscLarga;
	}

	public void setVciDscLarga(String vciDscLarga) {
		this.vciDscLarga = vciDscLarga;
	}

	public SipreTipoPlanilla getSipreTipoPlanilla() {
		return this.sipreTipoPlanilla;
	}

	public void setSipreTipoPlanilla(SipreTipoPlanilla sipreTipoPlanilla) {
		this.sipreTipoPlanilla = sipreTipoPlanilla;
	}

	public List<SipreConceptoDescuento> getSipreConceptoDescuentos() {
		return this.sipreConceptoDescuentos;
	}

	public void setSipreConceptoDescuentos(List<SipreConceptoDescuento> sipreConceptoDescuentos) {
		this.sipreConceptoDescuentos = sipreConceptoDescuentos;
	}

	public List<SipreIngresoGrado> getSipreIngresoGrados() {
		return this.sipreIngresoGrados;
	}

	public void setSipreIngresoGrados(List<SipreIngresoGrado> sipreIngresoGrados) {
		this.sipreIngresoGrados = sipreIngresoGrados;
	}

	public SipreIngresoGrado addSipreIngresoGrado(SipreIngresoGrado sipreIngresoGrado) {
		getSipreIngresoGrados().add(sipreIngresoGrado);
		sipreIngresoGrado.setSipreConceptoIngreso(this);

		return sipreIngresoGrado;
	}

	public SipreIngresoGrado removeSipreIngresoGrado(SipreIngresoGrado sipreIngresoGrado) {
		getSipreIngresoGrados().remove(sipreIngresoGrado);
		sipreIngresoGrado.setSipreConceptoIngreso(null);

		return sipreIngresoGrado;
	}

	public List<SiprePlanillaDetalle> getSiprePlanillaDetalles() {
		return this.siprePlanillaDetalles;
	}

	public void setSiprePlanillaDetalles(List<SiprePlanillaDetalle> siprePlanillaDetalles) {
		this.siprePlanillaDetalles = siprePlanillaDetalles;
	}

	public SiprePlanillaDetalle addSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
		getSiprePlanillaDetalles().add(siprePlanillaDetalle);
		siprePlanillaDetalle.setSipreConceptoIngreso(this);

		return siprePlanillaDetalle;
	}

	public SiprePlanillaDetalle removeSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
		getSiprePlanillaDetalles().remove(siprePlanillaDetalle);
		siprePlanillaDetalle.setSipreConceptoIngreso(null);

		return siprePlanillaDetalle;
	}

	public List<SipreTmpBonificacion> getSipreTmpBonificacions() {
		return this.sipreTmpBonificacions;
	}

	public void setSipreTmpBonificacions(List<SipreTmpBonificacion> sipreTmpBonificacions) {
		this.sipreTmpBonificacions = sipreTmpBonificacions;
	}

	public SipreTmpBonificacion addSipreTmpBonificacion(SipreTmpBonificacion sipreTmpBonificacion) {
		getSipreTmpBonificacions().add(sipreTmpBonificacion);
		sipreTmpBonificacion.setSipreConceptoIngreso(this);

		return sipreTmpBonificacion;
	}

	public SipreTmpBonificacion removeSipreTmpBonificacion(SipreTmpBonificacion sipreTmpBonificacion) {
		getSipreTmpBonificacions().remove(sipreTmpBonificacion);
		sipreTmpBonificacion.setSipreConceptoIngreso(null);

		return sipreTmpBonificacion;
	}

	public List<SipreTmpGuardia> getSipreTmpGuardias() {
		return this.sipreTmpGuardias;
	}

	public void setSipreTmpGuardias(List<SipreTmpGuardia> sipreTmpGuardias) {
		this.sipreTmpGuardias = sipreTmpGuardias;
	}

	public SipreTmpGuardia addSipreTmpGuardia(SipreTmpGuardia sipreTmpGuardia) {
		getSipreTmpGuardias().add(sipreTmpGuardia);
		sipreTmpGuardia.setSipreConceptoIngreso(this);

		return sipreTmpGuardia;
	}

	public SipreTmpGuardia removeSipreTmpGuardia(SipreTmpGuardia sipreTmpGuardia) {
		getSipreTmpGuardias().remove(sipreTmpGuardia);
		sipreTmpGuardia.setSipreConceptoIngreso(null);

		return sipreTmpGuardia;
	}

}