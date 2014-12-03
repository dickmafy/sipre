/* To change this license header, choose License Headers in Project Properties. To change this template file, choose Tools | Templates and
 * open the template in the editor. */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_CALCULO_DESCUENTO_LEY")
@NamedQueries({
		@NamedQuery(name = "SipreCalculoDescuentoLey.findAll", query = "SELECT s FROM SipreCalculoDescuentoLey s") })
public class SipreCalculoDescuentoLey implements Serializable {
	private static final long				serialVersionUID	= 1L;
	@EmbeddedId
	protected SipreCalculoDescuentoLeyPK	sipreCalculoDescuentoLeyPK;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "NCDL_MTO_EMPLEADO")
	private BigDecimal						ncdlMtoEmpleado;
	@Column(name = "NCDL_MTO_EMPLEADOR")
	private BigDecimal						ncdlMtoEmpleador;
	@Column(name = "NCDL_MTO_APLICABLE")
	private BigDecimal						ncdlMtoAplicable;
	@JoinColumn(name = "CTP_CODIGO", referencedColumnName = "CTP_CODIGO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SipreTipoPlanilla				sipreTipoPlanilla;
	@JoinColumns({
			@JoinColumn(name = "CPLANILLA_MES_PROCESO", referencedColumnName = "CPLANILLA_MES_PROCESO", insertable = false,
					updatable = false),
			@JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false),
			@JoinColumn(name = "NPLANILLA_NUM_PROCESO", referencedColumnName = "NPLANILLA_NUM_PROCESO", insertable = false,
					updatable = false) })
	@ManyToOne(optional = false)
	private SiprePlanilla					siprePlanilla;
	@JoinColumn(name = "CCD_CODIGO", referencedColumnName = "CCD_CODIGO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SipreConceptoDescuento			sipreConceptoDescuento;

	public SipreCalculoDescuentoLey() {
	}

	public SipreCalculoDescuentoLey(SipreCalculoDescuentoLeyPK sipreCalculoDescuentoLeyPK) {
		this.sipreCalculoDescuentoLeyPK = sipreCalculoDescuentoLeyPK;
	}

	public SipreCalculoDescuentoLey(String ctpCodigo, String ccdCodigo, String cpersonaNroAdm, String cplanillaMesProceso,
			Integer nplanillaNumProceso) {
		this.sipreCalculoDescuentoLeyPK = new SipreCalculoDescuentoLeyPK(ctpCodigo, ccdCodigo, cpersonaNroAdm, cplanillaMesProceso,
				nplanillaNumProceso);
	}

	public SipreCalculoDescuentoLeyPK getSipreCalculoDescuentoLeyPK() {
		return sipreCalculoDescuentoLeyPK;
	}

	public void setSipreCalculoDescuentoLeyPK(SipreCalculoDescuentoLeyPK sipreCalculoDescuentoLeyPK) {
		this.sipreCalculoDescuentoLeyPK = sipreCalculoDescuentoLeyPK;
	}

	public BigDecimal getNcdlMtoEmpleado() {
		return ncdlMtoEmpleado;
	}

	public void setNcdlMtoEmpleado(BigDecimal ncdlMtoEmpleado) {
		this.ncdlMtoEmpleado = ncdlMtoEmpleado;
	}

	public BigDecimal getNcdlMtoEmpleador() {
		return ncdlMtoEmpleador;
	}

	public void setNcdlMtoEmpleador(BigDecimal ncdlMtoEmpleador) {
		this.ncdlMtoEmpleador = ncdlMtoEmpleador;
	}

	public BigDecimal getNcdlMtoAplicable() {
		return ncdlMtoAplicable;
	}

	public void setNcdlMtoAplicable(BigDecimal ncdlMtoAplicable) {
		this.ncdlMtoAplicable = ncdlMtoAplicable;
	}

	public SipreTipoPlanilla getSipreTipoPlanilla() {
		return sipreTipoPlanilla;
	}

	public void setSipreTipoPlanilla(SipreTipoPlanilla sipreTipoPlanilla) {
		this.sipreTipoPlanilla = sipreTipoPlanilla;
	}

	public SiprePlanilla getSiprePlanilla() {
		return siprePlanilla;
	}

	public void setSiprePlanilla(SiprePlanilla siprePlanilla) {
		this.siprePlanilla = siprePlanilla;
	}

	public SipreConceptoDescuento getSipreConceptoDescuento() {
		return sipreConceptoDescuento;
	}

	public void setSipreConceptoDescuento(SipreConceptoDescuento sipreConceptoDescuento) {
		this.sipreConceptoDescuento = sipreConceptoDescuento;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (sipreCalculoDescuentoLeyPK != null ? sipreCalculoDescuentoLeyPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		
		if (!(object instanceof SipreCalculoDescuentoLey)) {
			return false;
		}
		SipreCalculoDescuentoLey other = (SipreCalculoDescuentoLey) object;
		if ((this.sipreCalculoDescuentoLeyPK == null && other.sipreCalculoDescuentoLeyPK != null)
				|| (this.sipreCalculoDescuentoLeyPK != null && !this.sipreCalculoDescuentoLeyPK.equals(other.sipreCalculoDescuentoLeyPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.SipreCalculoDescuentoLey[ sipreCalculoDescuentoLeyPK=" + sipreCalculoDescuentoLeyPK + " ]";
	}

}
