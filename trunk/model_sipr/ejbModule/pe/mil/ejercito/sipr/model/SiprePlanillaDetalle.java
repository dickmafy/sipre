/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_PLANILLA_DETALLE")
@NamedQueries({ @NamedQuery(name = "SiprePlanillaDetalle.findAll", query = "SELECT s FROM SiprePlanillaDetalle s") })
public class SiprePlanillaDetalle implements Serializable {
	private static final long				serialVersionUID	= 1L;
	@EmbeddedId
	protected SiprePlanillaDetallePK		siprePlanillaDetallePK;

	@Column(name = "IND_PROCESO")
	private String							indProceso;

	@Column(name = "NPD_MTO_CONCEPTO")
	private BigDecimal						npdMtoConcepto;
	@Size(max = 4)
	@Column(name = "CPD_CON_DESTINO")
	private String							cpdConDestino;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "siprePlanillaDetalle")
	private List<SiprePlanillaAdicional>	siprePlanillaAdicionalList;
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
	@JoinColumn(name = "CCI_CODIGO", referencedColumnName = "CCI_CODIGO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SipreConceptoIngreso			sipreConceptoIngreso;

	public SiprePlanillaDetalle() {
	}

	public SiprePlanillaDetalle(SiprePlanillaDetallePK siprePlanillaDetallePK) {
		this.siprePlanillaDetallePK = siprePlanillaDetallePK;
	}

	public SiprePlanillaDetalle(String ctpCodigo, String cciCodigo, String cpersonaNroAdm, String cplanillaMesProceso,
			Integer nplanillaNumProceso) {
		this.siprePlanillaDetallePK = new SiprePlanillaDetallePK(ctpCodigo, cciCodigo, cpersonaNroAdm, cplanillaMesProceso,
				nplanillaNumProceso);
	}

	public SiprePlanillaDetallePK getSiprePlanillaDetallePK() {
		return siprePlanillaDetallePK;
	}

	public void setSiprePlanillaDetallePK(SiprePlanillaDetallePK siprePlanillaDetallePK) {
		this.siprePlanillaDetallePK = siprePlanillaDetallePK;
	}

	public BigDecimal getNpdMtoConcepto() {
		return npdMtoConcepto;
	}

	public void setNpdMtoConcepto(BigDecimal npdMtoConcepto) {
		this.npdMtoConcepto = npdMtoConcepto;
	}

	public String getCpdConDestino() {
		return cpdConDestino;
	}

	public void setCpdConDestino(String cpdConDestino) {
		this.cpdConDestino = cpdConDestino;
	}

	public List<SiprePlanillaAdicional> getSiprePlanillaAdicionalList() {
		return siprePlanillaAdicionalList;
	}

	public void setSiprePlanillaAdicionalList(List<SiprePlanillaAdicional> siprePlanillaAdicionalList) {
		this.siprePlanillaAdicionalList = siprePlanillaAdicionalList;
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

	public SipreConceptoIngreso getSipreConceptoIngreso() {
		return sipreConceptoIngreso;
	}

	public void setSipreConceptoIngreso(SipreConceptoIngreso sipreConceptoIngreso) {
		this.sipreConceptoIngreso = sipreConceptoIngreso;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (siprePlanillaDetallePK != null ? siprePlanillaDetallePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof SiprePlanillaDetalle)) {
			return false;
		}
		SiprePlanillaDetalle other = (SiprePlanillaDetalle) object;
		if ((this.siprePlanillaDetallePK == null && other.siprePlanillaDetallePK != null)
				|| (this.siprePlanillaDetallePK != null && !this.siprePlanillaDetallePK.equals(other.siprePlanillaDetallePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SiprePlanillaDetalle [siprePlanillaDetallePK=" + siprePlanillaDetallePK + ", npdMtoConcepto=" + npdMtoConcepto
				+ ", cpdConDestino=" + cpdConDestino + ", siprePlanillaAdicionalList=" + siprePlanillaAdicionalList
				+ ", sipreTipoPlanilla=" + sipreTipoPlanilla + ", siprePlanilla=" + siprePlanilla + ", sipreConceptoIngreso="
				+ sipreConceptoIngreso + "]";
	}

	public String getIndProceso() {
		return indProceso;
	}

	public void setIndProceso(String indProceso) {
		this.indProceso = indProceso;
	}


}
