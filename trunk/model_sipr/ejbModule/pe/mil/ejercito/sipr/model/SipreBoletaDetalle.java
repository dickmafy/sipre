package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SIPRE_BOLETA_DETALLE database table.
 * 
 */
@Entity
@Table(name="SIPRE_BOLETA_DETALLE")
@NamedQuery(name="SipreBoletaDetalle.findAll", query="SELECT s FROM SipreBoletaDetalle s")
public class SipreBoletaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreBoletaDetallePK id;

	@Column(name="CBD_COD_ING_DESC")
	private String cbdCodIngDesc;

	@Column(name="CBD_IND_TIPO")
	private String cbdIndTipo;

	@Column(name="CBD_TIP_CONCPTO")
	private String cbdTipConcpto;

	@Column(name="NBD_MONTO")
	private BigDecimal nbdMonto;

	@Column(name="NBD_NUM_CUO_PAGADA")
	private BigDecimal nbdNumCuoPagada;

	@Column(name="NBD_NUM_CUO_TOTAL")
	private BigDecimal nbdNumCuoTotal;

	@Column(name="VBD_DSC_ING_DESC")
	private String vbdDscIngDesc;

	//bi-directional many-to-one association to SipreBoletaCabecera
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CBC_MES_PROCESO", referencedColumnName="CBC_MES_PROCESO",insertable = false, updatable = false),
		@JoinColumn(name="CBC_NRO_ADM", referencedColumnName="CBC_NRO_ADM",insertable = false, updatable = false),
		@JoinColumn(name="NBC_NUM_PROCESO", referencedColumnName="NBC_NUM_PROCESO",insertable = false, updatable = false)
		})
	private SipreBoletaCabecera sipreBoletaCabecera;

	public SipreBoletaDetalle() {
	}

	public SipreBoletaDetallePK getId() {
		return this.id;
	}

	public void setId(SipreBoletaDetallePK id) {
		this.id = id;
	}

	public String getCbdCodIngDesc() {
		return this.cbdCodIngDesc;
	}

	public void setCbdCodIngDesc(String cbdCodIngDesc) {
		this.cbdCodIngDesc = cbdCodIngDesc;
	}

	public String getCbdIndTipo() {
		return this.cbdIndTipo;
	}

	public void setCbdIndTipo(String cbdIndTipo) {
		this.cbdIndTipo = cbdIndTipo;
	}

	public String getCbdTipConcpto() {
		return this.cbdTipConcpto;
	}

	public void setCbdTipConcpto(String cbdTipConcpto) {
		this.cbdTipConcpto = cbdTipConcpto;
	}

	public BigDecimal getNbdMonto() {
		return this.nbdMonto;
	}

	public void setNbdMonto(BigDecimal nbdMonto) {
		this.nbdMonto = nbdMonto;
	}

	public BigDecimal getNbdNumCuoPagada() {
		return this.nbdNumCuoPagada;
	}

	public void setNbdNumCuoPagada(BigDecimal nbdNumCuoPagada) {
		this.nbdNumCuoPagada = nbdNumCuoPagada;
	}

	public BigDecimal getNbdNumCuoTotal() {
		return this.nbdNumCuoTotal;
	}

	public void setNbdNumCuoTotal(BigDecimal nbdNumCuoTotal) {
		this.nbdNumCuoTotal = nbdNumCuoTotal;
	}

	public String getVbdDscIngDesc() {
		return this.vbdDscIngDesc;
	}

	public void setVbdDscIngDesc(String vbdDscIngDesc) {
		this.vbdDscIngDesc = vbdDscIngDesc;
	}

	public SipreBoletaCabecera getSipreBoletaCabecera() {
		return this.sipreBoletaCabecera;
	}

	public void setSipreBoletaCabecera(SipreBoletaCabecera sipreBoletaCabecera) {
		this.sipreBoletaCabecera = sipreBoletaCabecera;
	}

}