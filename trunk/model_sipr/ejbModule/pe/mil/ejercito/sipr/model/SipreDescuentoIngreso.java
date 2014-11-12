/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_DESCUENTO_INGRESO")
public class SipreDescuentoIngreso implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "CCD_CODIGO")
	private String				ccdCodigo;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CCI_CODIGO")
	private String				cciCodigo;

	public SipreDescuentoIngreso() {
	}

	public String getCcdCodigo() {
		return ccdCodigo;
	}

	public void setCcdCodigo(String ccdCodigo) {
		this.ccdCodigo = ccdCodigo;
	}

	public String getCciCodigo() {
		return cciCodigo;
	}

	public void setCciCodigo(String cciCodigo) {
		this.cciCodigo = cciCodigo;
	}
}
