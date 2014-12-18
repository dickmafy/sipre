package pe.mil.ejercito.sipr.model;

import java.io.Serializable;

public class CrediticiaRegistro implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				codClasificadorEntCred;
	private String				codEntidadCrediticia;
	private String				razonSocial;
	private String				direccion;
	private String				email;
	private String				representanteEntidad;
	private String				sujetaDescuento;
	private String				ruc;
	private String				situacionEntidad;



	public String getCodClasificadorEntCred() {
		return codClasificadorEntCred;
	}

	public void setCodClasificadorEntCred(String codClasificadorEntCred) {
		this.codClasificadorEntCred = codClasificadorEntCred;
	}

	public String getCodEntidadCrediticia() {
		return codEntidadCrediticia;
	}

	public void setCodEntidadCrediticia(String codEntidadCrediticia) {
		this.codEntidadCrediticia = codEntidadCrediticia;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepresentanteEntidad() {
		return representanteEntidad;
	}

	public void setRepresentanteEntidad(String representanteEntidad) {
		this.representanteEntidad = representanteEntidad;
	}

	public String getSujetaDescuento() {
		return sujetaDescuento;
	}

	public void setSujetaDescuento(String sujetaDescuento) {
		this.sujetaDescuento = sujetaDescuento;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getSituacionEntidad() {
		return situacionEntidad;
	}

	public void setSituacionEntidad(String situacionEntidad) {
		this.situacionEntidad = situacionEntidad;
	}
}
