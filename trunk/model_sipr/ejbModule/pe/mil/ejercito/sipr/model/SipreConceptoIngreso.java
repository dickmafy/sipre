/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_CONCEPTO_INGRESO")
@NamedQueries({ @NamedQuery(name = "SipreConceptoIngreso.findAll", query = "SELECT s FROM SipreConceptoIngreso s") })
public class SipreConceptoIngreso implements Serializable {
	private static final long				serialVersionUID	= 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 4)
	@Column(name = "CCI_CODIGO")
	private String							cciCodigo;
	@Size(max = 4)
	@Column(name = "CCI_COD_DESTINO")
	private String							cciCodDestino;
	@Size(max = 80)
	@Column(name = "VCI_DSC_LARGA")
	private String							vciDscLarga;
	@Size(max = 40)
	@Column(name = "VCI_DSC_CORTA")
	private String							vciDscCorta;
	@Size(max = 6)
	@Column(name = "CCI_COD_MEF")
	private String							cciCodMef;
	@ManyToMany(mappedBy = "sipreConceptoIngresoList")
	private List<SipreConceptoDescuento>	sipreConceptoDescuentoList;
	@JoinColumn(name = "CTP_CODIGO", referencedColumnName = "CTP_CODIGO")
	@ManyToOne
	private SipreTipoPlanilla				sipreTipoPlanilla;

	@Transient
	private Boolean							check;

	public SipreConceptoIngreso() {
	}

	public SipreConceptoIngreso(String cciCodigo) {
		this.cciCodigo = cciCodigo;
	}

	public String getCciCodigo() {
		return cciCodigo;
	}

	public void setCciCodigo(String cciCodigo) {
		this.cciCodigo = cciCodigo;
	}

	public String getCciCodDestino() {
		return cciCodDestino;
	}

	public void setCciCodDestino(String cciCodDestino) {
		this.cciCodDestino = cciCodDestino;
	}

	public String getVciDscLarga() {
		return vciDscLarga;
	}

	public void setVciDscLarga(String vciDscLarga) {
		this.vciDscLarga = vciDscLarga;
	}

	public String getVciDscCorta() {
		return vciDscCorta;
	}

	public void setVciDscCorta(String vciDscCorta) {
		this.vciDscCorta = vciDscCorta;
	}

	public String getCciCodMef() {
		return cciCodMef;
	}

	public void setCciCodMef(String cciCodMef) {
		this.cciCodMef = cciCodMef;
	}

	public List<SipreConceptoDescuento> getSipreConceptoDescuentoList() {
		return sipreConceptoDescuentoList;
	}

	public void setSipreConceptoDescuentoList(
			List<SipreConceptoDescuento> sipreConceptoDescuentoList) {
		this.sipreConceptoDescuentoList = sipreConceptoDescuentoList;
	}

	public SipreTipoPlanilla getSipreTipoPlanilla() {
		return sipreTipoPlanilla;
	}

	public void setSipreTipoPlanilla(SipreTipoPlanilla sipreTipoPlanilla) {
		this.sipreTipoPlanilla = sipreTipoPlanilla;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cciCodigo != null ? cciCodigo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SipreConceptoIngreso)) {
			return false;
		}
		SipreConceptoIngreso other = (SipreConceptoIngreso) object;
		if ((this.cciCodigo == null && other.cciCodigo != null)
				|| (this.cciCodigo != null && !this.cciCodigo
						.equals(other.cciCodigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.SipreConceptoIngreso[ cciCodigo="
				+ cciCodigo + " ]";
	}

}
