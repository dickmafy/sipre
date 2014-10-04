package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SIPRE_PLANILLA_ADICIONAL database table.
 * 
 */
@Entity
@Table(name="SIPRE_PLANILLA_ADICIONAL")
@NamedQuery(name="SiprePlanillaAdicional.findAll", query="SELECT s FROM SiprePlanillaAdicional s")
public class SiprePlanillaAdicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiprePlanillaAdicionalPK id;

	@Column(name="NPD_MTO_CONCEPTO")
	private BigDecimal npdMtoConcepto;

	//bi-directional many-to-one association to SiprePlanillaDetalle
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CCI_CODIGO", referencedColumnName="CCI_CODIGO",insertable = false, updatable = false),
		@JoinColumn(name="CPERSONA_NRO_ADM", referencedColumnName="CPERSONA_NRO_ADM",insertable = false, updatable = false),
		@JoinColumn(name="CPLANILLA_MES_PROCESO", referencedColumnName="CPLANILLA_MES_PROCESO",insertable = false, updatable = false),
		@JoinColumn(name="CTP_CODIGO", referencedColumnName="CTP_CODIGO",insertable = false, updatable = false),
		@JoinColumn(name="NPLANILLA_NUM_PROCESO", referencedColumnName="NPLANILLA_NUM_PROCESO",insertable = false, updatable = false)
		})
	private SiprePlanillaDetalle siprePlanillaDetalle;

	public SiprePlanillaAdicional() {
	}

	public SiprePlanillaAdicionalPK getId() {
		return this.id;
	}

	public void setId(SiprePlanillaAdicionalPK id) {
		this.id = id;
	}

	public BigDecimal getNpdMtoConcepto() {
		return this.npdMtoConcepto;
	}

	public void setNpdMtoConcepto(BigDecimal npdMtoConcepto) {
		this.npdMtoConcepto = npdMtoConcepto;
	}

	public SiprePlanillaDetalle getSiprePlanillaDetalle() {
		return this.siprePlanillaDetalle;
	}

	public void setSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
		this.siprePlanillaDetalle = siprePlanillaDetalle;
	}

}