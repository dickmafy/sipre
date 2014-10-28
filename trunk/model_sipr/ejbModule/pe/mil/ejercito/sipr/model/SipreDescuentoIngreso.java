/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_DESCUENTO_INGRESO")
public class SipreDescuentoIngreso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
    @NotNull
	@Column(name = "CCD_CODIGO")
	private String cciCodigo;
	
	
	@Basic(optional = false)
    @NotNull
	@Column(name = "CCI_CODIGO")
	private String cciCodDestino;
	
	
	public SipreDescuentoIngreso() {
	}

}
