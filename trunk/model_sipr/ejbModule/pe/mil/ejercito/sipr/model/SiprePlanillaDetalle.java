package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SIPRE_PLANILLA_DETALLE database table.
 * 
 */
@Entity
@Table(name="SIPRE_PLANILLA_DETALLE")
@NamedQuery(name="SiprePlanillaDetalle.findAll", query="SELECT s FROM SiprePlanillaDetalle s")
public class SiprePlanillaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiprePlanillaDetallePK id;

	@Column(name="CPD_CON_DESTINO")
	private BigDecimal cpdConDestino;

	@Column(name="NPD_MTO_CONCEPTO")
	private BigDecimal npdMtoConcepto;

	//bi-directional many-to-one association to SiprePlanillaAdicional
	@OneToMany(mappedBy="siprePlanillaDetalle")
	private List<SiprePlanillaAdicional> siprePlanillaAdicionals;

	//bi-directional many-to-one association to SipreConceptoIngreso
	@ManyToOne
	@JoinColumn(name="CCI_CODIGO",insertable = false, updatable = false)
	private SipreConceptoIngreso sipreConceptoIngreso;

	//bi-directional many-to-one association to SiprePlanilla
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CPERSONA_NRO_ADM", referencedColumnName="CPERSONA_NRO_ADM",insertable = false, updatable = false),
		@JoinColumn(name="CPLANILLA_MES_PROCESO", referencedColumnName="CPLANILLA_MES_PROCESO",insertable = false, updatable = false),
		@JoinColumn(name="NPLANILLA_NUM_PROCESO", referencedColumnName="NPLANILLA_NUM_PROCESO",insertable = false, updatable = false)
		})
	private SiprePlanilla siprePlanilla;

	//bi-directional many-to-one association to SipreTipoPlanilla
	@ManyToOne
	@JoinColumn(name="CTP_CODIGO",insertable = false, updatable = false)
	private SipreTipoPlanilla sipreTipoPlanilla;

	public SiprePlanillaDetalle() {
	}

	public SiprePlanillaDetallePK getId() {
		return this.id;
	}

	public void setId(SiprePlanillaDetallePK id) {
		this.id = id;
	}

	public BigDecimal getCpdConDestino() {
		return this.cpdConDestino;
	}

	public void setCpdConDestino(BigDecimal cpdConDestino) {
		this.cpdConDestino = cpdConDestino;
	}

	public BigDecimal getNpdMtoConcepto() {
		return this.npdMtoConcepto;
	}

	public void setNpdMtoConcepto(BigDecimal npdMtoConcepto) {
		this.npdMtoConcepto = npdMtoConcepto;
	}

	public List<SiprePlanillaAdicional> getSiprePlanillaAdicionals() {
		return this.siprePlanillaAdicionals;
	}

	public void setSiprePlanillaAdicionals(List<SiprePlanillaAdicional> siprePlanillaAdicionals) {
		this.siprePlanillaAdicionals = siprePlanillaAdicionals;
	}

	public SiprePlanillaAdicional addSiprePlanillaAdicional(SiprePlanillaAdicional siprePlanillaAdicional) {
		getSiprePlanillaAdicionals().add(siprePlanillaAdicional);
		siprePlanillaAdicional.setSiprePlanillaDetalle(this);

		return siprePlanillaAdicional;
	}

	public SiprePlanillaAdicional removeSiprePlanillaAdicional(SiprePlanillaAdicional siprePlanillaAdicional) {
		getSiprePlanillaAdicionals().remove(siprePlanillaAdicional);
		siprePlanillaAdicional.setSiprePlanillaDetalle(null);

		return siprePlanillaAdicional;
	}

	public SipreConceptoIngreso getSipreConceptoIngreso() {
		return this.sipreConceptoIngreso;
	}

	public void setSipreConceptoIngreso(SipreConceptoIngreso sipreConceptoIngreso) {
		this.sipreConceptoIngreso = sipreConceptoIngreso;
	}

	public SiprePlanilla getSiprePlanilla() {
		return this.siprePlanilla;
	}

	public void setSiprePlanilla(SiprePlanilla siprePlanilla) {
		this.siprePlanilla = siprePlanilla;
	}

	public SipreTipoPlanilla getSipreTipoPlanilla() {
		return this.sipreTipoPlanilla;
	}

	public void setSipreTipoPlanilla(SipreTipoPlanilla sipreTipoPlanilla) {
		this.sipreTipoPlanilla = sipreTipoPlanilla;
	}

}